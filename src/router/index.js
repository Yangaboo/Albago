import Vue from 'vue';
import Router from 'vue-router';
import MainContainer from '../components/MainContainer';
import Bulletin from '../components/Bulletin';
import CompareJob from '../components/CompareJob';
import Post from '../components/Post';

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
    {
      path: '/compare',
      name: 'compare-job',
      component: CompareJob,
    },
    {
      path: '/post',
      name: 'post',
      component: Post,
    }
  ],
});
