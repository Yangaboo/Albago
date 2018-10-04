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
            class="bulletin__category"
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
        <div class="modal__cover">
          <input
            class="modal__pw"
            placeholder="비밀번호를 입력해주세요"
            type="text"
            v-model="pw">
          <input
            class="modal__pwRe"
            placeholder="비밀번호를 재 입력해주세요"
            type="text"
            v-model="pwRe">
        </div>
        <button @click="!onPost ? create() : ''">{{ buttonText }}</button>
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
  $side-padding: 60px;
  $name-padding-top: 30px;

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
  &-enter-active, &-leave-active {
    transition: opacity 0.5s ease;
  }
  &-enter, &-leave-to {
    opacity: 0;
  }
}
</style>
