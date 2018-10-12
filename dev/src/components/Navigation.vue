<template>
  <nav class="nav">
    <div :to="{path: '/'}" class="nav__logo-link">
      <img class="nav__logo-link__img"
        src="../assets/logo.png"
        alt="알바고">
    </div>
    <ul class="nav__list">
      <li class="nav__item">
        <a
          class="nav__item__link"
          @click="$EventBus.$emit('showSetLocation')">위치 설정</a>
      </li>
      <li class="nav__item">
        <a
          class="nav__item__link"
          @click="$EventBus.$emit('showTutorial')">도움말</a>
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

.nav {
  height: 70px;
  background-color: #fff;
  width: 100%;
  padding-left: 125px;
  position: relative;
  @include e('logo-link') {
    display: inline-block;
    padding: 20px 0;
    @include e('img') {
      height: 30px;
    }
  }
  @include e('list') {
    position: absolute;
    right: 125px;
    top: 0;
    font-size: 0;
  }
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
</style>
