<template>
  <div class="main-part-time">
    <h3 class="section-name">아르바이트 찾기</h3>
    <ul>
      <li class="main-part-time__list-item"
        v-for="partTime in partTimes" :key="partTime">
        <div class="main-part-time__list-item__title">{{ partTime.title }}</div>
        <div class="main-part-time__list-item__term">
          {{ partTime.startDay }} ~ {{ partTime.endDay }} ({{ partTime.term }}일간)
        </div>
        <div class="main-part-time__list-item__hour-wage">시급 {{ partTime.hourlWage }}원</div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'main-part-time',
  data() {
    return {
      partTimes: [
        {
          title: '대덕SW마이스터고등학교 gs29 편의점 아르바이트',
          startDay: 20180425,
          endDay: 20180426,
          hourlWage: 8200,
        },
        {
          title: '대덕SW마이스터고등학교 gs29 편의점 아르바이트',
          startDay: 20180425,
          endDay: 20180426,
          hourlWage: 8200,
        },
        {
          title: '대덕SW마이스터고등학교 gs29 편의점 아르바이트',
          startDay: 20180425,
          endDay: 20180426,
          hourlWage: 8200,
        },
        {
          title: '대덕SW마이스터고등학교 gs29 편의점 아르바이트',
          startDay: 20180425,
          endDay: 20180426,
          hourlWage: 8200,
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.main-part-time {
  position: relative;
  border-radius: 5px;
  background-color: #636d81;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
  padding: {
    top: 20px;
    left: 50px;
    right: 50px;
    bottom: 20px;
  }
  @include e('list-item') {
    height: 115px;
    position: relative;
    border-bottom: 2px solid #fff;
    padding: {
      top: 30px;
    }
    &:last-child {
      border: none;
    }
    @include e('title') {
      font-size: 20px;
      height: 25px;
      line-height: 25px;
      color: #fff;
    }
    @include e('term') {
      font-size: 17px;
      line-height: 20px;
      height: 20px;
      color: #fff;
      margin: {
        top: 10px;
      }
    }
    @include e('hour-wage') {
      font-size: 25px;
      height: 25px;
      color: #ffe886;
      position: absolute;
      bottom: 30px;
      right: 0;
    }
  }
}
</style>
