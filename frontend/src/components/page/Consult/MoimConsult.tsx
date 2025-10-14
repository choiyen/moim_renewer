import { FileText } from "lucide-react";
import type { JSX } from "react";
import { useNavigate } from "react-router-dom";
import { type Posts } from "../../../types/MoimDataDummy";
import {
  consultCategories,
  type ConsultCategory,
} from "../../../types/CategoryDummy";
import React, { useEffect, useState } from "react";
import { GET } from "../../comon/axios/axiosInstance";
import { ConsultCategorying } from "./function/Consult";
import PrivateRoute from "../../comon/frame/PrivateRoute";

const BoardSection = ({
  icon,
  title,
  posts,
}: {
  icon: JSX.Element;
  title: string;
  posts: Posts[];
}) => {
  const navigate = useNavigate();
  return (
    <div className="mt-12">
      <div className="flex items-center gap-2 mb-3">
        <span className="flex-shrink-0">{icon}</span>
        <h2 className="text-xl font-semibold">{title}</h2>
      </div>

      <table className="w-full table-auto border border-gray-200">
        <thead className="bg-gray-100 text-gray-700 text-left">
          <tr>
            <th className="p-3 w-3/6">제목</th>
            <th className="p-3 w-2/6">작성일/수정일</th>
            <th className="p-3 w-1/6">작성자</th>
          </tr>
        </thead>
        <tbody>
          {posts.length > 0 ? (
            posts.map((post) => (
              <tr
                key={post.moimConsultId}
                className="border-t hover:bg-gray-50 cursor-pointer"
                onClick={() =>
                  navigate(`/consult/select/${post.moimConsultId}`)
                } // ← 여기서 navigate 실행
              >
                <td className="p-3 text-blue-600">{post.Title}</td>
                <td className="p-3">
                  {new Date(post.createDate).toLocaleString("ko-KR")}
                </td>
                <td className="p-3">{post.Nickname}</td>
              </tr>
            ))
          ) : (
            <tr className="border-t">
              <td className="p-3 text-center" colSpan={3}>
                저장된 데이터 없습니다
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

const MoimConsult = () => {
  const navigate = useNavigate();
  const [consultCategory, setConsultCategories] = useState<ConsultCategory[]>();

  useEffect(() => {
    GET({
      url: "/ConsultCategory",
    })
      .then(async (res) => {
        const updated = await Promise.all(
          consultCategories.map(async (item, index) => ({
            ...item,
            ConsultCategory:
              res.data[index]?.consultType ?? item.ConsultCategory,
            post: await ConsultCategorying(res.data[index]?.consultCategoryId),
            // 보통 .data를 꺼내야 실제 서버 응답 본문이 들어감
          }))
        );
        console.log(updated);
        setConsultCategories(updated);
      })
      .catch((err) => {
        console.log(err.response.data);
        if (err.response.data.resultType === "empty") {
          alert("현재 상담 기능은 준비 중입니다. 전화번호로 문의 바랍니다.");
          navigate("/home");
        }
      });
  }, []);

  const handleClick = () => {
    navigate("/consult/insert");
  };

  return (
    <PrivateRoute name={"상담 게시판"}>
      <>
        <div className="max-w-4xl mx-auto px-4 py-10">
          {consultCategory &&
            consultCategory.map((category) => (
              <BoardSection
                key={category.id}
                icon={React.createElement(category.icon ?? FileText, {
                  width: 28,
                  height: 28,
                  className: "text-blue-600",
                })}
                title={category.ConsultCategory}
                posts={category.post != null ? category.post : []}
              />
            ))}
          <div className="mt-4 text-right">
            <button
              className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition"
              onClick={() => handleClick()}
            >
              글쓰기
            </button>
          </div>
        </div>
      </>
    </PrivateRoute>
  );
};

export default MoimConsult;
