<template>
  <transition name="modal">
    <div class="set-location">
      <div class="set-location__mask" @click="$emit('closeModal')"></div>
      <div class="set-location__modal">
        <h2 class="set-location__modal__name">
          위치 변경하기
          <button class="set-location__modal__name__close-button"
            @click="$emit('closeModal')">
            &times;
          </button>
        </h2>
        <div class="set-location__modal__main">
          <div class="set-location__modal__main__search">
            <input class="set-location__modal__main__search__input-text"
              type="text"
              :placeholder="placeholdText"
              v-model="keyword"
              @keydown.enter="searchPlaces">
            <button class="set-location__modal__main__search__search-button"
              @click="searchPlaces">
              검색
            </button>
          </div>
          <vue-daum-map class="set-location__modal__main__map"
            :appKey="appKey"
            :center.sync="center"
            :level.sync="level"
            :mapTypeId="mapTypeId"
            :libraries="libraries"
            @load="onLoad"/>
          <div class="set-location__modal__main__wrapper">
            <ul class="set-location__modal__main__wrapper__result-list">
              <li class="set-location__modal__main__wrapper__result-list__item"
                v-for="placeList in placeLists"
                :key="placeList">
                {{ placeList }}
                <div class="set-location__modal__main__wrapper__result-list__item__check"></div>
              </li>
            </ul>
            <button class="set-location__modal__main__wrapper__set-button">
              해당 위치로 설정
            </button>
          </div>
        </div>
      </div>
    </div>
  </transition>
</template>

<script>
import VueDaumMap from 'vue-daum-map';
import config from '../config';

export default {
  name: 'set-location',
  components: {
    VueDaumMap,
  },
  data() {
    return {
      appKey: config.appKey,
      center: { lat: 33.450701, lng: 126.570667 },
      level: 3,
      mapTypeId: VueDaumMap.MapTypeId.NORMAL,
      libraries: ['services'],
      mapObject: null,
      daumMapsObject: {},
      searchPlaceObject: {},
      keyword: '',
      placeLists: [],
      placeholdText: '키워드를 입력해 주세요',
    };
  },
  methods: {
    onLoad(map) {
      // 지도의 현재 영역을 얻어옵니다
      const bounds = map.getBounds();
      // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
      const boundsStr = bounds.toString();
      window.console.log('Daum Map Loaded', boundsStr);
      this.mapObject = map;
      this.daumMapsObject = window.daum.maps;
      this.searchPlaceObject = new this.daumMapsObject.services.Places();
    },
    searchPlaces() {
      if (!this.keyword.replace(/^\s+|\s+$/g, '')) {
        this.changePlaceholdText('키워드를 입력해주세요!');
      } else {
        // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
        this.searchPlaceObject.keywordSearch(this.keyword, this.placesSearchCB);
      }
    },
    placesSearchCB(data, status) {
      if (status === this.daumMapsObject.services.Status.OK) {
        this.changePlaceholdText('검색 성공');
      } else if (status === this.daumMapsObject.services.Status.ZERO_RESULT) {
        this.changePlaceholdText('검색 결과가 존재하지 않습니다.');
      } else if (status === this.daumMapsObject.services.Status.ERROR) {
        this.changePlaceholdText('검색 결과 중 오류가 발생했습니다.');
      }
    },
    changePlaceholdText(text) {
      this.keyword = '';
      this.placeholdText = text;
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$z-index-set-location: 1;
$z-index-mask: 2;
$z-index-modal: 3;
$color-main: #494f5c;

@mixin set-color($color1, $color2) {
  background-color: $color1;
  color: $color2;
  &>div {
    background-color: $color2;
  }
}

.set-location {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: $z-index-set-location;
  @include e('mask') {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: $z-index-mask;
  }
  @include e('modal') {
    $width-modal: 1100px;
    $height-modal: 700px;
    $radius: 5px;
    $side-padding: 60px;
    position: absolute;
    z-index: $z-index-modal;
    left: 50%;
    top: 50%;
    margin: {
      left: -($width-modal / 2);
      top: -($height-modal / 2);
    }
    width: $width-modal;
    height: $height-modal;
    background-color: #fff;
    border-radius: $radius;
    @include e('name') {
      $padding-top: 30px;
      width: 100%;
      background-color: $color-main;
      height: 80px;
      padding: {
        top: $padding-top;
        bottom: 20px;
        left: $side-padding;
        right: $side-padding;
      }
      border-top-left-radius: $radius;
      border-top-right-radius: $radius;
      font-size: 22px;
      font-weight: bold;
      line-height: 30px;
      color: white;
      @include e('close-button') {
        position: absolute;
        top: $padding-top;
        right: $side-padding;
        font-size: 30px;
        font-weight: lighter;
        line-height: inherit;
        color: inherit;
        background: none;
        border: none;
      }
    }
    @include e('main') {
      padding: {
        top: 40px;
        left: $side-padding;
        right: $side-padding;
      }
      background-color: #fff;
      border-bottom-left-radius: $radius;
      border-bottom-right-radius: $radius;
      display: flex;
      flex-wrap: wrap;
      @include e('search') {
        width: 100%;
        height: 60px;
        display: flex;
        margin: {
          bottom: 50px;
        }
        @include e('input-text') {
          box-sizing: border-box;
          flex: 8;
          height: 100%;
          border: solid 1px $color-main;
          background-color: #fff;
          padding: {
            left: 170px;
            top: 20px;
            bottom: 20px;
          }
          font-size: 18px;
          line-height: 20px;
        }
        @include e('search-button') {
          flex: 1;
          height: 100%;
          font-size: 20px;
          font-weight: bold;
          color: #fff;
          background-color: $color-main;
          border-bottom-right-radius: $radius;
          border-top-right-radius: $radius;
        }
      }
      @include e('map') {
        width: 55%;
        height: 400px;
      }
      @include e('wrapper') {
        margin-left: 5%;
        width: 40%;
        height: 400px;
        @include e('result-list') {
          height: 300px;
          box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
          overflow-y: scroll;
          @include e('item') {
            height: 50px;
            font-size: 13px;
            line-height: 50px;
            position: relative;
            padding: {
              left: 50px;
            }
            &:nth-child(even) {
              @include set-color(#fff, $color-main);
            }
            &:nth-child(odd) {
              @include set-color($color-main, #fff);
            }
            @include e('check') {
              position: absolute;
              top: 10px;
              right: 30px;
              width: 30px;
              height: 30px;
              box-shadow: 0 6px 6px 0 rgba(0, 0, 0, 0.16);
              border-radius: 100%;
            }
          }
        }
        @include e('set-button') {
          width: 100%;
          color: #fff;
          font-size: 18px;
          margin-top: 30px;
          height: 70px;
          border-radius: 3px;
          background-color: $color-main;
          box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
        }
      }
    }
  }
}
.modal {
  &-enter-active, &-leave-active {
    transition: opacity 0.5s ease;
  }
  &-enter, &-leave-to {
    opacity: 0;
  }
}
</style>
