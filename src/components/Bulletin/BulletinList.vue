<template>
  <ul class="bulletin-list">
    <li class="bulletin-list__head">
      <div class="bulletin-list__head__number">번호</div>
      <div class="bulletin-list__head__title">제목</div>
      <div class="bulletin-list__head__category">카테고리</div>
      <div class="bulletin-list__head__date">작성일</div>
      <div class="bulletin-list__head__numberOfGood">좋아요</div>
      <div class="bulletin-list__head__numberOfBad">싫어요</div>
    </li>
    <li class="bulletin-list__bulletin-link">
      <div class="bulletin-list__bulletin-link__number">{{ number }}</div>
      <div class="bulletin-list__bulletin-link__title">{{ title }}</div>
      <div class="bulletin-list__bulletin-link__category">{{ category }}</div>
      <div class="bulletin-list__bulletin-link__date">{{ date }}</div>
      <div class="bulletin-list__bulletin-link__numberOfGood">{{ numberOfGood }}</div>
      <div class="bulletin-list__bulletin-link__numberOfBad">{{ numberOfBad }}</div>
    </li>
  </ul>
</template>

<script>
export default {
  name: 'bulletin-list',
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';

</style>
