<template>
  <div class="filter">
    <ul class="filter__subject-list">
      <li class="filter__subject-list__item"
        v-for="(subject, index) in prioritySubjects"
        :key="index">
        <div class="filter__subject-list__item__name">
          {{ index + 1 }}. {{ subject.name }}
        </div>
        <div class="filter__subject-list__item__option">
          <!-- 항목별 라디오 버튼 -->
          <template v-for="option in subject.options">
            <input class="filter__subject-list__item__option__radio"
              type="radio"
              :key="`radio-${option.value}`"
              :id="option.value"
              :value="option.value"
              v-model="subjectValues[index]">
            <label class="filter__subject-list__item__option__label"
              :key="`label-${option.value}`"
              :for="option.value">
              {{ option.text }}
            </label>
          </template>

          <!-- 상관 없음 라디오 버튼 -->
          <input class="filter__subject-list__item__option__radio"
            type="radio"
            :id="`irrelevent-${subject.name}`"
            :value="false"
            v-model="subjectValues[index]">
          <label class="filter__subject-list__item__option__label"
            :for="`irrelevent-${subject.name}`">
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
      prioritySubjects: [
        {
          name: '거리',
          options: [
            { text: '가까운 거리', value: 'close' },
            { text: '먼 거리', value: 'far' },
          ],
        },
        {
          name: '시급',
          options: [
            { text: '높은 시급', value: 'high' },
            { text: '낮은 시급', value: 'low' },
          ],
        },
        {
          name: '근무 기간',
          options: [
            { text: '단기 근무', value: 'shortDay' },
            { text: '장기 근무', value: 'longDay' },
          ],
        },
        {
          name: '근무 시간',
          options: [
            { text: '짧은 시간', value: 'shortTime' },
            { text: '긴 시간', value: 'longTime' },
          ],
        },
      ],
      subjectValues: [false, false, false, false],
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

</style>
