<template>
  <div>
    <navigation :style="postNavStyle"/>
    <div class="post">
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
  </div>
</template>

<script>
import Navigation from './Common/Navigation';
import PostArticle from './Post/PostArticle';
import PostComment from './Post/PostComment';
import uri from '../constants/uri';

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
      name: '데이터를 가져오는 중 입니다',
      writingDate: '',
      writingTime: '',
      viewCount: 0,
      content: '데이터를 가져오는 중 입니다',
      good: 47,
      bad: 13,
      comments: null,
    };
  },
  created() {
    const postId = location.href.split('id=')[1];
    this.$axios.get(`${uri}/posts/${postId}`)
      .then(({ data }) => {
        const post = data.post[0];
        this.name = post.title;
        this.writingDate = `${post.createdDate[0]}.${post.createdDate[1]}.${post.createdDate[2]}`;
        this.writingTime = `${post.createdDate[3]}:${post.createdDate[4]}`;
        this.viewCount = post.visit;
        this.content = post.content;
        this.good = post.favor;
        this.bad = post.hate;
        this.comments = data.comments[0];
      });
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
