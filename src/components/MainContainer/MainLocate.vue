<template>
  <div class="main-locate">
    <h3 class="section-name">위치 설정</h3>
    <span class="main-locate__current-location">
      {{ currentLocation }}
    </span>
    <button class="main-locate__select-location" @click="$EventBus.$emit('showModal')">
      위치 변경하기
    </button>
  </div>
</template>

<script>
export default {
  name: 'main-locate',
  data() {
    return {
      currentLocation: '대전광역시 동구 산내로 1375',
    };
  },
  created() {
    if (window.localStorage.getItem('address_name')) {
      this.currentLocation = window.localStorage.getItem('address_name');
    }
    this.$EventBus.$on('setLocate', () => {
      this.currentLocation = window.localStorage.getItem('address_name');
    });
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.main-locate {
  position: relative;
  height: 200px;
  background-color: #636d81;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
  font-size: 20px;
  line-height: 25px;
  border-radius: 5px;
  padding: {
    left: 110px;
    top: 60px;
  }
  @include e('current-location') {
    color: #fff;
  }
  @include e('select-location') {
    position: absolute;
    bottom: 0;
    left: 0;
    height: 71px;
    padding: 23px;
    background-color: #fff;
    color: #636d81;
    font-weight: bold;
    width: 100%;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    cursor: pointer;
  }
}
</style>
