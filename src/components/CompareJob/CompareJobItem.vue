<template>
    <!--
      TODO
      job style
      job delete function
    -->
    <div class="job">
      <div class="job__distance">
        {{ distance }}
      </div>
      <div class="job__name">
        {{ name }}
      </div>
      <div class="job__period">
        {{ period }}
      </div>
      <div class="job__work-time">
        {{ workTime }}
      </div>
      <div class="job__hourly-wage">
        {{ hourlyWage }}
      </div>
      <a class="job__link" :href="href"></a>
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
  background-color: $main-color2;
}

</style>
