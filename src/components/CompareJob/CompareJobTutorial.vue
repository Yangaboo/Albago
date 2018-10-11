<template>
  <transition name="modal">
    <div class="tutorial">
      <div class="tutorial__mask" @click="$emit('close-modal')"></div>
      <div class="modal">
        <div class="modal__exit">
          &times;
        </div>
        <div class="modal__to-left">&lt;</div>
        <div class="modal__to-right">&gt;</div>
        <div
          class="modal__explanation"
          v-for="(explanation, index) in explanations"
          :key="`ex${index}`">
          <img class="modal__diagram" :src="explanation.diagram"/>
          <div class="modal__caption">
            {{ explanation.caption }}
          </div>
        </div>
        <div class="modal__paging">
          <div
            class="modal__page"
            v-for="page in 4"
            :key="page"
            :class="{'modal__page--selected': current === page}">
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'compare-job-tutorial',
  data() {
    return {
      current: 0,
      explanations: [
        {
          diagram: '../../assets/tutorial_1',
          caption: '알바 정보 사이트에 들어가세요',
        },
        {
          diagram: '../../assets/tutorial_2',
          caption: '원하는 아르바이트를 선택해주세요',
        },
        {
          diagram: '../../assets/tutorial_3',
          caption: '알바고에 원하는 아르바이트 url를 넣어주세요',
        },
        {
          diagram: '../../assets/tutorial_4',
          caption: '비교버튼을 누르고 아르바이트를 비교해주세요',
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$z-index-create: 1;
$z-index-mask: 2;
$z-index-modal: 3;
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
  $name-padding-top: 30px;

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
  background-color: #fff;
  border-radius: $color-main;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
}
</style>
