<template>
  <div class="wrap">
    <navigation/>
    <div class="compare-job">
      <header class="compare-job__header">
        <h1 class="compare-job__header__title">
          비교 필터
        </h1>
        <button class="delete-all-btn" @click="deleteAll">
          전체 제거
        </button>
        <form @submit.prevent="addJob" class="add-job">
          <input
            type="url"
            class="add-job__uri-input"
            v-model="jobUrl"
            placeholder="URI를 입력하세요">
          <input
            type="submit"
            class="add-job__request-btn"
            value="알바 추가"/>
        </form>
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
            :startTime="job.startTime"
            :endTime="job.endTime"
            :period="job.period"
            :hourlyWage="job.hourlyWage"
            :href="job.href"
            :days="job.days"
            :tags="job.tags"
            @delete="deleteItem(index)">
          </li>
        </ul>
      </main>
    </div>
  </div>
</template>

<script>
import Navigation from './Common/Navigation';
import CompareJobFilter from './CompareJob/CompareJobFilter';
import CompareJobItem from './CompareJob/CompareJobItem';
import URI from '../constants/uri';

export default {
  name: 'compare-job',
  components: {
    Navigation,
    CompareJobFilter,
    CompareJobItem,
  },
  data() {
    return {
      jobUrl: '',
      jobLists: [],
    };
  },
  methods: {
    addJob() {
      const startX = localStorage.getItem('x');
      const startY = localStorage.getItem('y');
      const url = this.jobUrl;
      this.$axios.post(`${URI}/filter`, { startX, startY, url })
        .then((res) => {
          const { data } = res;
          data.distance = Number(data.distance);
          data.startTime = Number(data.startTime);
          data.endTime = Number(data.endTime);
          data.hourlyWage = Number(data.hourlyWage);
          data.days = JSON.parse(data.days);
          data.href = url;

          data.sex = Number(data.sex);
          data.isTeen = data.isTeen === 'true';
          data.tags = [];
          if (
            (data.startTime >= 6 && data.startTime <= 21) ||
            (data.endTime >= 6 && data.endTime <= 21)
          ) {
            data.tags.push('주간');
          } if (
            (data.startTime <= 6 && data.startTime >= 21) ||
            (data.endTime <= 6 && data.endTime >= 21)
          ) {
            data.tags.push('야간');
          } if (data.sex === 0 || data.sex === 1) {
            data.tags.push('남자');
          } if (data.sex === 0 || data.sex === 2) {
            data.tags.push('여자');
          } if (data.isTeen) {
            data.tags.push('청소년');
          }

          this.jobLists.push(data);
        });
    },
    deleteAll() {
      this.jobLists = [];
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
$shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);

.delete-all-btn {
  width: 220px;
  height: 100%;
  border-radius: 5px;
  background-color: $point-color1;
  box-shadow: $shadow;
  border: none;
  font-size: 18px;
  color: $main-color1;
  position: absolute;
  top: 0;
  right: 600px;
}

.add-job {
  width: 570px;
  box-shadow: $shadow;
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
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.151);
    height: fit-content;
  }
  @include e('job-item') {
    border-bottom: 1px solid $point-color1;
    &:last-child {
      border: none;
    }
  }
}

</style>
