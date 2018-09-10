<template>
  <div class="post-article">
    <header class="article__header">
      <h2 class="article__title">
        {{ name }}
      </h2>
      <div class="article__info">
        {{ writingDay }} | {{ writingTime }} | 조회 {{ viewCount }}회
      </div>
    </header>
    <div class="article__content">
      {{ content }}
    </div>
    <div class="article__cover">
      <div class="clickable good">
        {{ good }}
      </div>
      <div class="clickable bad">
        {{ bad }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    name: String,
    writingDay: String,
    writingTime: String,
    viewCount: Number,
    content: String,
    good: Number,
    bad: Number,
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$color1: #494f5c;

</style>
