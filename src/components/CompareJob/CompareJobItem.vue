<template>
    <!--
      TODO
      job delete function
    -->
    <div class="job">
      <div class="job__distance">
        {{ distance }}
      </div>
      <a class="job__name" :href="href">
        {{ name }}
      </a>
      <div class="job__period">
        {{ period }}
      </div>
      <div class="job__work-time">
        {{ workTime }}
      </div>
      <div class="job__hourly-wage">
        {{ hourlyWage }}
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
    distance: String,
    name: String,
    period: String,
    workTime: String,
    hourlyWage: String,
    href: String,
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

.job {
  $job-padding: 20px;
  $distance-width: 50px;
  $btn-width: 50px;

  background-color: $main-color2;
  padding: {
    left: $job-padding + $distance-width;
    top: $job-padding;
    bottom: $job-padding;
    right: $job-padding;
  };
  position: relative;
  color: #fff;
  display: flex;
  flex-wrap: wrap;
  @include e('distance') {
    color: $point-color1;
    position: absolute;
    top: $job-padding;
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
    bottom: $job-padding / 2;
    right: $job-padding / 2;
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
