import type { MoimData, MoimDetailStore } from "./State";
import star from "../../public/vite.svg";

interface MoimDataDummy {
  MoimData: MoimData;
  MoimDetail: MoimDetailStore;
}

export interface Posts {
  Nickname: string;
  Title: string;
  consultCategoryId: number;
  consultComment: string;
  createDate: string;
  moimConsultId: string;
  viewcount: number;
}

export const ConsultType = {
  SERVICE: "서비스",
  FEATURE: "기능",
  REVIEW: "이용후기",
} as const;

export const consultPosts = [
  {
    id: 1,
    title: "모임 삭제가 안돼요",
    author: "홍길동",
    date: "2025-08-01",
    comment:
      "모임을 삭제하려고 시도했는데 버튼을 눌러도 아무 반응이 없네요. 여러 번 시도했지만 동일합니다. 혹시 다른 방법이 있는지 궁금합니다. 빠른 확인과 수정 부탁드립니다.",
    ConsultType: "서비스",
  },
  {
    id: 2,
    title: "비밀번호 변경은 어디서 하나요?",
    author: "김영희",
    date: "2025-07-30",
    comment:
      "회원정보 메뉴에서 비밀번호를 바꾸고 싶은데 위치를 찾기 어렵습니다. 단계별 안내가 있으면 좋겠습니다. 혹시 화면에 표시되는 가이드가 있다면 참고하고 싶어요. 확인 부탁드립니다.",
    ConsultType: "서비스",
  },
  {
    id: 3,
    title: "모임장 권한을 넘기고 싶어요",
    author: "이철수",
    date: "2025-07-28",
    comment:
      "현재 모임장의 권한을 다른 사람에게 넘기고 싶은데 방법을 잘 모르겠습니다. 메뉴를 찾아봐도 옵션이 보이지 않네요. 자세한 안내가 있으면 좋겠습니다. 확인 부탁드립니다.",
    ConsultType: "서비스",
  },
];

export const featureRequests = [
  {
    id: 4,
    title: "모임에 공지 고정 기능이 있었으면 해요",
    author: "박지은",
    date: "2025-07-31",
    comment:
      "중요한 공지를 항상 상단에 고정할 수 있는 기능이 있으면 좋겠습니다. 특히 이벤트나 안내 사항을 빠르게 확인할 수 있어 편리할 것 같아요. 사용자 입장에서 큰 도움이 될 것 같습니다. 구현 검토 부탁드립니다.",
    ConsultType: "기능",
  },
  {
    id: 5,
    title: "다크모드도 지원해주세요!",
    author: "최윤호",
    date: "2025-07-29",
    comment:
      "밤에 앱을 사용하면 눈이 많이 피로합니다. 다크모드를 지원하면 장시간 사용에도 편안할 것 같습니다. 시각적으로도 훨씬 깔끔해질 것 같아요. 기능 추가 검토 부탁드립니다.",
    ConsultType: "기능",
  },
];

export const userReviews = [
  {
    id: 6,
    title: "첫 모임 참여했는데 정말 좋았어요",
    author: "익명",
    date: "2025-07-25",
    comment:
      "첫 모임이라 조금 긴장했는데 분위기가 너무 좋았습니다. 사람들도 친절하고 자연스럽게 어울릴 수 있었습니다. 진행 방식도 깔끔하고 만족스러웠습니다. 다음 모임도 기대됩니다.",
    ConsultType: "이용후기",
  },
  {
    id: 7,
    title: "덕분에 좋은 사람들 만났어요!",
    author: "김태현",
    date: "2025-07-22",
    comment:
      "취미가 비슷한 사람들과 쉽게 친해질 수 있었습니다. 운영진도 세심하게 챙겨주셔서 불편함이 없었어요. 즐거운 시간을 보내고 돌아갈 수 있었습니다. 다음 모임도 꼭 참여하고 싶습니다.",
    ConsultType: "이용후기",
  },
  {
    id: 8,
    title: "오프라인 모임 분위기 최고였어요",
    author: "이은지",
    date: "2025-07-20",
    comment:
      "온라인과는 다른 따뜻한 분위기였습니다. 참가자 모두 적극적이고 친절해서 즐겁게 시간을 보냈습니다. 공간과 환경도 편안하게 마련되어 좋았습니다. 다음 모임도 기대됩니다.",
    ConsultType: "이용후기",
  },
];

export type ConsultType = (typeof ConsultType)[keyof typeof ConsultType];

export const MoimDataDummys: MoimDataDummy = {
  MoimData: {
    image: star,
    title: "주말 농구 동호회",
    maxpeople: 15,
    description:
      "농구를 좋아하는 사람들이 모여 실력을 키우고 친목을 다지는 주말 모임입니다.",
    tag: ["농구", "스포츠", "운동", "취미"],
    isOnline: false,
    organizer: "김유성",
    expirationDate: new Date(),
    evenDate: new Date(new Date().setDate(new Date().getDate() + 7)),
    location: "경상남도 진주",
    categoryDetail: "농구",
    moimId: "BASKET2025",
  },
  MoimDetail: {
    moimdetailId: 1,
    Members: ["박민수", "이수진", "정현우"],
    moimId: "BASKET2025",
    content:
      "매주 토요일 오후 2시에 시내 체육관에서 연습 경기를 진행합니다. 초보부터 상급자까지 모두 환영하며, 가벼운 트레이닝과 게임 후 뒷풀이도 함께합니다.",
    minPeople: 5,
    Pay: 10000,
    Approval: false,
  },
};
