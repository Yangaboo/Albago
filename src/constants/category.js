export const categoryObj = {
  ALL: {
    text: '전체',
    value: 0,
  },
  SERVE: {
    text: '카운터 서빙',
    value: 1,
  },
  MANAGE: {
    text: '매장관리',
    value: 2,
  },
  PARK: {
    text: '놀이공원 워터파크 스키장',
    value: 3,
  },
  IT: {
    text: 'IT 디자인',
    value: 4,
  },
  CLEAN: {
    text: '청소',
    value: 5,
  },
  EXPRESS: {
    text: '물류 이송',
    value: 6,
  },
  OFFICE: {
    text: '사무직',
    value: 7,
  },
  ECT: {
    text: '기타',
    value: 8,
  },
};

export const categoryArr = [
  'ALL',
  'SERVE',
  'MANAGE',
  'PARK',
  'IT',
  'CLEAN',
  'EXPRESS',
  'OFFICE',
  'ECT',
];

Object.freeze(categoryArr);
Object.freeze(categoryObj);

export default 0;
