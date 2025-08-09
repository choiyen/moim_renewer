import { useEffect } from "react";

// 카카오맵 전역 객체 선언
declare global {
  interface Window {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    kakao: any;
  }
}

const MoimMap = () => {
  useEffect(() => {
    const script = document.createElement("script");
    script.src =
      "//dapi.kakao.com/v2/maps/sdk.js?appkey=28f6c27651565b4d9090a3f542951474&autoload=false";
    script.async = true;

    script.onload = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById("map");
        const options = {
          center: new window.kakao.maps.LatLng(37.5665, 126.978), // 서울 중심
          level: 7,
        };
        const map = new window.kakao.maps.Map(container, options);

        // 테스트용 모임 위치 데이터
        const moimList = [
          { title: "농구 모임", lat: 37.5665, lng: 126.978 },
          { title: "축구 모임", lat: 37.57, lng: 126.982 },
        ];

        moimList.forEach((moim) => {
          const marker = new window.kakao.maps.Marker({
            map,
            position: new window.kakao.maps.LatLng(moim.lat, moim.lng),
          });

          // 마커 클릭 이벤트
          const infowindow = new window.kakao.maps.InfoWindow({
            content: `<div style="padding:5px;">${moim.title}</div>`,
          });
          window.kakao.maps.event.addListener(marker, "click", () => {
            infowindow.open(map, marker);
          });
        });
      });
    };

    document.head.appendChild(script);
  }, []);

  return (
    <div>
      <h2>지역 별 모임 지도</h2>
      <div
        id="map"
        style={{ width: "100%", height: "500px", border: "1px solid #ccc" }}
      />
    </div>
  );
};

export default MoimMap;
