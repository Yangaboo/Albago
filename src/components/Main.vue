<template>
  <div class="main">
    <main-bulletin class="main__main-bulletin"/>
    <div class="main__wrapper">
      <main-locate class="main__wrapper__main-locate"/>
      <main-part-time class="main__wrapper__main-part-time"/>
    </div>
  </div>
</template>

<script>
import MainBulletin from './Main/MainBulletin';
import MainLocate from './Main/MainLocate';
import MainPartTime from './Main/MainPartTime';

export default {
  name: 'main',
  components: {
    MainBulletin,
    MainLocate,
    MainPartTime,
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';

.main {
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
