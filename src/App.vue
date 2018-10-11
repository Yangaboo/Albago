<template>
  <div id="app">
    <set-location v-if="onSetLocationModal" @close-modal="onSetLocationModal = false"/>
    <tutorial v-if="onTutorialModal" @close-modal="onTutorialModal = false"/>
    <router-view/>
  </div>
</template>

<script>
import SetLocation from './components/SetLocation';
import Tutorial from './components/Tutorial';

export default {
  name: 'App',
  data() {
    return {
      onSetLocationModal: false,
      onTutorialModal: false,
    };
  },
  components: {
    SetLocation,
    Tutorial,
  },
  created() {
    if (!localStorage.getItem('isPassedTutorial')) {
      this.onTutorialModal = true;
      localStorage.setItem('isPassedTutorial', true);
    }
    this.$EventBus.$on('showSetLocation', () => {
      this.onSetLocationModal = true;
    });
    this.$EventBus.$on('showTutorial', () => {
      this.onTutorialModal = true;
    });
  },
};
</script>

<style lang="scss">
// css initialize
body {
  width: 100%;
  height: 100%;
  line-height: 1;
  font-family: 'Roboto', sans-serif;
}

html,
body,
div,
span,
h1, h2, h3, h4, h5, h6,
p,
a,
ol, ul, li,
nav,
header,
section,
a {
  margin: 0;
  padding: 0;
  border: 0;
  outline: none;
  vertical-align: baseline;
  box-sizing: border-box;
  text-decoration: none;
  color: inherit;
}

button,
input,
input::-webkit-input-placeholder,
label {
  background-color: none;
  border: none;
  font-size: inherit;
  outline: none;
  box-sizing: border-box;
}

ol, ul {
  list-style: none;
}

#app {
  min-width: 1670px;
}
// common style
.section-name {
  font-size: 20px;
  height: 24px;
  line-height: 24px;
  font-weight: 500;
  color: white;
  position: absolute;
  top: -45px;
  left: 0;
}
</style>
