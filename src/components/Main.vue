<template>
  <div class="main">
    <main-post class="main__main-post"/>
    <div class="main__wrapper">
      <main-locate class="main__wrapper__main-locate"/>
      <main-job class="main__wrapper__main-job"/>
    </div>
  </div>
</template>

<script>
import MainPost from '@/components/Main/MainPost';
import MainLocate from '@/components/Main/MainLocate';
import MainJob from '@/components/Main/MainJob';

export default {
  name: 'main',
  components: {
    MainPost,
    MainLocate,
    MainJob,
  },
};
</script>

<style lang="scss">
@import '../styles/setting';

.main {
  background-color: #494f5c;
  padding: 85px 125px;
  display: flex;
  justify-content: space-around;
  @include e('main-post') {
    flex-grow: 1;
    display: inline-block;
    margin-right: 5%;
  }
  @include e('wrapper') {
    flex-grow: 1;
    @include e('main-locate') {
      margin-bottom: 70px;
    }
    @include e('main-job') {
    }
  }
}
</style>
