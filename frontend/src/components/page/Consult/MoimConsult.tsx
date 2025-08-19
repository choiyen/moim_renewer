import { FileText, Lightbulb, Star } from "lucide-react";
import type { JSX } from "react";
import { useNavigate } from "react-router-dom";
import {
  consultPosts,
  featureRequests,
  userReviews,
} from "../../../types/MoimDataDummy";

const BoardSection = ({
  icon,
  title,
  posts,
}: {
  icon: JSX.Element;
  title: string;
  posts: typeof consultPosts;
}) => {
  const navigate = useNavigate();

  return (
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
            <tr
              key={post.id}
              className="border-t hover:bg-gray-50 cursor-pointer"
              onClick={() => navigate(`/consult/select/${post.id}`)} // ← 여기서 navigate 실행
            >
              <td className="p-3 text-blue-600">{post.title}</td>
              <td className="p-3">{post.author}</td>
              <td className="p-3">{post.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

const MoimConsult = () => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate("/consult/insert");
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
