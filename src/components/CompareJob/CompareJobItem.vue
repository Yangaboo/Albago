<template>
    <!--
      TODO
      job delete function
    -->
    <div class="job">
      <div class="job__tags">
        <div
          class="job__tag"
          v-for="tag in tags"
          :key="tag">
          {{ tag }}
        </div>
      </div>
      <div class="job__days">
        <div
          class="job__day"
          v-for="(day, index) in reassignedDays"
          :key="`day${index}`"
          :class="{'on':day }">
          {{ day }}
        </div>
      </div>
      <div class="job__distance">
        {{ unittedDistance }}
      </div>
      <a class="job__name" :href="href">
        {{ name }}
      </a>
      <div class="job__period">
        {{ periodSring }}
      </div>
      <div class="job__work-time">
        {{ workTimeString }}
      </div>
      <div class="job__hourly-wage">
        {{ hourlyWage.toLocaleString() }}
        <span style="font-size: 0.5em">원</span>
      </div>
      <button class="job__delete-btn" @click="$emit('delete')">
        제거
      </button>
    </div>
</template>

<script>
export default {
  name: 'compare-job-item',
  props: {
    distance: Number,
    name: String,
    startDay: String,
    endDay: String,
    startTime: Number,
    endTime: Number,
    hourlyWage: Number,
    href: String,
    days: Array,
    tags: Array,
  },
  data() {
    return {
      dailyConstant: ['월', '화', '수', '목', '금', '토', '일'],
    };
  },
  computed: {
    unittedDistance() {
      return this.distance >= 1000 ?
        `${Math.floor(this.distance / 1000)}km` :
        `${this.distance}m`;
    },
    reassignedDays() {
      const arr = ['', '', '', '', '', '', ''];
      this.days.forEach((day) => {
        arr[day] = this.dailyConstant[day];
      });
      return arr;
    },
    workTimeString() {
      const start = this.startTime;
      const end = this.endTime;
      const workTime = start > end ? (end + 24) - end : end - start;
      return `${workTime}시간 (${start}시 ~ ${end}시)`;
    },
    // period: '17일 근무 (2018.04.01 ~ 2018.04.18)',
    periodSring() {
      const start = new Date(this.startDay);
      const end = new Date(this.endDay);
      const dateConst = 1000 * 60 * 60 * 24;
      return `${Math.floor((end - start) / dateConst)}일 근무 (${this.startDay} ~ ${this.endDay})`;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

.job {
  $tags-height: 20px;
  $job-padding: 20px;
  $distance-width: 50px;
  $btn-width: 50px;

  background-color: $main-color2;
  padding: {
    left: $job-padding + $distance-width;
    top: $job-padding + $tags-height;
    bottom: $job-padding;
    right: $job-padding;
  };
  position: relative;
  color: #fff;
  display: flex;
  flex-wrap: wrap;
  @include e('tags') {
    top: $job-padding / 2;
    left: $job-padding;
    height: $tags-height;
    width: 50%;
    position: absolute;
    display: flex;
  }
  @include e('tag') {
    background-color: #fff;
    border-radius: 3px;
    margin-right: 5px;
    padding: 0 5px;
    color: #000;
    text-align: center;
    line-height: $tags-height;
  }
  @include e('days') {
    top: $job-padding / 2;
    left: 50%;
    height: $tags-height;
    width: 50%;
    position: absolute;
    display: flex;
  }
  @include e('day') {
    background-color: #fff;
    width: 20px;
    border-radius: 3px;
    margin-right: 5px;
    color: #000;
    text-align: center;
    line-height: $tags-height;
    &.on {
      background-color: $point-color1;
    }
  }
  @include e('distance') {
    color: $point-color1;
    position: absolute;
    top: $job-padding + $job-padding;
    left: $job-padding;
    font-size: 14px;
    width: $distance-width;
  }
  @include e('name') {
    padding: {
      right: $btn-width;
    }
    font-size: 27px;
    width: 100%;
    padding-bottom: 13px;
    &:hover {
      text-decoration: underline #fff;
      &::after {
        content: '〉';
      }
    }
  }

  %sub-element {
    font-size: 15px;
    background-color: $main-color1;
    box-shadow: inset 0px 2px 2px rgba(0, 0, 0, 0.158);
    padding: 10px;
    border-radius: 3px;
  }
  @include e('period') {
    @extend %sub-element;
    margin-right: 10%;
    width: 40%;
  }
  @include e('work-time') {
    @extend %sub-element;
    width: 20%;
  }
  @include e('hourly-wage') {
    color: $point-color1;
    font-weight: bold;
    position: absolute;
    bottom: $job-padding;
    right: $job-padding;
    font-size: 35px;
  }
  @include e('delete-btn') {
    position: absolute;
    // top: $job-padding;
    // right: $job-padding;
    top: 0;
    right: 0;
    width: $btn-width;
    font-size: 16px;
    line-height: 20px;
    background-color: $point-color1;
    border-bottom-left-radius: 10px;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.253);
  }
}

</style>
