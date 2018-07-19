<template>
  <div class="wrap">
    <slideshow/>
    <div class="main-container">
      <main-bulletin class="main-container__main-bulletin"/>
      <div class="main-container__wrapper">
        <main-locate class="main-container__wrapper__main-locate"/>
        <main-part-time class="main-container__wrapper__main-part-time"/>
      </div>
    </div>
  </div>
</template>

<script>
import Slideshow from './Common/Slideshow';
import MainBulletin from './MainContainer/MainBulletin';
import MainLocate from './MainContainer/MainLocate';
import MainPartTime from './MainContainer/MainPartTime';

export default {
  name: 'main-container',
  components: {
    Slideshow,
    MainBulletin,
    MainLocate,
    MainPartTime,
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';

.main-container {
  background-color: #494f5c;
  padding: 85px 125px;
  display: flex;
  justify-content: space-around;
  @include e('main-bulletin') {
    flex: 1;
    display: inline-block;
    margin-right: 5%;
  }
  @include e('wrapper') {
    flex: 1;
    @include e('main-locate') {
      margin-bottom: 70px;
    }
  }
}
</style>
