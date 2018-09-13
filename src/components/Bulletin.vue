<template>
  <div class="wrap">
    <slideshow/>
    <div class="bulletin">
      <div class="bulletin__container">
        <header class="bulletin__header">
          <div class="bulletin__outline">
            총 {{ totalBulletin }}건 / 금일 {{ todayBulletin }}건
          </div>
          <div class="bulletin__nav-tool">
            <select class="bulletin__category">
              <option disabled selected>카테고리</option>
            </select>
            <input type="search"
              placeholder="검색어.."
              class="bulletin__search-input">
            <button class="bulletin__search-button">검색</button>
          </div>
        </header>
        <bulletin-list class="bulletin__bulletin-list"/>
        <bulletin-paging class="bulletin__bulletin-paging"
          :pagesTotalNumber="25"/>
      </div>
    </div>
  </div>
</template>

<script>
import Slideshow from './Common/Slideshow';
import BulletinList from './Bulletin/BulletinList';
import BulletinPaging from './Bulletin/BulletinPaging';

export default {
  name: 'bulletin',
  data() {
    return {
      totalBulletin: 0,
      todayBulletin: 0,
    };
  },
  components: {
    Slideshow,
    BulletinList,
    BulletinPaging,
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
    width: 75px;
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
