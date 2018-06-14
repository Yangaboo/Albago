<template>
  <transition name="modal">
    <div class="set-location">
      <div class="set-location__mask" @click="$emit('closeModal')"></div>
      <div class="set-location__modal">
        <h2 class="set-location__modal__name">
          위치 변경하기
          <button class="set-location__modal__name__close-button">
            &times;
          </button>
        </h2>
        <div class="set-location__modal__main">
          <div class="set-location__modal__main__search">
            <input type="search" class="set-location__modal__main__search__input-text">
            <button class="set-location__modal__main__search__search-button">
              검색
            </button>
          </div>
          <vue-daum-map class="set-location__modal__main__map"
            :appKey="appKey"
            :center.sync="center"
            :level.sync="level"
            :mapTypeId="mapTypeId"
            :libraries="libraries"
            @load="onLoad"
            @center_changed="onMapEvent('center_changed', $event)"
            @zoom_start="onMapEvent('zoom_start', $event)"
            @zoom_changed="onMapEvent('zoom_changed', $event)"
            @bounds_changed="onMapEvent('bounds_changed', $event)"
            @click="onMapEvent('click', $event)"
            @dblclick="onMapEvent('dblclick', $event)"
            @rightclick="onMapEvent('rightclick', $event)"
            @mousemove="onMapEvent('mousemove', $event)"
            @dragstart="onMapEvent('dragstart', $event)"
            @drag="onMapEvent('drag', $event)"
            @dragend="onMapEvent('dragend', $event)"
            @idle="onMapEvent('idle', $event)"
            @tilesloaded="onMapEvent('tilesloaded', $event)"
            @maptypeid_changed="onMapEvent('maptypeid_changed', $event)"/>
          <div class="set-location__modal__main__wrapper">
            <ul class="set-location__modal__main__wrapper__result-list">
              <li class="set-location__modal__main__wrapper__result-list__item">
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

export default {
  name: 'set-location',
  components: {
    VueDaumMap,
  },
  data() {
    return {
      appKey: 'dc491c01f5648598a4745b9710a16418', // 테스트용 appkey
      center: { lat: 33.450701, lng: 126.570667 },
      level: 3,
      mapTypeId: VueDaumMap.MapTypeId.NORMAL,
      libraries: [],
      mapObject: null,
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
    },
    onMapEvent(event, params) {
      window.console.log(`Daum Map Event(${event})`, params);
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
      @include e('search') {
        width: 100%;
        height: 60px;
        display: flex;
        margin: {
          bottom: 50px;
        }
        @include e('input-text') {
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
        // flex: 5;
        width: 400px;
        height: 400px;
      }
      // @include e('wrapper') {
      //   flex: 4;
      // }
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
