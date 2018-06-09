import Vue from 'vue';
import Router from 'vue-router';
import Main from '../components/Main';
import Bulletin from '../components/Bulletin';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'main',
      component: Main,
    },
    {
      path: '/bulletin',
      name: 'bulletin',
      component: Bulletin,
    },
  ],
});
