<template>
  <transition name="modal">
    <div class="tutorial">
      <div class="tutorial__mask" @click="$emit('close-modal')"></div>
      <div class="modal">
        <div class="modal__exit" @click="$emit('close-modal')">
          &times;
        </div>
        <div
          class="modal__to-left"
          @click="left"
          v-show="current > 0">
          &lang;
        </div>
        <div
          class="modal__to-right"
          @click="right"
          v-show="current < explanations.length - 1">
          &rang;
        </div>
        <transition name="explanation">
          <div
            class="modal__explanation"
            v-for="(explanation, index) in explanations"
            :key="`ex${index}`"
            :style="{ backgroundImage: `url(${explanation.diagram})`}"
            v-if="current === index">
            <div class="modal__caption">
              {{ explanation.caption }}
            </div>
          </div>
        </transition>
        <div class="modal__paging">
          <div
            class="modal__page"
            v-for="page in 4"
            :key="page"
            :class="{'modal__page--selected': current === page - 1}">
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import tutorialImg1 from '../assets/tutorial_1.png';
import tutorialImg2 from '../assets/tutorial_2.png';
import tutorialImg3 from '../assets/tutorial_3.png';
import tutorialImg4 from '../assets/tutorial_4.png';

export default {
  name: 'tutorial',
  data() {
    return {
      current: 0,
      explanations: [
        {
          diagram: tutorialImg1,
          caption: '알바 정보 사이트에 들어가세요',
        },
        {
          diagram: tutorialImg2,
          caption: '원하는 아르바이트를 선택해주세요',
        },
        {
          diagram: tutorialImg3,
          caption: '알바고에 원하는 아르바이트 url를 넣어주세요',
        },
        {
          diagram: tutorialImg4,
          caption: '비교버튼을 누르고 아르바이트를 비교해주세요',
        },
      ],
    };
  },
  methods: {
    left() {
      this.current -= 1;
    },
    right() {
      this.current += 1;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$z-index-create: 1;
$z-index-mask: 2;
$z-index-modal: 3;
$z-index-explanation: 4;
$z-index-move-page: 5;
$color-main: #494f5c;

.tutorial {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: $z-index-create;
  @include e('mask') {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: $z-index-mask;
  }
}

.modal {
  $width-modal: 500px;
  $height-modal: 650px;
  $radius: 10px;

  position: absolute;
  z-index: $z-index-modal;
  left: 50%;
  top: 50%;
  margin: {
    left: -($width-modal / 2);
    top: -($height-modal / 2);
  }
  width: $width-modal;
  height: $height-modal;
  background-color: $color-main;
  border-radius: $radius;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
  color: #fff;
  overflow-x: hidden;
  @include e('exit') {
    position: absolute;
    top: 0;
    right: 0;
    height: 50px;
    width: 50px;
    text-align: center;
    line-height: 50px;
    font-size: 30px;
  }
  %move-page {
    z-index: $z-index-move-page;
    position: absolute;
    height: 250px;
    width: 100px;
    top: 170px;
    font-size: 30px;
    line-height: 250px;
    cursor: pointer;
    text-align: center;
  }
  @include e('to-left') {
    @extend %move-page;
    left: 0;
  }
  @include e('to-right') {
    @extend %move-page;
    right: 0;
  }
  @include e('explanation') {
    width: 100%;
    height: 290px;
    background-size: 250px 250px;
    background-position: center 0;
    background-repeat: no-repeat;
    z-index: $z-index-explanation;
    position: absolute;
    top: 170px;
  }
  @include e('caption') {
    position: absolute;
    bottom: 0;
    width: $width-modal;
    left: 50%;
    margin-left: -($width-modal / 2);
    font-size: 20px;
    text-align: center;
  }
  @include e('paging') {
    position: absolute;
    display: flex;
    justify-content: center;
    width: 100%;
    bottom: 50px;
  }
  @include e('page') {
    width: 15px;
    height: 15px;
    margin: 0 15px;
    background-color: #686868;
    border-radius: 100%;
    @include m('selected') {
      background-color: #fff;
    }
  }
}

.explanation {
  &-enter-active, &-leave-active {
    transition: transform 0.5s;
  }
  &-enter {
    transform: translateX(100%);
  }
  &-enter-to, &-leave {
    transform: translateX(0);
  }
  &-leave-to {
    transform: translateX(-100%);
  }
}
</style>
