<template>
  <div class="filter">
    <ul class="priority-list">
      <li><h3 class="section-name">우선순위로 비교</h3></li>

      <!-- 상관 없음이 선택되지 않은 항목, 비교 기준 -->
      <li
        is="compare-job-priority"
        :is-relevant="true"
        v-for="(subject, index) in relevantSubjects"
        :key="`sub1${subject.name}`"
        :index="index"
        :name="subject.name"
        :options="subject.options"
        v-model="subject.value"
        @toIrrelevant="toIrrelevant(index)"
        @forward="forward(index)"
        @backward="backward(index)">
      </li>

      <!-- 상관 없음이 선택된 항목, 비교 기준에서 제외 -->
      <li
        is="compare-job-priority"
        :is-relevant="false"
        v-for="(subject, index) in irrelevantSubjects"
        :key="`sub2${index}`"
        :index="index"
        :name="subject.name"
        :options="subject.options"
        v-model="subject.value"
        @change="newValue => toRelevant(index, newValue)">
      </li>

    </ul>

    <!--
      TODO
      요일: 월, 화, 수, 목, 금, 토, 일
    -->
    <div class="selectitem-cover">
      <h3 class="section-name">항목체크로 비교</h3>
      <div
        class="selectitem"
        v-for="selectitem in selectitems"
        :key="selectitem.name">
        <div class="selectitem__name">
          {{ selectitem.name }}
        </div>
        <div class="selectitem__options">
          <label
            v-for="option in selectitem.options"
            :key="`input${option.value}`"
            :for="`input${option.value}`">
            <input
              :id="`input${option.value}`"
              type="radio"
              :value="option.value"
              v-model="selectitem.value"
              style="display: none">
            {{ option.text }}
          </label>
        </div>
      </div>
    </div>

    <button class="submit" @click="submit">
      아르바이트 비교
    </button>
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
      selectitems: [
        {
          name: '시간대',
          options: [
            { text: '주간', value: 'day' },
            { text: '야간', value: 'night' },
            { text: '상관없음', value: 'ignore' },
          ],
          value: 'ignore',
        },
        {
          name: '성별',
          options: [
            { text: '남성', value: 'male' },
            { text: '여성', value: 'female' },
            { text: '상관없음', value: 'ignore' },
          ],
          value: 'ignore',
        },
        {
          name: '나이',
          options: [
            { text: '청소년', value: 'teen' },
            { text: '상관없음', value: 'ignore' },
          ],
          value: 'ignore',
        },
      ],
    };
  },
  methods: {
    movePosition(arr, from, to) {
      arr.splice(to, 0, ...arr.splice(from, 1));
    },
    forward(index) {
      if (index !== 0) {
        this.movePosition(this.relevantSubjects, index, index - 1);
      }
    },
    backward(index) {
      if (index !== this.relevantSubjects.length - 1) {
        this.movePosition(this.relevantSubjects, index, index + 1);
      }
    },
    toIrrelevant(index) {
      this.relevantSubjects[index].value = '';
      this.irrelevantSubjects.push(...this.relevantSubjects.splice(index, 1));
    },
    toRelevant(index, value) {
      this.irrelevantSubjects[index].value = value;
      this.relevantSubjects.push(...this.irrelevantSubjects.splice(index, 1));
    },
    submit() {
      this.$EventBus.$emit('updateFilter');
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
  position: relative;
}

.priority-list {
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.16);
  margin-bottom: 70px;
}

.selectitem-cover {
  position: relative;
}

.submit {
  width: 100%;
  height: 80px;
  font-size: 28px;
  line-height: 80px;
  color: #484e5a;
  background: url('../../assets/logo_mark.png') no-repeat right -50px center #fff;
  background-size: 50px auto;
  transition: background-position-x 0.5s, background-color 0.5s;
  box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.16);
  &:hover {
    background-position: right 20px center;
    background-color: #fff;
    font-weight: bold;
  }
  &:active {
    padding-top: 10px;
    line-height: 70px;
    background-position: right 20px bottom 12px;
  }
}

</style>
