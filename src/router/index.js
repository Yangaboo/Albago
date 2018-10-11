import Vue from 'vue';
import Router from 'vue-router';
import Bulletin from '../components/Bulletin';
import CompareJob from '../components/CompareJob';
import Post from '../components/Post';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'compare-job',
      component: CompareJob,
    },
    {
      path: '/bulletin',
      name: 'bulletin',
      component: Bulletin,
    },
    {
      path: '/post',
      name: 'post',
      component: Post,
    },
  ],
});
