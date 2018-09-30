<template>
  <ul class="paging">
    <li class="paging__page paging__page--previous">
      <a v-if="startNumber > 1"
        class="paging__link"
        :href="link(startNumber - 1)"
        @click="previous(startNumber - 1)">&lt;</a>
    </li>
    <li class="paging__page"
      v-for="number in pages" :key="number"
      :class="{'paging__page--selected': selected === number}">
      <a class="paging__link"
        :href="link(number)"
        @click="select(number)">
        {{ number }}
      </a>
    </li>
    <li class="paging__page paging__page--next">
      <a v-if="endNumber < pagesTotalNumber"
        class="paging__link"
        :href="link(endNumber)"
        @click="next(endNumber)">&gt;</a>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'bulletin-paging',
  props: {
    pagesTotalNumber: {
      type: Number,
      required: true,
    },
    selected: {
      type: Number,
      default: 1,
    },
    category: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      startNumber: 1,
    };
  },
  computed: {
    endNumber() {
      const count = this.startNumber + 10 > this.pagesTotalNumber ? this.pagesTotalNumber % 10 : 10;
      return this.startNumber + count;
    },
    pages() {
      const pages = [];
      for (let page = this.startNumber; page < this.endNumber; page += 1) {
        pages.push(page);
      }
      return pages;
    },
  },
  methods: {
    link(number) {
      return `#/bulletin?page=${number}&category=${this.category}`;
    },
    select(number) {
      this.$emit('select', number);
    },
    previous(number) {
      this.startNumber -= 10;
      this.select(number);
    },
    next(number) {
      this.startNumber += 10;
      this.select(number);
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

.paging {
  $size: 25px;

  display: flex;
  margin: 85px auto 0;
  width: 250px;
  position: relative;
  @include e('page') {
    width: $size;
    height: $size;
    color: white;
    @include m('previous') {
      position: absolute;
      top: 0;
      left: -$size;
    }
    @include m('next') {
      position: absolute;
      top: 0;
      right: -$size;
    }
    @include m('selected') {
      border-radius: 100%;
      background-color: #fff;
      color: #494f5c;
      box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
      font-weight: bolder;
    }
  }
  @include e('link') {
    font-size: 15px;
    line-height: $size;
    text-align: center;
    width: 100%;
    height: 100%;
    display: block;
  }
}
</style>
