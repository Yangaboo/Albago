<template>
  <ul class="bulletin-paging">
    <li class="bulletin-paging__page">
      <a href="" class="bulletin-paging__page__link bulletin-paging__page__link"></a>
    </li>
    <li class="bulletin-paging__page"
      v-for="number in pagesNumber" :key="number"
      :class="selected === number ? 'bulletin-paging__page--selected' : ''">
      <a class="bulletin-paging__page__link"
        :href="link(number)"
        @click="select(number)">
        {{ number }}
      </a>
    </li>
    <li class="bulletin-paging__page">
      <a href="" class="bulletin-paging__page__link bulletin-paging__page__link"></a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'bulletin-paging',
  data() {
    return {
      selected: 1,
      pagesNumber: 10,
    };
  },
  methods: {
    link(number) {
      return `#/bulletin?page=${number}`;
    },
    select(number) {
      this.selected = number;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.bulletin-paging {
  display: flex;
  justify-content: center;
  margin-top: 85px;
  @include e('page') {
    width: 25px;
    height: 25px;
    color: white;
    @include e('link') {
      font-size: 15px;
      line-height: 25px;
      text-align: center;
      width: 100%;
      height: 100%;
      display: block;
    }
    @include m('selected') {
      border-radius: 100%;
      background-color: #fff;
      color: #494f5c;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
      font-weight: bolder;
    }
  }
}
</style>
