<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style lang="scss">
$primary-color: #333;

body {
  background-color: $primary-color;
}
</style>
