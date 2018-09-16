<template>
  <div class="post">
    <navigation :style="postNavStyle"/>
    <div class="branch">
      홈 > 게시판 > 아르바이트 리뷰
    </div>
    <post-article
      class="post__article"
      :name="name"
      :writingDate="writingDate"
      :writingTime="writingTime"
      :viewCount="viewCount"
      :content="content"
      :good="good"
      :bad="bad"/>
    <post-comment
      class="post__comment"
      :comments="comments"
      @click-good="index => toggleGood(index)"
      @delete="index => deleteCommentByIndex(index)"
      @create="(content, name, pw) => createComment(content, name, pw)"/>
  </div>
</template>

<script>
import Navigation from './Common/Navigation';
import PostArticle from './Post/PostArticle';
import PostComment from './Post/PostComment';

export default {
  components: {
    Navigation,
    PostArticle,
    PostComment,
  },
  name: 'post',
  data() {
    return {
      postNavStyle: 'background-color: #eee; box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.16)',
      name: '대전 대덕소프트웨어마이스터고등학교 GS27 편의점 아르바이트 후기',
      writingDate: '2018.09.10',
      writingTime: '12:00',
      viewCount: 87,
      content: 'text',
      good: 47,
      bad: 13,
      comments: [{
        name: '1와 정말 좋네요',
        writingDate: '2018.06.30',
        writingTime: '09:22',
        content: '편의점 아르바이트 하는데 도움이 많이 됐습니다',
        good: 10,
        isCheckedGood: false,
      }, {
        name: '2와 정말 좋네요',
        writingDate: '2018.06.30',
        writingTime: '09:22',
        content: '편의점 아르바이트 하는데 도움이 많이 됐습니다',
        good: 10,
        isCheckedGood: false,
      }, {
        name: '3와 정말 좋네요',
        writingDate: '2018.06.30',
        writingTime: '09:22',
        content: '편의점 아르바이트 하는데 도움이 많이 됐습니다',
        good: 10,
        isCheckedGood: false,
      }],
    };
  },
  methods: {
    deleteCommentByIndex(index) {
      this.comments.splice(index, 1);

      // TODO: 댓글 삭제 요청
    },
    toggleGood(index) {
      // TODO: 해당 index의 isCheckedGood이 토글됨, 서버로 요청(각 컴퓨터를 구분할 수 있는 식별자와 함께)
    },
    formatDate(dateObject = new Date()) {
      const year = dateObject.getFullYear();
      const month = `0${dateObject.getMonth() + 1}`.slice(-2);
      const date = `0${dateObject.getDate()}`.slice(-2);

      return `${year}.${month}.${date}`;
    },
    formatTime(dateObject = new Date()) {
      const hour = `0${dateObject.getHours()}`.slice(-2);
      const minute = `0${dateObject.getMinutes()}`.slice(-2);

      return `${hour}:${minute}`;
    },
    createComment(content, name, pw) {
      this.comments.push({
        name,
        content,
        writingDate: this.formatDate(),
        writingTime: this.formatTime(),
        good: 0,
      });

      // TODO: 댓글 추가 요청
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$color1: #494f5c;

.post {
  width: 800px;
  margin: 0 auto;
  padding-top: 40px;
  @include e('comment') {
    margin-top: 60px;
  }
}
.branch {
  height: 32px;
  font-size: 12px;
  line-height: 15px;
  color: $color1;
}
</style>
