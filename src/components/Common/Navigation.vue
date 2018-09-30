<template>
  <nav class="nav">
    <div class="container">
      <router-link :to="{path: '/'}" class="container__logo-link">
        <img class="container__logo-link__img"
          src="../../assets/logo.png"
          alt="알바고">
      </router-link>
      <ul class="container__list">
        <li class="container__item">
          <router-link class="container__item__link"
            :to="{path: '/'}">홈</router-link>
        </li>
        <li class="container__item">
          <router-link class="container__item__link"
            :to="{path: '/bulletin', query: {page: 1, category: 'ALL'}}">게시판</router-link>
        </li>
        <li class="container__item">
          <a class="container__item__link"
            @click="$EventBus.$emit('showModal')">위치 설정</a>
        </li>
        <li class="container__item">
          <router-link class="container__item__link"
            :to="{path: '/compare'}">아르바이트 비교</router-link>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  name: 'navigation',
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting.scss';

.nav {
  position: absolute;
  top: 0;
  left: 0;
  height: 70px;
  background-color: #fff;
  width: 100%;
}

.container {
  position: relative;
  width: 1670px;
  margin: 0 auto;
  @include e('logo-link') {
    display: inline-block;
    padding: 20px 0;
    @include e('img') {
      height: 30px;
    }
  }
  @include e('list') {
    position: absolute;
    right: 0;
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
