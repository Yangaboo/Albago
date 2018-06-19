import Vue from 'vue';
import Router from 'vue-router';
import MainContainer from '../components/MainContainer';
import Bulletin from '../components/Bulletin';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'main-container',
      component: MainContainer,
    },
    {
      path: '/bulletin',
      name: 'bulletin',
      component: Bulletin,
    },
  ],
});
