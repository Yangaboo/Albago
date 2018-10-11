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
            :workTime="job.workTime"
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
import PERIOD from '../constants/period';
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
  watch: {
    jobLists(list) {
      localStorage.setItem('job-list', JSON.stringify(list));
    },
  },
  computed: {
    points() {
      return this.jobLists.map(item => item.point);
    },
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
          data.days = JSON.parse(data.days.replace('\\', ''));
          data.href = url;
          data.periodWeight = PERIOD[data.period];
          const start = data.startTime;
          const end = data.endTime;
          data.workTime = start > end ? (end + 24) - start : end - start;

          data.isDay = false;
          data.isNight = false;
          data.sex = Number(data.sex);
          data.isTeen = data.isTeen === 'true';
          data.tags = [];
          if (
            (data.startTime >= 6 && data.startTime <= 21) ||
            (data.endTime >= 6 && data.endTime <= 21)
          ) {
            data.isDay = true;
            data.tags.push('주간');
          } if (
            (data.startTime <= 6 && data.startTime >= 21) ||
            (data.endTime <= 6 && data.endTime >= 21)
          ) {
            data.isNight = true;
            data.tags.push('야간');
          } if (data.sex === 0 || data.sex === 1) {
            data.tags.push('남자');
          } if (data.sex === 0 || data.sex === 2) {
            data.tags.push('여자');
          } if (data.isTeen) {
            data.tags.push('청소년');
          }

          this.jobLists.push(data);
          this.saveJobList();
        });
    },
    deleteAll() {
      this.jobLists = [];
    },
    deleteItem(index) {
      this.jobLists.splice(index, 1);
    },
    compare(relevantFilter, selectFilter) {
      let filtered = this.jobLists;

      // select 필터링
      filtered = filtered.filter(({ days }) => days.every(day => selectFilter[0].includes(day)));
      if (selectFilter[1] === 'day') {
        filtered = filtered.filter(({ isDay }) => isDay);
      } else if (selectFilter[1] === 'night') {
        filtered = filtered.filter(({ isNight }) => isNight);
      }
      if (selectFilter[2] === 'male') {
        filtered = filtered.filter(({ sex }) => sex === 0 || sex === 1);
      } else if (selectFilter[2] === 'female') {
        filtered = filtered.filter(({ sex }) => sex === 0 || sex === 2);
      }
      if (selectFilter[3] === 'teen') {
        filtered = filtered.filter(({ isTeen }) => isTeen);
      }

      // 점수 초기화
      filtered.forEach((item) => {
        const job = item;
        job.point = 0;
      });

      // 비교 및 점수 부여
      relevantFilter.forEach(({ name, value }, index) => {
        switch (name) {
          case '거리': this.compareDistance(filtered, value, index); break;
          case '시급': this.compareHourlyWage(filtered, value, index); break;
          case '근무 기간': this.comparePeriod(filtered, value, index); break;
          case '근무 시간': this.compareWorkTime(filtered, value, index); break;
          default: break;
        }
      });

      // 점수를 기준으로 정렬, 적용
      filtered.sort((a, b) => b.point - a.point);
      this.jobLists = filtered;
    },
    compareDistance(list, value, priority) {
      const comparable = [];
      const originalList = list;
      originalList.forEach(({ distance }, index) => comparable.push({ distance, index }));
      comparable.sort((a, b) => (value === 'close' ? a.distance - b.distance : b.distance - a.distance));
      comparable.forEach(({ distance }, i) => {
        if (i !== 0 && distance === comparable[i - 1].distance) {
          comparable[i].point = comparable[i - 1].point;
        } else {
          comparable[i].point = (list.length - i) * (4 - priority);
        }
      });
      comparable.forEach(({ index, point }) => {
        originalList[index].point += point;
      });
    },
    compareHourlyWage(list, value, priority) {
      const comparable = [];
      const originalList = list;
      originalList.forEach(({ hourlyWage }, index) => comparable.push({ hourlyWage, index }));
      comparable.sort((a, b) => (value === 'high' ? b.hourlyWage - a.hourlyWage : a.hourlyWage - b.hourlyWage));
      comparable.forEach(({ hourlyWage }, i) => {
        if (i !== 0 && hourlyWage === comparable[i - 1].hourlyWage) {
          comparable[i].point = comparable[i - 1].point;
        } else {
          comparable[i].point = (list.length - i) * (4 - priority);
        }
      });
      comparable.forEach(({ index, point }) => {
        originalList[index].point += point;
      });
    },
    comparePeriod(list, value, priority) {
      const comparable = [];
      const originalList = list;
      originalList.forEach(({ periodWeight }, index) => comparable.push({ periodWeight, index }));
      comparable.sort((a, b) => (value === 'shortDay' ? a.periodWeight - b.periodWeight : b.periodWeight - a.periodWeight));
      comparable.forEach(({ periodWeight }, i) => {
        if (i !== 0 && periodWeight === comparable[i - 1].periodWeight) {
          comparable[i].point = comparable[i - 1].point;
        } else {
          comparable[i].point = (list.length - i) * (4 - priority);
        }
      });
      comparable.forEach(({ index, point }) => {
        originalList[index].point += point;
      });
    },
    compareWorkTime(list, value, priority) {
      const comparable = [];
      const originalList = list;
      originalList.forEach(({ workTime }, index) => comparable.push({ workTime, index }));
      comparable.sort((a, b) => (value === 'shortTime' ? a.workTime - b.workTime : b.workTime - a.workTime));
      comparable.forEach(({ workTime }, i) => {
        if (i !== 0 && workTime === comparable[i - 1].workTime) {
          comparable[i].point = comparable[i - 1].point;
        } else {
          comparable[i].point = (list.length - i) * (4 - priority);
        }
      });
      comparable.forEach(({ index, point }) => {
        originalList[index].point += point;
      });
    },
  },
  created() {
    this.$EventBus.$on('updateFilter', this.compare);
    this.jobLists = JSON.parse(localStorage.getItem('job-list')) || [];
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
    &:first-child {
      background-color: $main-color2 * 1.3;
    }
    &:last-child {
      border: none;
    }
  }
}

</style>
