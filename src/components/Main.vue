<template>
  <main class="main">
    <main-job class="main__main-job"/>
    <main-post/>
    <main-locate/>
  </main>
</template>

<script>
import MainJob from '@/components/Main/MainJob';
import MainPost from '@/components/Main/MainPost';
import MainLocate from '@/components/Main/MainLocate';

export default {
  name: 'main',
  components: {
    MainJob,
    MainPost,
    MainLocate,
  },
};
</script>

<style lang="scss">
@import '../styles/setting';

.main {
  background-color: #494f5c;
}
</style>
