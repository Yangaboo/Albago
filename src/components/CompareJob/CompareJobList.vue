<template>
  <div class="list"></div>
</template>

<script>
export default {
  name: 'compare-job-list',
  data() {
    return {
      jobLists: [{
        name: 'GS25 편의점 아르바이트',
        distance: '16km',
        period: '17일 근무 (2018.04.01 ~ 2018.04.18)',
        workTime: '5시간 (17시 ~ 22시)',
        hourlyWage: '8,690원',
      }],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

</style>
