<template>
  <div class="slideshow" :class="currentIndex">
    <div class="slideshow__slide">
      <div class="slideshow__slide__job-introduction">
        gs25 아르바이트생 구합니다. 남자 1 여자 1<br>
        주간,주말 (공휴일 제외)<br>
        시급 : 8200원
      </div>
    </div>
    <div class="slideshow__slide">
      <div class="slideshow__slide__job-introduction">
        gs25 아르바이트생 구합니다. 남자 1 여자 1<br>
        주간,주말 (공휴일 제외)<br>
        시급 : 8200원
      </div>
    </div>
    <div class="slideshow__slide">
      <div class="slideshow__slide__job-introduction">
        gs25 아르바이트생 구합니다. 남자 1 여자 1<br>
        주간,주말 (공휴일 제외)<br>
        시급 : 8200원
      </div>
    </div>
    <div class="slideshow__slide">
      <div class="slideshow__slide__job-introduction">
        gs25 아르바이트생 구합니다. 남자 1 여자 1<br>
        주간,주말 (공휴일 제외)<br>
        시급 : 8200원
      </div>
    </div>
    <div class="sliding-index">
      <div class="sliding-index__dot" v-for="dot in 4" :key="dot"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'slide-show',
  data() {
    return {
      currentIndex: 'slideshow--1',
      temp: 0,
    };
  },
  created() {
    setInterval(() => {
      this.temp = (this.temp + 1) % 4;
      this.currentIndex = 'slideshow--'.concat(String(this.temp + 1));
    }, 3000);
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

@mixin active-dot($number) {
  &--#{$number} .sliding-index__dot:nth-child(#{$number}) {
    background-color: #000;
  }
}
@mixin slide-position($number, $left, $is-block) {
  & .slideshow__slide:nth-child(#{$number}) {
    left: $left;
    display: if($is-block, block, none);
  }
}

.slideshow {
  background-color: pink;
  height: 224px;
  text-align: center;
  position: relative;
  overflow: hidden;
  @include e('slide') {
    width: 100%;
    padding-top: 52px;
    height: 100%;
    position: absolute;
    top: 0;
    transition: left 1s;
    background-image: url('../../assets/picture.png');
    background-size: cover;
    background-position: center -300px;
    @include e('job-introduction') {
      font-size: 20px;
      color: white;
      line-height: 35px;
      height: 35px;
    }
  }
  .sliding-index {
    position: absolute;
    width: 100%;
    bottom: 20px;
    padding-top: 30px;
    @include e('dot') {
      border-radius: 100%;
      width: 8.3px;
      height: 8.3px;
      background-color: #ffffff;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
      display: inline-block;
      margin: 0 5px;
    }
  }
  @include m('1') {
    @include slide-position(1, 0, true);
    @include slide-position(2, -100%, true);
    @include slide-position(3, 100%, false);
    @include slide-position(4, 100%, true);
  }
  @include m('2') {
    @include slide-position(4, 0, true);
    @include slide-position(1, -100%, true);
    @include slide-position(2, 100%, false);
    @include slide-position(3, 100%, true);
  }
  @include m('3') {
    @include slide-position(3, 0, true);
    @include slide-position(4, -100%, true);
    @include slide-position(1, 100%, false);
    @include slide-position(2, 100%, true);
  }
  @include m('4') {
    @include slide-position(2, 0, true);
    @include slide-position(3, -100%, true);
    @include slide-position(4, 100%, false);
    @include slide-position(1, 100%, true);
  }
  @for $i from 1 through 4 {
    @include active-dot($i);
  }
}
</style>
