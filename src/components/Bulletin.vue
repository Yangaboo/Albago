<template>
  <div class="wrap">
    <navigation/>
    <slideshow/>
    <div class="bulletin">
      <div class="bulletin__container">
        <header class="bulletin__header">
          <div class="bulletin__outline">
            총 {{ totalElements }}건
            <!-- 금일 {{ todayBulletin }}건 -->
          </div>
          <div class="bulletin__nav-tool">
            <select
              class="bulletin__category"
              v-model="currentCategory"
              @change="getPostList(1)">
              <option v-for="option in categoryOptions"
                :key="option.value"
                :value="option.value">
                {{ option.text }}
              </option>
            </select>
            <input
              type="search"
              placeholder="검색어.."
              class="bulletin__search-input">
            <button class="bulletin__search-button">검색</button>
          </div>
        </header>

        <bulletin-list
          class="bulletin__bulletin-list"
          :bulletins="bulletins"/>

        <bulletin-paging
          class="bulletin__bulletin-paging"
          :selected="currentPage"
          @select="number => getPostList(number)"
          :pagesTotalNumber="totalPages"/>
      </div>
    </div>
  </div>
</template>

<script>
import Navigation from './Common/Navigation';
import Slideshow from './Common/Slideshow';
import BulletinList from './Bulletin/BulletinList';
import BulletinPaging from './Bulletin/BulletinPaging';
import { categoryObj } from '../constants/category';
import uri from '../constants/uri';

export default {
  name: 'bulletin',
  data() {
    return {
      categoryOptions: categoryObj,
      currentCategory: 0,
      bulletins: null,
      currentPage: 1,
      totalPages: 0,
      totalElements: 0,
    };
  },
  components: {
    Navigation,
    Slideshow,
    BulletinList,
    BulletinPaging,
  },
  methods: {
    getPostList(number) {
      this.currentPage = number;
      this.$axios.get(`${uri}/posts?page=${number - 1}&catId=${this.currentCategory}`)
        .then(({ data }) => {
          console.log(data);
          this.bulletins = data.content;
          this.totalPages = data.totalPages;
          this.totalElements = data.totalElements;
        });
    },
  },
  created() {
    this.getPostList(this.currentPage);
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';

.bulletin {
  $header-height: 30px;

  height: 800px;
  width: 100%;
  background-image: linear-gradient(to left, #494f5c, #373c46);
  color: #fff;
  @include e('container') {
    margin: 0 auto;
    width: 1200px;
    padding: {
      top: 56px;
    }
  }
  @include e('header') {
    height: $header-height;
    position: relative;
  }
  @include e('outline') {
    font-size: 15px;
    line-height: $header-height;
  }
  @include e('nav-tool') {
    position: absolute;
    top: 0;
    right: 0;
    height: 100%;
    font-size: 0;
    display: flex;
  }
  %tool {
    background-color: #fff;
    height: 100%;
    font-size: 11px;
    display: block;
    box-sizing: border-box;
    color: #494f5c;
    border: none;
    margin-left: 5px;
  }
  @include e('category') {
    @extend %tool;
    width: 200px;
    appearance: none;
    padding-left: 15px;
  }
  @include e('search-input') {
    @extend %tool;
    width: 175px;
    padding-left: 35px;
  }
  @include e('search-button') {
    @extend %tool;
    width: 45px;
  }
}
</style>
