<template>
  <transition name="modal">
    <div class="set-loc">>
      <div class="set-loc__mask" @click="$emit('closeModal')"></div>
      <div class="modal">

        <!-- modal header -->
        <h2 class="modal__name">
          위치 변경하기
          <button class="modal__close-button"
            @click="$emit('closeModal')">
            &times;
          </button>
        </h2>

        <!-- modal main -->
        <div class="modal__main">
          <form @submit.prevent="searchPlaces" class="modal__search">
            <!-- modal input keyword -->
            <input class="modal__search__input-text"
              type="text"
              :placeholder="placeholdText"
              v-model="keyword">
            <input
              type="submit"
              class="modal__search__search-button"
              value="검색"/>
          </form>

          <!-- map view -->
          <vue-daum-map class="modal__map"
            :appKey="appKey"
            :center.sync="center"
            :level.sync="level"
            :libraries="libraries"
            @load="onLoad"/>
          <div class="modal__wrapper">

            <!-- result list -->
            <ul class="modal__result-list">
              <li class="modal__result-item"
                v-for="(placeList, index) in placeLists"
                :key="index"
                @click="selectPlace(index)"
                @mouseover="displayInfowindow(markers[index], placeList.place_name)"
                @mouseout="infowindow.close()"
                :class="{'selected': selected == index}">
                <h5 class="modal__result-item__name">
                  {{ placeList.place_name }}
                </h5>
                <div class="modal__result-item__address-name">
                  {{ placeList.road_address_name || placeList.address_name }}
                </div>
                <div class="modal__result-item__check check">
                  &#10004;
                </div>
              </li>
            </ul>

            <!-- result list pagination -->
            <div class="modal__pagination">
              <a class="modal__pagination__number"
                href="#"
                v-for="index in pagination.last"
                :key="index"
                :class="{'on': index == pagination.current}"
                @click="pagination.gotoPage(index)">
                {{ index }}
              </a>
            </div>

            <!-- select button -->
            <button class="modal__set-button"
              @click="setLocation">
              {{ buttonText }}
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
      libraries: ['services'],
      mapObject: null,
      daumMapsObject: {},
      searchPlaceObject: {},
      keyword: '',
      placeLists: [],
      markers: [],
      selected: 0,
      placeholdText: '키워드를 입력해 주세요(건물명, 주소..)',
      pagination: {},
      buttonText: '해당 위치로 설정',
      infowindow: null,
    };
  },
  methods: {
    onLoad(map) {
      // 지도의 현재 영역을 얻어옵니다
      const bounds = map.getBounds();

      // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
      const boundsStr = bounds.toString();
      window.console.log('Daum Map Loaded', boundsStr);

      // 생성된 지도 객체 바인딩
      this.mapObject = map;
      // daum.maps 객체를 window 속성으로 매핑
      this.daumMapsObject = window.daum.maps;
      // 장소 검색 객체 바인딩
      this.searchPlaceObject = new this.daumMapsObject.services.Places();
      // 마커위에 표출할 인포윈도우
      this.infowindow = new this.daumMapsObject.InfoWindow({ zIndex: 1 });
    },
    searchPlaces() {
      if (!this.keyword.replace(/^\s+|\s+$/g, '')) {
        this.changePlaceholdText('키워드를 입력해 주세요(건물명, 주소..)');
      } else {
        // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
        this.searchPlaceObject.keywordSearch(this.keyword, this.placesSearchCB);

        this.buttonText = '해당 위치로 설정';
      }
    },
    placesSearchCB(data, status, pagination) {
      if (status === this.daumMapsObject.services.Status.OK) {
        // 지역 목록을 가져옵니다
        this.placeLists = data;
        // 마커를 표출합니다
        this.displayPlaces();
        // 페이지 번호를 가져옵니다
        this.pagination = pagination;
      } else if (status === this.daumMapsObject.services.Status.ZERO_RESULT) {
        this.changePlaceholdText('검색 결과가 존재하지 않습니다');
      } else if (status === this.daumMapsObject.services.Status.ERROR) {
        this.changePlaceholdText('검색 결과 중 오류가 발생했습니다');
      }
    },
    displayPlaces() {
      const bounds = new this.daumMapsObject.LatLngBounds();
      this.removeAllMarker();
      for (let i = 0; i < this.placeLists.length; i += 1) {
        // 마커를 생성하고 지도에 표시합니다
        const placePosition = new this.daumMapsObject.LatLng(
          this.placeLists[i].y,
          this.placeLists[i].x,
        );
        const marker = this.addMarker(placePosition);

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커에 마우스오버시 인포윈도우표시
        this.daumMapsObject.event.addListener(marker, 'mouseover', () => {
          this.displayInfowindow(marker, this.placeLists[i].place_name);
        });

        this.daumMapsObject.event.addListener(marker, 'mouseout', () => {
          this.infowindow.close();
        });

        // 마커 클릭시 해당 장소 선택
        this.daumMapsObject.event.addListener(marker, 'click', () => {
          this.selectPlace(i);
        });
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.mapObject.setBounds(bounds);
    },
    addMarker(position) {
      const imageSrc = 'https://github.com/Yangaboo/Albago/blob/Front-End/src/assets/map_mark.png?raw=true';
      const imageSize = new this.daumMapsObject.Size(36, 37);
      const imageOption = {
        offset: new this.daumMapsObject.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      };
      const markerImage = new this.daumMapsObject.MarkerImage(imageSrc, imageSize, imageOption);
      const marker = new this.daumMapsObject.Marker({
        position, // 마커의 위치
        image: markerImage,
      });

      marker.setMap(this.mapObject); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removeAllMarker() {
      this.markers.forEach(marker => marker.setMap(null));
      this.markers = [];
    },
    displayInfowindow(marker, title) {
      const content = `<div style="padding:5px;z-index:1;">${title}</div>`;

      this.infowindow.setContent(content);
      this.infowindow.open(this.mapObject, marker);
    },
    changePlaceholdText(text) {
      this.keyword = '';
      this.placeholdText = text;
    },
    selectPlace(index) {
      this.selected = index;
    },
    setLocation() {
      if (this.placeLists) {
        const selectedPlace = this.placeLists[this.selected];
        window.localStorage.setItem('place_name', selectedPlace.place_name);
        window.localStorage.setItem('address_name', selectedPlace.address_name);
        window.localStorage.setItem('road_address_name', selectedPlace.road_address_name);
        window.localStorage.setItem('x', selectedPlace.x);
        window.localStorage.setItem('y', selectedPlace.y);
        this.$EventBus.$emit('setLocate');
        this.$emit('closeModal');
      }
    },
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/setting';
$z-index-set-loc: 1;
$z-index-mask: 2;
$z-index-modal: 3;
$color-main: #494f5c;

@mixin set-color($color1, $color2) {
  background-color: $color1;
  color: $color2;
  .check {
    background-color: $color2;
  }
  &.selected {
    .check {
      color: $color1;
    }
  }
}

.set-loc {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  z-index: $z-index-set-loc;
  @include e('mask') {
    position: absolute;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: $z-index-mask;
  }
}
.modal {
  $width-modal: 1100px;
  $height-modal: 700px;
  $radius: 5px;
  $side-padding: 60px;
  $name-padding-top: 30px;

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
    width: 100%;
    background-color: $color-main;
    height: 80px;
    padding: {
      top: $name-padding-top;
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
  }
  @include e('close-button') {
    position: absolute;
    top: $name-padding-top;
    right: $side-padding;
    font-size: 30px;
    font-weight: lighter;
    line-height: inherit;
    color: inherit;
    background: none;
    border: none;
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
  }
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
        left: 70px;
        top: 20px;
        bottom: 20px;
      }
      font-size: 18px;
      line-height: 20px;
      background: url('../assets/search.png') no-repeat left 20px center;
      background-size: auto 50%;
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
  }
  @include e('result-list') {
    height: 270px;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.16);
    overflow-y: scroll;
  }
  @include e('result-item') {
    height: 50px;
    position: relative;
    padding: {
      left: 50px;
      top: 10px;
      bottom: 10px;
    }
    background-size: 30px;
    background-position: left 10px center;
    background-repeat: no-repeat;
    &:nth-child(even) {
      @include set-color(#fff, $color-main);
      background-image: url('../assets/set-location__1.png');
    }
    &:nth-child(odd) {
      @include set-color($color-main, #fff);
      background-image: url('../assets/set-location__2.png');
    }
    @include e('name') {
      font-size: 13px;
      line-height: 20px;
    }
    @include e('address-name') {
      font-size: 8px;
      line-height: 10px;
    }
    @include e('check') {
      position: absolute;
      top: 10px;
      right: 30px;
      width: 30px;
      height: 30px;
      box-shadow: 0 6px 6px 0 rgba(0, 0, 0, 0.16);
      border-radius: 100%;
      text-align: center;
      line-height: 30px;
      font-size: 20px;
    }
  }
  @include e('pagination') {
    text-align: center;
    height: 20px;
    margin-top: 10px;
    @include e('number') {
      display: inline-block;
      height: 100%;
      width: 20px;
      font-size: 15px;
      font-weight: bolder;
      line-height: 20px;
      color: rgb(170, 170, 170);
      &.on {
        color: #000;
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
    transition: background-color 0.1s;
    &:hover {
      background-color: $color-main / 5 * 6;
    }
    &:active {
      padding-top: 10px;
      color: #ddd;
    }
  }
  &-enter-active, &-leave-active {
    transition: opacity 0.5s ease;
  }
  &-enter, &-leave-to {
    opacity: 0;
  }
}
</style>
