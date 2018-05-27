import Vue from 'vue';
import Router from 'vue-router';
import Navigation from '../components/Navigation';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'navigation',
      component: Navigation,
    },
  ],
});
