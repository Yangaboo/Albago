<template>
  <div class="filter">
    <ul class="filter__priority-list">
      <li><h3 class="section-name">우선순위로 비교</h3></li>

      <!-- 상관 없음이 선택되지 않은 항목, 비교 기준 -->
      <compare-job-priority
        :is-relevant="true"
        v-for="(subject, index) in relevantSubjects"
        :key="`sub1${subject.name}`"
        :index="index"
        :name="subject.name"
        :options="subject.options"
        v-model="subject.value"
        @toIrrelevant="toIrrelevant(index)">
      </compare-job-priority>

      <!-- 상관 없음이 선택된 항목, 비교 기준에서 제외 -->
      <compare-job-priority
        :is-relevant="false"
        v-for="(subject, index) in irrelevantSubjects"
        :key="`sub2${index}`"
        :index="index"
        :name="subject.name"
        :options="subject.options"
        v-model="subject.value"
        @change="newValue => toRelevant(index, newValue)">
      </compare-job-priority>
    </ul>
  </div>
</template>

<script>
import CompareJobPriority from './CompareJobPriority';

export default {
  components: {
    CompareJobPriority,
  },
  name: 'compare-job-filter',
  data() {
    return {
      relevantSubjects: [
        {
          name: '거리',
          options: [
            { text: '가까운 거리', value: 'close' },
            { text: '먼 거리', value: 'far' },
          ],
          value: 'close',
        },
        {
          name: '시급',
          options: [
            { text: '높은 시급', value: 'high' },
            { text: '낮은 시급', value: 'low' },
          ],
          value: 'high',
        },
        {
          name: '근무 기간',
          options: [
            { text: '단기 근무', value: 'shortDay' },
            { text: '장기 근무', value: 'longDay' },
          ],
          value: 'shortDay',
        },
        {
          name: '근무 시간',
          options: [
            { text: '짧은 시간', value: 'shortTime' },
            { text: '긴 시간', value: 'longTime' },
          ],
          value: 'shortTime',
        },
      ],
      irrelevantSubjects: [],
    };
  },
  methods: {
    toIrrelevant(index) {
      this.relevantSubjects[index].value = '';
      this.irrelevantSubjects.push(...this.relevantSubjects.splice(index, 1));
    },
    toRelevant(index, value) {
      this.irrelevantSubjects[index].value = value;
      this.relevantSubjects.push(...this.irrelevantSubjects.splice(index, 1));
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

.filter {
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.16);
  position: relative;
}

</style>
