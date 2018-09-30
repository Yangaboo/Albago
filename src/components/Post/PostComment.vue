<template>
  <div class="comment">
    <div class="comment__count" v-if="comments">
      댓글 {{ comments.length }}개
    </div>
    <ul class="comment__list">
      <li
        class="comment__item"
        v-for="(comment, index) in comments" :key="index">
        <div class="comment__name">
          {{ comment.author }}
        </div>
        <div class="comment__writing-date">
          {{ formatDate(comment.createdDate) }}
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
          {{comment.favor}}
        </label>
        <button class="comment__delete" @click="$emit('delete', index)">
          삭제
        </button>
        <!-- TODO: 댓글 수정 기능 -->
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
        class="comment__input"
        type="text"
        placeholder="닉네임"
        v-model="name">
      <input
        class="comment__input"
        type="password"
        placeholder="비밀번호"
        v-model="pw">
      <input
        class="comment__input"
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
  // TODO: 서버로부터 데이터 받아온 comments로 데이터 바인딩하기
  methods: {
    formatDate(dateArr) {
      return `${dateArr[0]}.${dateArr[1]}.${dateArr[2]} ${dateArr[3]}:${dateArr[4]}`;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$color1: #494f5c;
$border1: 3px solid #c4c6c9;

.comment {
  background-color: #eeeeee;
  padding: 30px 40px;
  position: relative;
  border-top: $border1;
  @include e('count') {
    position: absolute;
    top: -30px;
    left: 0;
    font-size: 15px;
  }
  @include e('item') {
    display: flex;
    flex-wrap: wrap;
    position: relative;
    padding: 20px 0;
    line-height: 30px;
    border-bottom: $border1;
  }
  @include e('name') {
    font-size: 15px;
    font-weight: bold;
    width: 100%;
  }
  @include e('writing-date') {
    font-size: 12px;
    position: absolute;
    top: 20px;
    right: 0;
  }
  @include e('content') {
    flex: 1;
    line-height: 20px;
  }
  @include e('good-checkbox') {
    display: none;
    &:checked + label {
      background-image: url('../../assets/comment-like_pressed.png');
    }
  }
  @include e('good-label') {
    padding-right: 10px;
    font-size: 15px;
    height: 17px;
    line-height: 18px;
    width: 90px;
    text-align: right;
    background-image: url('../../assets/comment-like.png');
    background-size: contain;
    background-repeat: no-repeat;
    user-select: none;
  }
  @include e('delete') {
    height: 17px;
    font-size: 12px;
    color: #f00;
    background: none;
    padding-left: 10px;
    border-left: $border1;
    user-select: none;
  }

  $add-font-size: 15px;
  $add-border: solid 2px #d9dadb;
  @include e('add-cover') {
    display: flex;
    flex-flow: wrap;
    margin-top: 30px;
  }
  @include e('textarea') {
    width: 100%;
    padding: 10px;
    resize: none;
    font-size: $add-font-size;
    line-height: 1;
    height: 20px + $add-font-size * 3;
    border: $add-border;
  }
  @include e('input') {
    font-size: $add-font-size;
    height: $add-font-size * 3;
    width: 25%;
    padding: 0 10px;
    border: $add-border;
  }
  @include e('btn-add') {
    width: 25%;
    font-size: $add-font-size;
    border: $add-border;
    &:focus, &:hover {
      font-weight: bolder;
    }
    &:active {
      font-weight: lighter;
    }
  }
}
</style>
