import { FileText, Lightbulb, Star } from "lucide-react";
import type { JSX } from "react";
import { useNavigate } from "react-router-dom";

const consultPosts = [
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

const featureRequests = [
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

const userReviews = [
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

const BoardSection = ({
  icon,
  title,
  posts,
}: {
  icon: JSX.Element;
  title: string;
  posts: typeof consultPosts;
}) => (
  <div className="mt-12">
    <div className="flex items-center gap-2 mb-6">
      {icon}
      <h2 className="text-xl font-semibold">{title}</h2>
    </div>

    <table className="w-full table-auto border border-gray-200">
      <thead className="bg-gray-100 text-gray-700 text-left">
        <tr>
          <th className="p-3 w-1/2">제목</th>
          <th className="p-3 w-1/4">작성자</th>
          <th className="p-3 w-1/4">작성일</th>
        </tr>
      </thead>
      <tbody>
        {posts.map((post) => (
          <tr key={post.id} className="border-t hover:bg-gray-50">
            <td className="p-3 text-blue-600 cursor-pointer">{post.title}</td>
            <td className="p-3">{post.author}</td>
            <td className="p-3">{post.date}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
);

const MoimConsult = () => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/insert");
  };
  return (
    <div className="max-w-3xl mx-auto px-4 py-10">
      <BoardSection
        icon={<FileText size={28} className="text-blue-600" />}
        title="서비스 운영 상담 게시판"
        posts={consultPosts}
      />
      <BoardSection
        icon={<Lightbulb size={28} className="text-yellow-500" />}
        title="기능 개선 의견 게시판"
        posts={featureRequests}
      />
      <BoardSection
        icon={<Star size={28} className="text-pink-500" />}
        title="이용 후기 & 경험 공유 게시판"
        posts={userReviews}
      />
      <div className="mt-4 text-right">
        <button
          className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition"
          onClick={() => handleClick()}
        >
          글쓰기
        </button>
      </div>
    </div>
  );
};

export default MoimConsult;
