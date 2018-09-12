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
        <div class="comment__writing-date">
          {{ comment.writingDate }}
        </div>
        <div class="comment__writing-time">
          {{ comment.writingTime }}
        </div>
        <div class="comment__content">
          {{ comment.content }}
        </div>
        <input
          type="checkbox"
          class="comment__good-checkbox"
          v-model="comment.isCheckedGood"
          @change="$emit('click-good', index)"
          :id="`good${index}`"/>
        <label
          class="comment__good-label"
          :for="`good${index}`">
          좋아요 {{comment.good}}
        </label>
        <button class="comment__delete" @click="$emit('delete', index)">
          삭제
        </button>
      </li>
    </ul>
    <div class="comment__add-cover">
      <div class="comment__alert" v-show="pw !== pwRe">
        비밀번호가 다릅니다
      </div>
      <textarea
        class="comment__textarea"
        placeholder="댓글을 입력해주세요."
        v-model="text">
      </textarea>
      <input
        class="comment__input-name"
        type="text"
        placeholder="닉네임"
        v-model="name">
      <input
        class="comment__input-pw"
        type="password"
        placeholder="비밀번호"
        v-model="pw">
      <input
        class="comment__input-pw-re"
        type="password"
        placeholder="비밀번호 재입력"
        v-model="pwRe">
      <button
        class="comment__btn-add"
        @click="pw === pwRe ? $emit('create', text, name, pw) : ''">
        댓글달기
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: { comments: Array },
  data() {
    return {
      text: '',
      name: '',
      pw: '',
      pwRe: '',
      isCheckGood: false,
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$color1: #494f5c;
$border1: 3px solid #c4c6c9;

</style>
