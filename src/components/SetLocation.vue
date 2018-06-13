<template>
  <transition name="modal">
    <div class="set-location">
      <div class="set-location__mask" @click="$emit('closeModal')"></div>
      <div class="set-location__modal">
        <header class="set-location__modal__header">
          <h2 class="set-location__modal__header__name">
            위치 변경하기
          </h2>
          <button class="set-location__modal__header__close-button">
            &times;
          </button>
        </header>
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

.set-location {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
  @include e('mask') {
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
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
