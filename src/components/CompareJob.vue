<template>
  <div class="wrap">
    <div class="compare-job">
      <header class="compare-job__header">
        <h1 class="compare-job__header__title">
          비교 필터
        </h1>
        <button class="compare-job__header__cancel">
          중복 제거 취소
        </button>
        <div class="compare-job__header__search">
          <input type="text"
            class="compare-job__header__search__input"
            placeholder="검색어를 입력하시오">
          <button class="compare-job__header__search__button">
            검색
          </button>
        </div>
      </header>
      <main class="compare-job__main">
        <compare-job-filter class="compare-job__main__filter"/>
        <compare-job-list class="compare-job__main__list"/>
      </main>
    </div>
  </div>
</template>

<script>
import CompareJobFilter from './CompareJob/CompareJobFilter';
import CompareJobList from './CompareJob/CompareJobList';

export default {
  name: 'compare-job',
  components: {
    CompareJobFilter,
    CompareJobList,
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

.compare-job {
  $width: 1670px;

  background-color: $main-color1;
  height: calc(100vh - 70px);
  padding-top: 70px;
  width: 100%;
  @include e('header') {
    width: $width;
    margin: {
      left: auto;
      right: auto;
      bottom: 40px;
    }
    height: 60px;
    position: relative;
    font-size: 0;
    @include e('title') {
      display: inline-block;
      height: 100%;
      line-height: 60px;
      font-size: 30px;
      font-weight: bold;
      color: #fff;
    }
    @include e('cancel') {
      width: 220px;
      height: 100%;
      border-radius: 5px;
      background-color: $point-color1;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
      border: none;
      font-size: 18px;
      color: $main-color1;
      position: absolute;
      top: 0;
      right: 600px;
    }
    @include e('search') {
      width: 570px;
      height: 100%;
      border-radius: 5px;
      background-color: #fff;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
      display: flex;
      position: absolute;
      top: 0;
      right: 0;
      padding: {
        left: 92px;
      }
      @include e('input') {
        width: 450px;
        font-size: 18px;
        color: $main-color1;
        line-height: 21px;
        height: 21px;
        width: 100%;
        margin-top: 20px;
        &::-webkit-input-placeholder {
          opacity: 0.6;
        }
      }
      @include e('button') {
        width: 120px;
        height: 100%;
        background-color: $main-color2;
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
        color: #fff;
        font-size: 20px;
      }
    }
  }
  @include e('main') {
    width: $width;
    margin: 0 auto;
    display: flex;
    @include e('filter') {
      width: 600px;
      margin-right: 50px;
    }
    @include e('list') {
      width: 1020px;
    }
  }
}
</style>
