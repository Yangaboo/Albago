<template>
  <div class="post-article">
    <header class="article__header">
      <h2 class="article__title">
        {{ name }}
      </h2>
      <div class="article__info">
        {{ writingDate }} | {{ writingTime }} | 조회 {{ viewCount }}회
      </div>
    </header>
    <div class="article__content" v-html="content"></div>
    <div class="article__cover">
      <div
        class="clickable good"
        :class="{'checked': isCheckedGood}"
        @click="$emit('click-good')">
        {{ isCheckedGood ? good + 1 : good }}
      </div>
      <div
        class="clickable bad"
        :class="{'checked': isCheckedBad}"
        @click="$emit('click-bad')">
        {{ isCheckedBad ? bad + 1 : bad }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    name: String,
    writingDate: String,
    writingTime: String,
    viewCount: Number,
    content: String,
    good: Number,
    bad: Number,
    isCheckedGood: Boolean,
    isCheckedBad: Boolean,
  },
  methods: {
    updatePost() {
      this.$emit('update');
    },
    deletePost() {
      this.$emit('delete');
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$color1: #494f5c;
$border1: 3px solid #c4c6c9;

.post-article {
  border: {
    top: $border1;
    bottom: $border1;
  }
  padding: 30px 0;
}

.article {
  @include e('header') {
    border: {
      top: solid 3px $color1;
      bottom: $border1;
    }
    padding: 30px 10px;
  }
  @include e('title') {
    height: 20px;
    font-size: 22px;
    line-height: 20px;
    color: $color1;
    margin-bottom: 10px;
  }
  @include e('info') {
    height: 14px;
    font-size: 14px;
    color: $color1;
    display: inline-block;
  }
  @include e('content') {
    padding: 30px 10px;
    font-size: 14px;
    line-height: 20px;
    color: $color1;
  }
  @include e('cover') {
    display: flex;
    justify-content: center;
  }
}

.clickable {
  width: 100px;
  height: 50px;
  text-align: center;
  margin: 0 30px;
}
.good {
  background: url('../../assets/post-like.png') no-repeat bottom center;
  background-size: contain;
  &.checked {
    background: url('../../assets/post-like_pressed.png') no-repeat bottom center;
    background-size: contain;
  }
}
.bad {
  background: url('../../assets/post-dislike.png') no-repeat bottom center;
  background-size: contain;
  &.checked {
    background: url('../../assets/post-dislike_pressed.png') no-repeat bottom center;
    background-size: contain;
  }
}

</style>
