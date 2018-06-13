<template>
  <nav class="navigation">
    <a href="" class="navigation__logo-link">
      <img src="" alt="알바고">
    </a>
    <ul class="navigation__list">
      <li class="navigation__list__item">
        <router-link class="navigation__list__item__link"
          :to="{path: '/'}">홈</router-link>
      </li>
      <li class="navigation__list__item">
        <router-link class="navigation__list__item__link"
          :to="{path: '/bulletin', query: {page: 1}}">게시판</router-link>
      </li>
      <li class="navigation__list__item">
        <a class="navigation__list__item__link"
          @click="$EventBus.$emit('showModal')">위치 설정</a>
      </li>
      <li class="navigation__list__item">
        <router-link class="navigation__list__item__link"
          :to="{path: '/parttime'}">아르바이트 찾기</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'navigation',
};
</script>

<style lang="scss" scoped>
@import '../styles/setting.scss';

.navigation {
  height: 70px;
  padding: 0 25px;
  background-color: #fff;
  position: relative;
  @include e('logo-link') {
    display: inline-block;
    padding: 25px 0;
  }
  @include e('list') {
    float: right;
    font-size: 0;
    @include e('item') {
      display: inline-block;
      @include e('link') {
        display: block;
        font-size: 17px;
        line-height: 20px;
        height: 70px;
        padding: 25px 20px;
        color: #636d81;
        font-weight: 500;
        cursor: pointer;
        &:hover {
          background-color: #e6e6e6;
        }
      }
    }
  }
}
</style>
