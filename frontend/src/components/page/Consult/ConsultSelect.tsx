import { useNavigate, useParams } from "react-router-dom";
import { type Posts } from "../../../types/MoimDataDummy";
import styled from "@emotion/styled";
import { FaArrowLeft } from "react-icons/fa";

import styleds from "../Consult/Consult.module.css";
import { useEffect, useState } from "react";
import { GET, POST } from "../../comon/axios/axiosInstance";

const ConsultContainer = styled.div`
  display: flex;
  flex-direction: column;
  min-height: calc(100vh - 185px);
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  padding: 100px;
`;

const WhiteContainer = styled.div`
  width: 70vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
  background-color: aliceblue;
  padding: 70px;
  border-radius: 15px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
`;

interface CommentType {
  name: string;
  comment: string;
  commentDate: Date;
  star: string;
}

const ConsultSelect = () => {
  const { id } = useParams<{ id: string }>(); // id는 문자열로 들어옴
  const [targetPost, settargetPost] = useState<Posts>();
  const [comment, setcomment] = useState<CommentType[]>([]);
  const [commenting, setcommenting] = useState<string>("");
  const user = localStorage.getItem("user");

  const nativeGate = useNavigate();
  const settingcomment = () => {
    if (user) console.log(JSON.parse(user));
    if (user && JSON.parse(user).nickname !== undefined) {
      POST({
        url: "dddddddddddddddddd",
      });

      setcomment([
        ...comment,
        {
          name: JSON.parse(user).nickname,
          comment: commenting,
          commentDate: new Date(),
          star: JSON.parse(user).profileImg,
        },
      ]);
      setcommenting("");
    } else {
      alert("댓글 작성 동작 : 로그인 정보 없음");
    }
  };
  useEffect(() => {
    GET({
      url: "/consult",
      params: {
        ConsultId: id,
      },
    }).then((res) => {
      console.log(res.data[0]);
      settargetPost(res.data[0]);
    });
  }, []);

  const Backed = () => {
    nativeGate("/consult/");
  };
  const Removing = () => {
    const confirmed = window.confirm("정말 삭제하시겠습니까?");
    if (confirmed) {
      // 삭제 로직 실행
      alert("삭제되었습니다.");
    } else {
      // 취소했을 때 동작
      alert("삭제가 취소되었습니다.");
    }
  };
  return (
    <ConsultContainer>
      <div
        style={{
          width: "100%",
          padding: "25px",
          marginBottom: "20px",
          display: "flex",
          justifyContent: "flex-end", // 오른쪽 정렬
        }}
      >
        <button
          className="flex items-center gap-2 p-3 border bg-gray-200 rounded shadow-md hover:bg-gray-300 hover:shadow-lg transition-all duration-200"
          onClick={() => {
            Backed();
          }}
        >
          <FaArrowLeft size={20} />
          이전
        </button>
      </div>
      <WhiteContainer>
        <div className={styleds.postHeader}>
          <span className={styleds.postType}>
            {targetPost?.consultCategoryId} - {targetPost?.moimConsultId}
          </span>
          <span className={styleds.postTitle}>{targetPost?.Title}</span>
        </div>

        <div className={styleds.postMeta}>
          <span>{targetPost?.Nickname}</span>
          <span>{targetPost?.createDate}</span>
        </div>

        <div className={styleds.postStats}>
          <span>추천 : 0</span>
          <span>조회 : 0</span>
          <span>댓글 : 0</span>
        </div>

        <div
          className={styleds.postComment}
          dangerouslySetInnerHTML={{ __html: targetPost?.consultComment ?? "" }}
        />
        <div className={styleds.commentForm}>
          <input
            type="text"
            placeholder="댓글을 입력하세요"
            value={commenting}
            onChange={(e) => setcommenting(e.target.value)}
          />
          <button onClick={() => settingcomment()}>입력</button>
        </div>
        <div className={styleds.commentRemoveContainer}>
          <button>수정</button>
          <button onClick={() => Removing()}>삭제</button>
        </div>
      </WhiteContainer>
      {comment.length > 0 ? (
        <div className={styleds.commentList}>
          {comment.map((c, idx) => (
            <div key={idx} className={styleds.commentItem}>
              <div
                style={{
                  display: "flex",
                  alignItems: "flex-start",
                  justifyContent: "space-between",
                }}
              >
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    gap: "10px",
                    backgroundColor: "#ffe4e1",
                    padding: "15px",
                    borderRadius: "15%",
                  }}
                >
                  <img src={c.star} className={styleds.commentimg} />
                  <span style={{ fontSize: "25px" }}>{c.name}</span>
                </div>
                <span
                  style={{
                    textAlign: "center",
                    width: "300px",
                    padding: "10px",
                    boxShadow: "0 4px 10px rgba(0, 0, 0, 0.15)",
                  }}
                >
                  {c.commentDate.toLocaleString()}
                </span>
              </div>
              <span
                style={{
                  textAlign: "center",
                  fontSize: "25px",
                  border: "1px solid #ccc",
                  padding: "25px",
                  margin: "25px 0px",
                  borderRadius: "15px",
                  backgroundColor: "beige",
                }}
              >
                {c.comment}
              </span>
              <div
                style={{
                  display: "flex",
                  justifyContent: "flex-end",
                  gap: "15px",
                }}
              >
                <button
                  className={styleds.commentbutton}
                  style={{ backgroundColor: "green" }}
                >
                  댓글 수정
                </button>
                <button className={styleds.commentbutton}>댓글 삭제</button>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div className={styleds.noComment}>아직 댓글이 없습니다.</div>
      )}
    </ConsultContainer>
  );
};

export default ConsultSelect;
