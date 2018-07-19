<template>
  <div class="wap">
    <slideshow/>
    <div class="bulletin">
      <div class="bulletin__container">
        <header class="bulletin__container__header">
          <div class="bulletin__container__header__outline">
            총 {{ totalBulletin }}건 / 금일 {{ todayBulletin }}건
          </div>
          <div class="bulletin__container__header__navigation-tool">
            <select class="bulletin__container__header__navigation-tool__category">
              <option disabled selected>카테고리</option>
            </select>
            <input type="search"
              placeholder="검색어.."
              class="bulletin__container__header__navigation-tool__search-input">
            <button class="bulletin__container__header__navigation-tool__search-button">검색</button>
          </div>
        </header>
        <bulletin-list class="bulletin__container__bulletin-list"/>
        <bulletin-paging class="bulletin__container__bulletin-paging"
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
    @include e('header') {
      $height: 30px;
      height: $height;
      position: relative;
      @include e('outline') {
        font-size: 15px;
        line-height: $height;
      }
      @include e('navigation-tool') {
        position: absolute;
        top: 0;
        right: 0;
        height: 100%;
        font-size: 0;
        display: flex;
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
    }
  }
}
</style>
