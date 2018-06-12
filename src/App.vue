<template>
  <div id="app">
    <set-location v-if="isOnModal" @closeModal="isOnModal = false"/>
    <navigation/>
    <slideshow/>
    <router-view/>
  </div>
</template>

<script>
import Navigation from './components/Navigation';
import Slideshow from './components/Slideshow';
import SetLocation from './components/SetLocation';

export default {
  name: 'App',
  data() {
    return {
      isOnModal: false,
    };
  },
  components: {
    SetLocation,
    Navigation,
    Slideshow,
  },
  created() {
    this.$EventBus.$on('showModal', () => {
      this.isOnModal = true;
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

html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  outline: none;
  vertical-align: baseline;
  box-sizing: border-box;
}

a {
  text-decoration: none;
  color: inherit;
}

button {
  background-color: none;
  border: none;
  font-size: inherit;
}

article, aside, details, figcaption, figure,
footer, header, hgroup, menu, nav, section {
  display: block;
}

ol, ul {
  list-style: none;
}

blockquote, q {
  quotes: none;
}

blockquote:before, blockquote:after,
q:before, q:after {
  content: '';
  content: none;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
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
}
</style>
