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
        {{ bulletin.postId }}
      </div>
      <a :href="`#/post?id=${bulletin.postId}`" class="link__item link__title">
        {{ bulletin.title }}
      </a>
      <div class="link__item link__category">
        {{ categoryObj[categoryArr[bulletin.cat_id]].text }}
      </div>
      <div class="link__item link__date">
        {{ formatDate(bulletin.createdDate) }}
      </div>
      <div class="link__item link__good">
        {{ bulletin.favor }}
      </div>
      <div class="link__item link__bad">
        {{ bulletin.hate }}
      </div>
    </li>
  </ul>
</template>

<script>
import { categoryObj, categoryArr } from '../../constants/category';

export default {
  name: 'bulletin-list',
  props: {
    bulletins: Array,
  },
  data() {
    return {
      categoryObj,
      categoryArr,
    };
  },
  methods: {
    formatDate(dateArr) {
      return `${dateArr[0]}.${dateArr[1]}.${dateArr[2]}`;
    },
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
    width: 5%;
    text-align: center;
  }
  @include e('title') {
    flex: 1;
    padding-left: 20px;
  }
  @include e('category') {
    width: 20%;
  }
  @include e('date') {
    width: 10%;
  }
}
.link {
  @extend %list-item;
  @include e('item') {
    @extend %flex-item;
    width: 5%;
    text-align: center;
  }
  @include e('title') {
    flex: 1;
    padding-left: 20px;
    text-align: left;
  }
  @include e('category') {
    width: 20%;
  }
  @include e('date') {
    width: 10%;
  }
}
</style>
