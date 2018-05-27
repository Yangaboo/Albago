<template>
  <nav class="navigation">
    <a href="" class="navigation__">
      <img src="" alt="알바고">
    </a>
    <ul class="navigation__list">
      <li for="navigation__list__item">
        <a href="navigation__list__item__link">홈</a>
      </li>
      <li for="navigation__list__item">
        <a href="navigation__list__item__link">게시판</a>
      </li>
      <li for="navigation__list__item">
        <a href="navigation__list__item__link">위치 설정</a>
      </li>
      <li for="navigation__list__item">
        <a href="navigation__list__item__link">아르바이트 찾기</a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'navigation',
};
</script>

<style lang="scss">
  .navigation {
    height: 70px;
    padding: 25px;
  }
</style>
