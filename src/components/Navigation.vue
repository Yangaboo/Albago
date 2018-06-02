<template>
  <nav class="navigation">
    <a href="" class="navigation__logo-link">
      <img src="" alt="알바고">
    </a>
    <ul class="navigation__list">
      <li class="navigation__list__item">
        <a href="" class="navigation__list__item__link">홈</a>
      </li>
      <li class="navigation__list__item">
        <a href="" class="navigation__list__item__link">게시판</a>
      </li>
      <li class="navigation__list__item">
        <a href="" class="navigation__list__item__link">위치 설정</a>
      </li>
      <li class="navigation__list__item">
        <a href="" class="navigation__list__item__link">아르바이트 찾기</a>
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
  padding: 25px;
  background-color: white;
  position: relative;
  @include e('list') {
    float: right;
    font-size: 0;
    @include e('item') {
      display: inline-block;
      @include e('link') {
        font-size: 17px;
        line-height: 21px;
        padding: 0 20px;
        color: #636d81;
        font-weight: 500;
      }
    }
  }
}
</style>
