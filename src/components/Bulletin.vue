<template>
  <div class="bulletin">
    <header class="bulletin__header">
      <div class="bulletin__header__outline">
        총 {{ totalBulletin }} / 금일 {{ todayBulletin }}
      </div>
      <div class="bulletin__header__navigation-tool">
        <select class="bulletin__header__navigation-tool__category">
          <option disabled selected>카테고리</option>
        </select>
        <input type="text" class="bulletin__header__navigation-tool__search-input">
        <button class="bulletin__header__navigation-tool__search-button">검색</button>
      </div>
    </header>
    <bulletin-list class="bulletin__bulletin-list"/>
    <bulletin-paging class="bulletin__bulletin-paging"/>
  </div>
</template>

<script>
import BulletinList from './Bulletin/BulletinList';
import BulletinPaging from './Bulletin/BulletinPaging';

export default {
  name: 'bulletin',
  data() {

  },
  components: {
    BulletinList,
    BulletinPaging,
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';

.bulletin {
  height: 700px;
  width: 100%;
  background-image: linear-gradient(to left, #494f5c, #373c46);
}

</style>
