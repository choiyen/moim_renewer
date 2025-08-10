import type { MoimData, MoimDetailStore } from "./State";
import star from "../../comon/frame/image/star.png";

interface MoimDataDummy {
  MoimData: MoimData;
  MoimDetail: MoimDetailStore;
}

export const consultPosts = [
  { id: 1, title: "모임 삭제가 안돼요", author: "홍길동", date: "2025-08-01" },
  {
    id: 2,
    title: "비밀번호 변경은 어디서 하나요?",
    author: "김영희",
    date: "2025-07-30",
  },
  {
    id: 3,
    title: "모임장 권한을 넘기고 싶어요",
    author: "이철수",
    date: "2025-07-28",
  },
];

export const featureRequests = [
  {
    id: 1,
    title: "모임에 공지 고정 기능이 있었으면 해요",
    author: "박지은",
    date: "2025-07-31",
  },
  {
    id: 2,
    title: "다크모드도 지원해주세요!",
    author: "최윤호",
    date: "2025-07-29",
  },
];

export const userReviews = [
  {
    id: 1,
    title: "첫 모임 참여했는데 정말 좋았어요",
    author: "익명",
    date: "2025-07-25",
  },
  {
    id: 2,
    title: "덕분에 좋은 사람들 만났어요!",
    author: "김태현",
    date: "2025-07-22",
  },
  {
    id: 3,
    title: "오프라인 모임 분위기 최고였어요",
    author: "이은지",
    date: "2025-07-20",
  },
];

export const ConsultType = {
  SERVICE: "서비스",
  FEATURE: "기능",
  REVIEW: "이용후기",
} as const;

export type ConsultType = (typeof ConsultType)[keyof typeof ConsultType];

interface MoimCousultDummy {
  ConsultId: string;
  ConsultType: ConsultType;
  ConsultTitle: string;
  ConsultNickname: string;
  ConsultContent: string;
}

export const MoimConsultDummy: MoimCousultDummy[] = [
  {
    ConsultId: "SERVICE1",
    ConsultType: ConsultType.SERVICE,
    ConsultTitle: "모임 삭제가 안돼요",
    ConsultNickname: "홍길동",
    ConsultContent:
      "모임을 삭제하려고 하는데 삭제 버튼이 작동하지 않습니다. 해결 방법을 알려주세요.",
  },
  {
    ConsultId: "SERVICE2",
    ConsultType: ConsultType.SERVICE,
    ConsultTitle: "비밀번호 변경은 어디서 하나요?",
    ConsultNickname: "김영희",
    ConsultContent:
      "계정 비밀번호를 변경하고 싶은데 어디서 할 수 있는지 안내 부탁드립니다.",
  },
  {
    ConsultId: "SERVICE3",
    ConsultType: ConsultType.SERVICE,
    ConsultTitle: "모임장 권한을 넘기고 싶어요",
    ConsultNickname: "이철수",
    ConsultContent:
      "현재 모임장 권한을 다른 사람에게 넘기고 싶은데 방법이 있나요?",
  },
  {
    ConsultId: "FEATURE1",
    ConsultType: ConsultType.FEATURE,
    ConsultTitle: "모임에 공지 고정 기능이 있었으면 해요",
    ConsultNickname: "박지은",
    ConsultContent:
      "중요한 공지를 상단에 고정할 수 있는 기능이 추가되면 좋겠습니다.",
  },
  {
    ConsultId: "FEATURE2",
    ConsultType: ConsultType.FEATURE,
    ConsultTitle: "다크모드도 지원해주세요!",
    ConsultNickname: "최윤호",
    ConsultContent:
      "밤에 눈이 편하도록 다크모드를 제공해 주시면 감사하겠습니다.",
  },
  {
    ConsultId: "REVIEW1",
    ConsultType: ConsultType.REVIEW,
    ConsultTitle: "첫 모임 참여했는데 정말 좋았어요",
    ConsultNickname: "익명",
    ConsultContent:
      "처음 참여한 모임이었는데 분위기도 좋고 사람들도 친절했습니다.",
  },
  {
    ConsultId: "REVIEW2",
    ConsultType: ConsultType.REVIEW,
    ConsultTitle: "덕분에 좋은 사람들 만났어요!",
    ConsultNickname: "김태현",
    ConsultContent:
      "이 서비스를 통해 좋은 분들을 만나서 즐거운 시간을 보냈습니다.",
  },
  {
    ConsultId: "REVIEW3",
    ConsultType: ConsultType.REVIEW,
    ConsultTitle: "오프라인 모임 분위기 최고였어요",
    ConsultNickname: "이은지",
    ConsultContent:
      "오프라인 모임의 분위기가 매우 좋았고 다시 참여하고 싶습니다.",
  },
];

export const MoimDataDummys: MoimDataDummy = {
  MoimData: {
    image: star,
    title: "주말 농구 동호회",
    maxpeople: 15,
    description:
      "농구를 좋아하는 사람들이 모여 실력을 키우고 친목을 다지는 주말 모임입니다.",
    tag: ["농구", "스포츠", "운동", "취미"],
    isOnline: false,
    organizer: "",
    expirationDate: new Date(),
    evenDate: new Date(),
    location: "",
    category: "",
    categoryDetail: "",
  },
  MoimDetail: {
    moimdetailId: 1,
    Members: ["박민수", "이수진", "정현우"],
    moimId: "BASKET2025",
    content:
      "매주 토요일 오후 2시에 시내 체육관에서 연습 경기를 진행합니다. 초보부터 상급자까지 모두 환영하며, 가벼운 트레이닝과 게임 후 뒷풀이도 함께합니다.",
    minPeople: 5,
    Pay: 10000,
  },
};
