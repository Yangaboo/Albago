<template>
  <ul class="list">
    <li class="head">
      <div class="head__item head__number">번호</div>
      <div class="head__item head__title">제목</div>
      <div class="head__item head__category">카테고리</div>
      <div class="head__item head__date">작성일</div>
      <div class="head__item head__good">좋아요</div>
      <div class="head__item head__bad">싫어요</div>
    </li>
    <li class="link" v-for="bulletin in bulletins" :key="bulletin.number">
      <div class="link__item link__number">
        {{ bulletin.number }}
      </div>
      <a :href="`#/post/${bulletin.number}`" class="link__item link__title">
        {{ bulletin.title }}
      </a>
      <div class="link__item link__category">
        {{ bulletin.category }}
      </div>
      <div class="link__item link__date">
        {{ bulletin.date }}
      </div>
      <div class="link__item link__good">
        {{ bulletin.good }}
      </div>
      <div class="link__item link__bad">
        {{ bulletin.bad }}
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'bulletin-list',
  props: {
    bulletins: Array,
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.list {
  height: 506px;
  margin: {
    top: 10px;
  }
  border: {
    top: 2px solid white;
  }
}

%list-item {
  width: 100%;
  display: flex;
}
%flex-item {
  font-size: 15px;
  line-height: 19px;
  height: 46px;
  border-bottom: 1px solid #fff;
  padding: {
    top: 13px;
    bottom: 13px;
  }
}

.head {
  @extend %list-item;
  @include e('item') {
    @extend %flex-item;
    width: 10%;
    text-align: center;
  }
  @include e('title') {
    width: 50%;
  }
}
.link {
  @extend %list-item;
  @include e('item') {
    @extend %flex-item;
    width: 10%;
    text-align: center;
  }
  @include e('title') {
    width: 50%;
    text-align: left;
  }
}
</style>
