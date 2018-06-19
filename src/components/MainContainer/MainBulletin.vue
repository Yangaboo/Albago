<template>
  <div class="main-bulletin">
    <h3 class="section-name">게시판</h3>
    <ul>
      <li class="main-bulletin__list-item" v-for="(bulletin, index) in bulletins" :key="index">
        <a href="" class="main-bulletin__list-item__link">
          <div class="main-bulletin__list-item__link__title">
            {{ bulletin.title }}
          </div>
          <div class="main-bulletin__list-item__link__numberOfGood">
            {{ bulletin.numberOfGood }} 회
          </div>
        </a>
      </li>
    </ul>
  </div>
</template>

<script>
import category from '../../constants/category';

export default {
  name: 'main-bulletin',
  data() {
    return {
      bulletins: [
        {
          category: category.CONVENIENCE_STORE,
          title: '대덕SW마이스터고등학교 gs29 편의점 아르바이트...',
          numberOfGood: 180,
        },
        {
          category: category.CONVENIENCE_STORE,
          title: '아르바이트 공고',
          numberOfGood: 180,
        },
        {
          category: category.CONVENIENCE_STORE,
          title: '아르바이트 공고',
          numberOfGood: 180,
        },
        {
          category: category.CONVENIENCE_STORE,
          title: '아르바이트 공고',
          numberOfGood: 180,
        },
        {
          category: category.CONVENIENCE_STORE,
          title: '아르바이트 공고',
          numberOfGood: 180,
        },
        {
          category: category.CONVENIENCE_STORE,
          title: '아르바이트 공고',
          numberOfGood: 180,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.main-bulletin {
  position: relative;
  @include e('list-item') {
    width: 100%;
    height: 120px;
    border-radius: 5px;
    background-color: #636d81;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
    margin-bottom: 10px;
    padding: {
      left: 120px;
      right: 20%;
      top: 10px;
      bottom: 10px;
    }
    @include e('link') {
      border-left: 2px solid rgba(255, 255, 255, 0.3);
      display: block;
      height: 100%;
      width: 100%;
      padding: {
        left: 30px;
        top: 20px;
        bottom: 20px;
      }
      color: #ffffff;
      @include e('title') {
        height: 25px;
        font-size: 20px;
        line-height: 25px;
      }
      @include e('numberOfGood') {
        padding: {
          top: 15px;
          bottom: 15px;
          left: 33px;
        }
        height: 17px;
        font-size: 14px;
        line-height: 17px;
      }
    }
  }
}
</style>
