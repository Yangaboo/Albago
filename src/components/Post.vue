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
        :bad="bad"
        :isCheckedGood="isCheckedGood"
        :isCheckedBad="isCheckedBad"
        @click-good="isCheckedGood = !isCheckedGood"
        @click-bad="isCheckedBad = !isCheckedBad"/>
      <post-comment
        class="post__comment"
        :comments="comments"
        @click-good="index => toggleGood(index)"
        @delete="(index, pw, id) => deleteCommentByIndex(index, pw, id)"
        @create="(content, name, pw) => createComment(content, name, pw)"/>
    </div>
  </div>
</template>

<script>
import Navigation from './Common/Navigation';
import PostArticle from './Post/PostArticle';
import PostComment from './Post/PostComment';
import URI from '../constants/uri';

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
      postId: location.href.split('id=')[1],
      writingDate: '',
      writingTime: '',
      viewCount: 0,
      content: '데이터를 가져오는 중 입니다',
      good: 0,
      bad: 0,
      comments: null,
      isCheckedGood: false,
      isCheckedBad: false,
    };
  },
  watch: {
    isCheckedGood(value) {
      localStorage.setItem(`post${this.postId}_is_good`, value);
    },
    isCheckedBad(value) {
      localStorage.setItem(`post${this.postId}_is_bad`, value);
    },
  },
  created() {
    this.isCheckedGood = localStorage.getItem(`post${this.postId}_is_good`) === 'true';
    this.isCheckedBad = localStorage.getItem(`post${this.postId}_is_bad`) === 'true';
    this.$axios.get(`${URI}/posts/${this.postId}`)
      .then((res) => {
        const { data } = res;
        const post = data.post[0];
        this.name = post.title;
        this.writingDate = `${post.createdDate[0]}.${post.createdDate[1]}.${post.createdDate[2]}`;
        this.writingTime = `${post.createdDate[3]}:${post.createdDate[4]}`;
        this.viewCount = post.visit;
        this.content = post.content;
        this.good = post.favor;
        this.bad = post.hate;

        for (let i = 0; i < data.comments[0].length; i += 1) {
          data.comments[0][i].verifyPw = '';
          data.comments[0][i].verifyPlaceholdText = '비밀번호';
          data.comments[0][i].onVerify = false;
          data.comments[0][i].isCheckedGood = localStorage.getItem(`post${this.postId}_comment${i}_is_good`) === 'true';
        }
        this.comments = data.comments[0];
      });
  },
  methods: {
    deleteCommentByIndex(index, pw, id) {
      this.$axios.delete(`${URI}/posts/${this.postId}/delete/${id}`, {
        param: id,
        data: { pwd: pw },
      }).then(({ status }) => {
        if (status === 200) {
          this.comments.splice(index, 1);
        }
      });
    },
    toggleGood(index) {
      localStorage.setItem(`post${this.postId}_comment${index}_is_good`, this.comments[index].isCheckedGood);
    },
    createComment(content, author, pwd) {
      this.$axios.post(`${URI}/posts/${this.postId}/comments`, {
        content,
        author,
        pwd,
        post_id: this.postId,
      }).then((res) => {
        const { data } = res;
        data.verifyPw = '';
        data.verifyPlaceholdText = '비밀번호';
        data.onVerify = false;
        data.isCheckedGood = false;
        this.comments.push(data);
      });
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
