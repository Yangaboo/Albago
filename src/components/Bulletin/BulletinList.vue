<template>
  <ul class="bulletin-list">
    <li class="bulletin-list__head">
      <div class="bulletin-list__head__item bulletin-list__head__number">번호</div>
      <div class="bulletin-list__head__item bulletin-list__head__title">제목</div>
      <div class="bulletin-list__head__item bulletin-list__head__category">카테고리</div>
      <div class="bulletin-list__head__item bulletin-list__head__date">작성일</div>
      <div class="bulletin-list__head__item bulletin-list__head__numberOfGood">좋아요</div>
      <div class="bulletin-list__head__item bulletin-list__head__numberOfBad">싫어요</div>
    </li>
    <li class="bulletin-list__link" v-for="bulletin in bulletins" :key="bulletin.number">
      <div class="bulletin-list__link__item bulletin-list__link__number">
        {{ bulletin.number }}
      </div>
      <div class="bulletin-list__link__item bulletin-list__link__title">
        {{ bulletin.title }}
      </div>
      <div class="bulletin-list__link__item bulletin-list__link__category">
        {{ bulletin.category }}
      </div>
      <div class="bulletin-list__link__item bulletin-list__link__date">
        {{ bulletin.date }}
      </div>
      <div class="bulletin-list__link__item bulletin-list__link__numberOfGood">
        {{ bulletin.numberOfGood }}
      </div>
      <div class="bulletin-list__link__item bulletin-list__link__numberOfBad">
        {{ bulletin.numberOfBad }}
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'bulletin-list',
  data() {
    return {
      bulletins: [
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
        {
          number: 1,
          title: '대덕소프트웨어마이스터고등학교 교사 아르바이트 후기',
          category: '교육',
          date: '2014-04-28',
          numberOfGood: 100,
          numberOfBad: 200,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.bulletin-list {
  height: 506px;
  margin: {
    top: 10px;
  }
  border: {
    top: 2px solid white;
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
  @include e('head') {
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
  @include e('link') {
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
}
</style>
