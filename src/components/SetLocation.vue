<template>
  <transition name="modal">
    <div class="set-location">
      <div class="set-location__mask" @click="$emit('closeModal')"></div>
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
