export interface MoimType {
  id: number;
  Category: string;
  SubCategory: MoimCategorySub[];
}

export interface MoimCategorySub {
  id: string;
  SubCategoryName: string;
}

export interface MoimCategorySub {
  id: string; // 문자열
  SubCategoryName: string;
}

export interface MoimType {
  id: number; // 숫자
  Category: string;
  SubCategory: MoimCategorySub[];
}

export const hopper: MoimType[] = [
  {
    id: 1,
    Category: "파티",
    SubCategory: [
      { id: "1", SubCategoryName: "컨셉파티" },
      { id: "2", SubCategoryName: "홈파티" },
      { id: "3", SubCategoryName: "내향인파티" },
      { id: "4", SubCategoryName: "뮤직파티" },
      { id: "5", SubCategoryName: "푸드파이터" },
      { id: "6", SubCategoryName: "외인파티" },
      { id: "7", SubCategoryName: "생일파티" },
      { id: "8", SubCategoryName: "포틀릭파티" },
      { id: "9", SubCategoryName: "기타" },
    ],
  },
  {
    id: 2,
    Category: "푸드 & 드링크",
    SubCategory: [
      { id: "1", SubCategoryName: "디저트" },
      { id: "2", SubCategoryName: "맛집투어" },
      { id: "3", SubCategoryName: "술" },
      { id: "4", SubCategoryName: "비건" },
      { id: "5", SubCategoryName: "와인" },
      { id: "6", SubCategoryName: "홈쿡방" },
      { id: "7", SubCategoryName: "홈베이킹" },
      { id: "8", SubCategoryName: "티룸" },
      { id: "9", SubCategoryName: "파인다이닝" },
      { id: "10", SubCategoryName: "페어링" },
      { id: "11", SubCategoryName: "기타" },
    ],
  },
  {
    id: 3,
    Category: "스포츠",
    SubCategory: [
      { id: "1", SubCategoryName: "축구" },
      { id: "2", SubCategoryName: "농구" },
      { id: "3", SubCategoryName: "야구" },
      { id: "4", SubCategoryName: "배드민턴" },
      { id: "5", SubCategoryName: "테니스" },
      { id: "6", SubCategoryName: "탁구" },
      { id: "7", SubCategoryName: "등산" },
      { id: "8", SubCategoryName: "헬스" },
      { id: "9", SubCategoryName: "러닝" },
      { id: "10", SubCategoryName: "요가/필라테스" },
      { id: "11", SubCategoryName: "기타" },
    ],
  },
  {
    id: 4,
    Category: "음악",
    SubCategory: [
      { id: "1", SubCategoryName: "밴드" },
      { id: "2", SubCategoryName: "합창" },
      { id: "3", SubCategoryName: "작곡" },
      { id: "4", SubCategoryName: "노래방" },
      { id: "5", SubCategoryName: "기타연주" },
      { id: "6", SubCategoryName: "피아노" },
      { id: "7", SubCategoryName: "드럼" },
      { id: "8", SubCategoryName: "클래식" },
      { id: "9", SubCategoryName: "힙합" },
      { id: "10", SubCategoryName: "EDM" },
      { id: "11", SubCategoryName: "기타" },
    ],
  },
  {
    id: 5,
    Category: "예술",
    SubCategory: [
      { id: "1", SubCategoryName: "미술" },
      { id: "2", SubCategoryName: "사진" },
      { id: "3", SubCategoryName: "공예" },
      { id: "4", SubCategoryName: "조각" },
      { id: "5", SubCategoryName: "캘리그라피" },
      { id: "6", SubCategoryName: "메이크업" },
      { id: "7", SubCategoryName: "디자인" },
      { id: "8", SubCategoryName: "패션" },
      { id: "9", SubCategoryName: "영화감상" },
      { id: "10", SubCategoryName: "드라마" },
      { id: "11", SubCategoryName: "기타" },
    ],
  },
  {
    id: 6,
    Category: "스터디",
    SubCategory: [
      { id: "1", SubCategoryName: "코딩" },
      { id: "2", SubCategoryName: "영어" },
      { id: "3", SubCategoryName: "자격증" },
      { id: "4", SubCategoryName: "독서" },
      { id: "5", SubCategoryName: "토론" },
      { id: "6", SubCategoryName: "경제" },
      { id: "7", SubCategoryName: "철학" },
      { id: "8", SubCategoryName: "심리학" },
      { id: "9", SubCategoryName: "과학" },
      { id: "10", SubCategoryName: "역사" },
      { id: "11", SubCategoryName: "기타" },
    ],
  },
];
