<template>
  <li class="subject" :class="{'subject--irrelevant': !isRelevant}">
    <div class="subject__name">
      <span v-show="isRelevant">{{ index + 1 }}.</span> {{ name }}
    </div>
    <div class="subject__option">
      <!-- 항목별 라디오 버튼 -->
      <template v-for="option in options">
        <input
          class="subject__option__radio"
          type="radio"
          :key="`radio-${option.value}`"
          :id="option.value"
          :value="option.value"
          v-model="mutableValue"
          @change="$emit('change', mutableValue)">
        <label
          class="subject__option__label"
          :key="`label-${option.value}`"
          :for="option.value">
          {{ option.text }}
        </label>
      </template>

      <!-- 상관 없음 라디오 버튼 -->
      <input
        class="subject__option__radio"
        type="radio"
        :id="`irrelevant-${name}`"
        :checked="!isRelevant"
        @click="$emit('toIrrelevant')">
      <label class="subject__option__label"
        :for="`irrelevant-${name}`">
        상관 없음
      </label>
    </div>
    <div class="subject__order-btn-cover">
      <button class="order-btn forward"></button>
      <button class="order-btn backward"></button>
    </div>
  </li>
</template>

<script>
export default {
  name: 'compare-job-priority',
  model: {
    prop: 'value',
    event: 'change',
  },
  props: {
    index: { type: Number },
    name: { type: String },
    options: { type: Array },
    value: { type: String },
    irrelevantValue: { type: Boolean },
    isRelevant: { type: Boolean },
  },
  data() {
    return {
      mutableValue: this.value,
    };
  },
};
</script>

<style lang="scss" scoped>
@import '../../styles/setting';
$main-color1: #494f5c;
$main-color2: #636d81;
$point-color1: #ffe886;

.subject {
  $item-height: 80px;
  $item-content-height: 35px;

  background-color: $main-color2;
  height: $item-height;
  display: flex;
  border-bottom: 1px solid $point-color1;
  color: #fff; // name color
  position: relative;
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
  @include e('order-btn-cover') {
    position: absolute;
    top: 0;
    right: 0;
    height: 100%;
    width: 50px;
    display: flex;
    flex-flow: column;
    .order-btn {
      display: block;
      height: 50%;
      width: 100%;
      filter: brightness(0.6);
      cursor: pointer;
      &:hover {
        filter: brightness(0.8);
      }
    }
  }
}

.forward {
  background: url('../../assets/arrow_top.svg') no-repeat center bottom 5px;
  background-size: 25px;
}

.backward {
  background: url('../../assets/arrow_bottom.svg') no-repeat center top 5px;
  background-size: 25px;
}
</style>
