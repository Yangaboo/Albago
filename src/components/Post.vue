<template>
  <div class="post">
    <div class="branch">
      홈 > 게시판 > 아르바이트 리뷰
    </div>
    <post-article
      :name="name"
      :writingDay="writingDay"
      :writingTime="writingTime"
      :content="content"
      :good="good"
      :bad="bad"/>
    <post-comment :comments="comments"/>
  </div>
</template>

<script>
import PostArticle from './Post/PostArticle';
import PostComment from './Post/PostComment';

export default {
  components: {
    PostArticle,
    PostComment,
  },
  name: 'post',
  data() {
    return {
      name: '대전 대덕소프트웨어마이스터고등학교 GS27 편의점 아르바이트 후기',
      writingDay: '2018.09.10',
      writingTime: '12:00',
      viewCount: 87,
      content: 'text',
      good: 47,
      bad: 13,
      comments: [{
        name: '와 정말 좋네요',
        writingDay: '2018.06.30',
        writingTime: '09:22',
        content: '편의점 아르바이트 하는데 도움이 많이 됐습니다',
        good: 10,
      }],
    };
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
}
.branch {
  height: 32px;
  font-size: 12px;
  line-height: 15px;
  color: $color1;
}
</style>
