<template>
  <div class="set-location" @click="$emit('closeModal')">
  </div>
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
</style>
