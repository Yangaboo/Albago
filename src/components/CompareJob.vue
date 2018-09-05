<template>
  <div class="wrap">
    <div class="compare-job">
      <header class="compare-job__header">
        <h1 class="compare-job__header__title">
          비교 필터
        </h1>
        <button class="delete-all-btn">
          전체 제거
        </button>
        <div class="add-job">
          <input
            type="url"
            class="add-job__uri-input"
            v-model="uri"
            placeholder="URI를 입력하세요">
          <button
            class="add-job__request-btn"
            @click="addJob">
            알바 추가
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
  data() {
    return {
      uri: '',
    };
  },
  methods: {
    addJob() {
      // request to uri crawling for job data
    },
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
  height: auto;
  padding-top: 70px;
  width: 100%;
  @include e('header') {
    $header-height: 60px;

    width: $width;
    margin: {
      left: auto;
      right: auto;
      bottom: 40px;
    }
    height: $header-height;
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
    .add-job {
      height: 100%;
      display: flex;
      position: absolute;
      top: 0;
      right: 0;
    }
  }
  @include e('main') {
    width: $width;
    margin: 50px auto 0;
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

.delete-all-btn {
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

.add-job {
  width: 570px;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
  @include e('uri-input') {
    height: 100%;
    flex: 1;
    font-size: 18px;
    color: $main-color1;
    background-color: #fff;
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
    padding: 0 20px;
    &::-webkit-input-placeholder {
      opacity: 0.6;
    }
  }
  @include e('request-btn') {
    width: 120px;
    height: 100%;
    background-color: $main-color2;
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
    color: #fff;
    font-size: 20px;
  }
}
</style>
