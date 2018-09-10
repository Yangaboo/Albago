<template>
  <div class="comment">
    <div class="comment__count">
      댓글 {{ comments.length }}개
    </div>
    <ul class="comment__list">
      <li
        class="comment__item"
        v-for="(comment, index) in comments" :key="index">
        <div class="comment__name">
          {{ comment.name }}
        </div>
        <div class="comment__writing-day">
          {{ comment.writingDay }}
        </div>
        <div class="comment__writing-time">
          {{ comment.writingTime }}
        </div>
        <div class="comment__content">
          {{ comment.content }}
        </div>
        <button class="comment__good">
          좋아요 {{ comment.good }}
        </button>
        <button class="comment__delete" @click="$emit('delete', index)">
          삭제
        </button>
      </li>
    </ul>
    <div class="comment__add-cover">
      <textarea
        class="comment__textarea"
        placeholder="댓글을 입력해주세요."></textarea>
      <input
        class="comment__input-name"
        type="text"
        placeholder="닉네임">
      <input
        class="comment__input-pw"
        type="password"
        placeholder="비밀번호">
      <input
        class="comment__input-pw-re"
        type="password"
        placeholder="비밀번호 재입력">
      <button
        class="comment__btn-add">
        댓글달기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: { comments: Array },

};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$color1: #494f5c;
$border1: 3px solid #c4c6c9;

</style>
