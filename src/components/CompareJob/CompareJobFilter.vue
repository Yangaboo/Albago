<template>
  <div class="filter">
    <ul class="filter__priority-list">
      <li><h3 class="section-name">우선순위로 비교</h3></li>

      <!-- 상관 없음이 선택되지 않은 항목, 비교 기준 -->
      <li class="filter__priority-list__item"
        v-for="(subject, index) in relevantSubjects"
        :key="`sub1${index}`">
        <div class="filter__priority-list__item__name">
          {{ index + 1 }}. {{ subject.name }}
        </div>
        <div class="filter__priority-list__item__option">
          <!-- 항목별 라디오 버튼 -->
          <template v-for="option in subject.options">
            <input class="filter__priority-list__item__option__radio"
              type="radio"
              :key="`radio-${option.value}`"
              :id="option.value"
              :value="option.value"
              v-model="subject.value">
            <label class="filter__priority-list__item__option__label"
              :key="`label-${option.value}`"
              :for="option.value">
              {{ option.text }}
            </label>
          </template>

          <!-- 상관 없음 라디오 버튼 -->
          <input class="filter__priority-list__item__option__radio"
            type="checkbox"
            :id="`irrelevant-${subject.name}`"
            v-model="subject.isIrrelevant"
            @click="toIrrelevant(index)">
          <label class="filter__priority-list__item__option__label"
            :for="`irrelevant-${subject.name}`">
            상관 없음
          </label>
        </div>
      </li>

      <!-- 상관 없음이 선택된 항목, 비교 기준에서 제외 -->
      <li class="filter__priority-list__item filter__priority-list__item--irrelevant"
        v-for="(subject, index) in irrelevantSubjects"
        :key="`sub2${index}`">
        <div class="filter__priority-list__item__name">
          {{ subject.name }}
        </div>
        <div class="filter__priority-list__item__option">
          <!-- 항목별 라디오 버튼 -->
          <template v-for="option in subject.options">
            <input class="filter__priority-list__item__option__radio"
              type="radio"
              :key="`radio-${option.value}`"
              :id="option.value"
              :value="option.value"
              v-model="subject.value"
              @click="toRelevant(index)">
            <label class="filter__priority-list__item__option__label"
              :key="`label-${option.value}`"
              :for="option.value">
              {{ option.text }}
            </label>
          </template>

          <!-- 상관 없음 라디오 버튼 -->
          <input class="filter__priority-list__item__option__radio"
            type="checkbox"
            :id="`irrelevant-${subject.name}`"
            v-model="subject.isIrrelevant">
          <label class="filter__priority-list__item__option__label"
            :for="`irrelevant-${subject.name}`">
            상관 없음
          </label>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
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
          isIrrelevant: false,
        },
        {
          name: '시급',
          options: [
            { text: '높은 시급', value: 'high' },
            { text: '낮은 시급', value: 'low' },
          ],
          value: 'high',
          isIrrelevant: false,
        },
        {
          name: '근무 기간',
          options: [
            { text: '단기 근무', value: 'shortDay' },
            { text: '장기 근무', value: 'longDay' },
          ],
          value: 'shortDay',
          isIrrelevant: false,
        },
        {
          name: '근무 시간',
          options: [
            { text: '짧은 시간', value: 'shortTime' },
            { text: '긴 시간', value: 'longTime' },
          ],
          value: 'shortTime',
          isIrrelevant: false,
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
    toRelevant(index) {
      this.irrelevantSubjects[index].isIrrelevant = false;
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
  @include e('priority-list') {
    @include e('item') {
      $item-height: 80px;
      $item-content-height: 35px;
      background-color: $main-color2;
      height: $item-height;
      display: flex;
      border-bottom: 1px solid $point-color1;
      color: #fff; // name color
      padding: {
        top: ($item-height - $item-content-height) / 2;
        left: 40px;
      }
      &:last-child {
        border-bottom: none;
      }
      @include m('irrelevant') {
        background-color: $main-color2 / 1.2;
        color: #aaa;
        &:first-child {
          margin-top: 100px;
        }
      }
      @include e('name') {
        width: 30%;
        height: $item-content-height;
        font-size: 20px;
        line-height: $item-content-height;
        color: inherit;
      }
      @include e('option') {
        width: 60%;
        display: flex;
        @include e('radio') {
          display: none;
          &:checked + label {
            background-color: $main-color1;
            color: #fff;
            box-shadow: inset 0 2px 2px 0 rgba(0, 0, 0, 0.16);
          }
        }
        @include e('label') {
          flex: 1;
          height: $item-content-height;
          border-radius: 2px;
          background-color: #fff;
          box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
          display: block;
          text-align: center;
          line-height: $item-content-height;
          font-size: 16px;
          color: $main-color1;
          margin-left: 10px;
        }
      }
    }
  }
}

</style>
