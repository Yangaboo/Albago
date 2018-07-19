<template>
  <div class="wrap">
    <div class="compare-job">
      <header class="compare-job__header">
        <h1 class="compare-job__header__title">
          비교 필터
        </h1>
        <button class="compare-job__header__cancle">
          중복 제거 취소
        </button>
        <input type="text"
          class="compare-job__header__input-search">
        <button class="compare-job__header__button-search">
          검색
        </button>
      </header>
      <main class="compare-job__main">
        <compare-job-filter class="compare-job__header__filter"/>
        <compare-job-list class="compare-job__header__list"/>
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
  background-color: $main-color1;
  height: calc(100vh - 70px);
  padding: 70px 153px;
}
</style>
