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
      <main class="main">
        <compare-job-filter class="main__filter"/>
        <ul class="main__job-list">
          <li
            is="compare-job-item"
            class="main__job-item"
            v-for="(job, index) in jobLists"
            :key="`job${index}`"
            :name="job.name"
            :distance="job.distance"
            :period="job.period"
            :workTime="job.workTime"
            :hourlyWage="job.hourlyWage"
            :href="job.href"
            @delete="deleteItem(index)">
          </li>
        </ul>
      </main>
    </div>
  </div>
</template>

<script>
import CompareJobFilter from './CompareJob/CompareJobFilter';
import CompareJobItem from './CompareJob/CompareJobItem';

export default {
  name: 'compare-job',
  components: {
    CompareJobFilter,
    CompareJobItem,
  },
  data() {
    return {
      uri: '',
      jobLists: [{
        name: 'GS25 편의점 아르바이트',
        distance: '16km',
        period: '17일 근무 (2018.04.01 ~ 2018.04.18)',
        workTime: '5시간 (17시 ~ 22시)',
        hourlyWage: '8,690원',
        href: '#',
      }, {
        name: 'GS25 편의점 아르바이트',
        distance: '17km',
        period: '17일 근무 (2018.04.01 ~ 2018.04.18)',
        workTime: '5시간 (17시 ~ 22시)',
        hourlyWage: '8,690원',
        href: '#',
      }, {
        name: 'GS25 편의점 아르바이트',
        distance: '18km',
        period: '17일 근무 (2018.04.01 ~ 2018.04.18)',
        workTime: '5시간 (17시 ~ 22시)',
        hourlyWage: '8,690원',
        href: '#',
      }],
    };
  },
  methods: {
    addJob() {
      // request to uri crawling for job data
    },
    deleteItem(index) {
      this.jobLists.splice(index, 1);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;
$compare-job-width: 1670px;

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

.compare-job {
  background-color: $main-color1;
  height: auto;
  padding-top: 70px;
  width: 100%;
  @include e('header') {
    $header-height: 60px;

    width: $compare-job-width;
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
}

.main {
  width: $compare-job-width;
  margin: 50px auto 0;
  display: flex;
  @include e('filter') {
    width: 600px;
    margin-right: 50px;
  }
  @include e('job-list') {
    width: 1020px;
  }
  @include e('job-item') {
    border-bottom: 1px solid $point-color1;
    &:last-child {
      border: none;
    }
  }
}

</style>
