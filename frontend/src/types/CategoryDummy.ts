import { FileText, Lightbulb, Star } from "lucide-react";
import {
  consultPosts,
  featureRequests,
  userReviews,
  type Posts,
} from "./MoimDataDummy";

interface CategoryDummy {
  id: number;
  Category: string;
}
interface SubCategoryDummy {
  id: number;
  SubCategory: string;
  CategoryId: number;
}

interface CategoryWithSub {
  Category: CategoryDummy;
  SubCategories: SubCategoryDummy[];
}

export const categories: CategoryWithSub[] = [
  {
    Category: { id: 1, Category: "파티" },
    SubCategories: [
      { id: 1, SubCategory: "컨셉파티", CategoryId: 1 },
      { id: 2, SubCategory: "홈파티", CategoryId: 1 },
      { id: 3, SubCategory: "내향인파티", CategoryId: 1 },
      { id: 4, SubCategory: "뮤직파티", CategoryId: 1 },
      { id: 5, SubCategory: "푸드파이터", CategoryId: 1 },
      { id: 6, SubCategory: "외인파티", CategoryId: 1 },
      { id: 7, SubCategory: "생일파티", CategoryId: 1 },
      { id: 8, SubCategory: "포틀릭파티", CategoryId: 1 },
      { id: 9, SubCategory: "기타", CategoryId: 1 },
    ],
  },
  {
    Category: { id: 2, Category: "푸드 & 드링크" },
    SubCategories: [
      { id: 10, SubCategory: "디저트", CategoryId: 2 },
      { id: 11, SubCategory: "맛집투어", CategoryId: 2 },
      { id: 12, SubCategory: "술", CategoryId: 2 },
      { id: 13, SubCategory: "비건", CategoryId: 2 },
      { id: 14, SubCategory: "와인", CategoryId: 2 },
      { id: 15, SubCategory: "홈쿡방", CategoryId: 2 },
      { id: 16, SubCategory: "홈베이킹", CategoryId: 2 },
      { id: 17, SubCategory: "티룸", CategoryId: 2 },
      { id: 18, SubCategory: "파인다이닝", CategoryId: 2 },
      { id: 19, SubCategory: "페어링", CategoryId: 2 },
      { id: 20, SubCategory: "기타", CategoryId: 2 },
    ],
  },
  {
    Category: { id: 3, Category: "자기개발" },
    SubCategories: [
      { id: 21, SubCategory: "독서", CategoryId: 3 },
      { id: 22, SubCategory: "스터디", CategoryId: 3 },
      { id: 23, SubCategory: "브랜딩", CategoryId: 3 },
      { id: 24, SubCategory: "커리어", CategoryId: 3 },
      { id: 25, SubCategory: "사이드프로젝트", CategoryId: 3 },
      { id: 26, SubCategory: "창작", CategoryId: 3 },
      { id: 27, SubCategory: "대화", CategoryId: 3 },
      { id: 28, SubCategory: "스피치", CategoryId: 3 },
      { id: 29, SubCategory: "기타", CategoryId: 3 },
    ],
  },
];

interface ConsultCategory {
  id: number;
  ConsultCategory: string;
  icon?: React.ComponentType<React.SVGProps<SVGSVGElement>>;
  post: Posts[];
}

export const consultCategories: ConsultCategory[] = [
  {
    id: 1,
    ConsultCategory: "서비스 운영",
    icon: FileText,
    post: consultPosts,
  },
  {
    id: 2,
    ConsultCategory: "이용 후기 & 경험",
    icon: Lightbulb,
    post: featureRequests,
  },
  {
    id: 3,
    ConsultCategory: "이용 후기",
    icon: Star,
    post: userReviews,
  },
];
