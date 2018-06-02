<template>
  <div class="slideshow" :class="currentIndex">
    <div class="slideshow__slide" :class="slidePosition1">
      <div class="slideshow__slide__job-introduction">A</div>
    </div>
    <div class="slideshow__slide" :class="slidePosition2">
      <div class="slideshow__slide__job-introduction">B</div>
    </div>
    <div class="slideshow__slide" :class="slidePosition3">
      <div class="slideshow__slide__job-introduction">C</div>
    </div>
    <div class="slideshow__slide" :class="slidePosition4">
      <div class="slideshow__slide__job-introduction">D</div>
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
      slidePosition1: 'slideshow__slide--1',
      slidePosition2: 'slideshow__slide--2',
      slidePosition3: 'slideshow__slide--3',
      slidePosition4: 'slideshow__slide--4',
      currentIndex: 'slideshow--1',
      temp: 0,
    };
  },
  created() {
    setInterval(() => {
      for (let i = 1; i <= 4; i += 1) {
        const positionProperty = 'slidePosition'.concat(String(i));
        let slideNumber = ((this.temp + i) % 4) + 1;
        slideNumber = String(slideNumber);
        this[positionProperty] = 'slideshow__slide--'.concat(slideNumber);
      }
      this.temp = (this.temp + 1) % 4;
      this.currentIndex = 'slideshow--'.concat(String(this.temp + 1));
    }, 3000);
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting.scss';
@mixin active-dot($number) {
  &--#{$number} .sliding-index__dot:nth-child(#{$number}) {
    background-color: #000;
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
    position: absolute;
    top: 0;
    transition: left 1s;
    @include m('1') {
      left: 0;
      display: block;
    }
    @include m('2') {
      left: -100%;
      display: block;
    }
    @include m('3') {
      left: 100%;
      display: none;
    }
    @include m('4') {
      left: 100%;
      display: block;
    }
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
  @for $i from 1 through 4 {
    @include active-dot($i);
  }
}
</style>
