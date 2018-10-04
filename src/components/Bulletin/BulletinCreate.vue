<template>
  <transition name="modal">
    <div class="create">
      <div class="create__mask" @click="$emit('close-modal')"></div>
      <div class="modal">
        <h2 class="modal__name">
          글 작성하기
        </h2>
        <div class="modal__flex-cover">
          <input
            class="modal__title"
            type="text"
            placeholder="제목을 입력해주세요"
            @input="title = $event.target.value"
            :value="title">
          <select
            class="modal__category"
            v-model="currentCategory">
            <option v-for="option in categoryOptions"
              :key="option.value"
              :value="option.value">
              {{ option.text }}
            </option>
          </select>
        </div>
        <textarea
          class="modal__content"
          type="text"
          placeholder="내용을 입력해주세요"
          @input="content = $event.target.value"
          :value="content">
        </textarea>
        <input
          class="modal__nickname"
          type="text"
          placeholder="닉네임을 입력해주세요"
          @input="nickname = $event.target.value"
          :value="nickname">
        <div class="modal__flex-cover">
          <input
            class="modal__pw"
            placeholder="비밀번호를 입력해주세요"
            type="password"
            v-model="pw">
          <input
            class="modal__pw-re"
            placeholder="비밀번호를 재 입력해주세요"
            type="password"
            v-model="pwRe">
        </div>
        <button
          class="modal__btn"
          @click="!onPost ? create() : ''">
          {{ buttonText }}
        </button>
      </div>
    </div>
  </transition>
</template>

<script>
import { categoryObj } from '../../constants/category';
import URI from '../../constants/uri';

export default {
  name: 'bulletin-create',
  data() {
    return {
      title: '',
      content: '',
      nickname: '',
      pw: '',
      pwRe: '',
      currentCategory: 8,
      categoryOptions: categoryObj,
      buttonText: '게시',
      onPost: false,
    };
  },
  methods: {
    create() {
      this.onPost = true;
      this.buttonText = '게시중';
      this.$axios.post(`${URI}/posts`, {
        author: this.nickname,
        cat_id: this.currentCategory,
        content: this.content,
        pwd: this.pw,
        title: this.title,
        hate: 0,
        favor: 0,
        visit: 0,
      }).then(({ status }) => {
        if (status !== 200) {
          this.buttonText = '오류 발생';
        } else {
          this.$emit('create');
          this.$emit('close-modal');
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$z-index-create: 1;
$z-index-mask: 2;
$z-index-modal: 3;
$color-main: #494f5c;

.create {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: $z-index-create;
  @include e('mask') {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: $z-index-mask;
  }
}

.modal {
  $width-modal: 900px;
  $height-modal: 600px;
  $radius: 10px;
  $name-padding-top: 30px;
  $border-modal: solid 1px #707070;

  position: absolute;
  z-index: $z-index-modal;
  left: 50%;
  top: 50%;
  margin: {
    left: -($width-modal / 2);
    top: -($height-modal / 2);
  }
  width: $width-modal;
  height: $height-modal;
  background-color: #fff;
  border-radius: $radius;
  font-size: 16px;
  line-height: 60px;
  display: flex;
  flex-flow: column;
  @include e('name') {
    border-bottom: $border-modal;
    height: 60px;
    text-align: center;
    color: $color-main;
    font-weight: bold;
  }
  @include e('flex-cover') {
    height: 60px;
    border-bottom: $border-modal;
    display: flex;
  }
  @include e('title') {
    padding-left: 30px;
    flex: 1;
  }
  @include e('category') {
    padding-left: 20px;
    width: 200px;
    border: none;
    border-left: $border-modal;
  }
  @include e('content') {
    resize: none;
    border: none;
    font-size: inherit;
    overflow-y: scroll;
    border-bottom: $border-modal;
    width: 100%;
    flex: 1;
    padding: 30px;
    box-sizing: border-box;
  }
  @include e('nickname') {
    height: 60px;
    border-bottom: $border-modal;
    width: 100%;
    padding-left: 30px;
  }
  @include e('pw') {
    flex: 1;
    padding-left: 30px;
  }
  @include e('pw-re') {
    flex: 1;
    padding-left: 30px;
    border-left: $border-modal;
  }
  @include e('btn ') {
    height: 80px;
    line-height: 80px;
    font-size: 24px;
    font-weight: bolder;
    color: #fff;
    background-color: $color-main;
  }
  &-enter-active, &-leave-active {
    transition: opacity 0.5s ease;
  }
  &-enter, &-leave-to {
    opacity: 0;
  }
}
</style>
