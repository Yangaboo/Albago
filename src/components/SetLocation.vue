<template>
  <transition name="modal">
    <div class="set-location" @click="$emit('closeModal')">
    </div>
  </transition>
</template>

<script>
export default {
  name: 'setlocation',
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';

.set-location {
  background-color: rgba(0, 0, 0, 0.5);
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1;
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
