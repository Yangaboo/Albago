<template>
  <transition name="modal">
    <div class="set-location">
      <div class="set-location__mask" @click="$emit('closeModal')"></div>
      <div class="set-location__modal">
        <h2 class="set-location__modal__name">
          위치 변경하기
          <button class="set-location__modal__name__close-button">
            &times;
          </button>
        </h2>
        <div class="set-location__modal__main">
          <div class="set-location__modal__main__search">
            <input type="search" class="set-location__modal__main__search__input-text">
            <button class="set-location__modal__main__search__search-button">
              검색
            </button>
          </div>
          <div class="set-location__modal__main__map"></div>
          <div class="set-location__modal__main__wrapper">
            <ul class="set-location__modal__main__wrapper__result-list">
              <li class="set-location__modal__main__wrapper__result-list__item">
                <div class="set-location__modal__main__wrapper__result-list__item__check"></div>
              </li>
            </ul>
            <button class="set-location__modal__main__wrapper__set-button">
              해당 위치로 설정
            </button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'set-location',
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$z-index-set-location: 1;
$z-index-mask: 2;
$z-index-modal: 3;

.set-location {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: $z-index-set-location;
  @include e('mask') {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: $z-index-mask;
  }
  @include e('modal') {
    $width-modal: 1100px;
    $height-modal: 700px;
    $radius: 5px;
    $side-padding: 60px;
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
    border-radius: $radius;
    @include e('name') {
      $padding-top: 30px;
      width: 100%;
      background-color: #494f5c;
      height: 80px;
      padding: {
        top: $padding-top;
        bottom: 20px;
        left: $side-padding;
        right: $side-padding;
      }
      border-top-left-radius: $radius;
      border-top-right-radius: $radius;
      font-size: 22px;
      font-weight: bold;
      line-height: 30px;
      color: white;
      @include e('close-button') {
        position: absolute;
        top: $padding-top;
        right: $side-padding;
        font-size: 30px;
        font-weight: lighter;
        line-height: inherit;
        color: inherit;
        background: none;
        border: none;
      }
    }
  }
}
.modal {
  &-enter-active, &-leave-active {
    transition: opacity 0.5s ease;
  }
  &-enter, &-leave-to {
    opacity: 0;
  }
}
</style>
