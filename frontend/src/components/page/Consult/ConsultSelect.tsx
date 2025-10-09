import { useNavigate, useParams } from "react-router-dom";
import { type Posts } from "../../../types/MoimDataDummy";
import styled from "@emotion/styled";
import { FaArrowLeft } from "react-icons/fa";

import styleds from "../Consult/Consult.module.css";
import { useEffect, useState } from "react";
import { DELETE, GET, POST } from "../../comon/axios/axiosInstance";

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
  moimConsultCommentId: string;
  nickname: string;
  comments: string;
  updateDate: Date;
  profileImg: string;
}

const ConsultSelect = () => {
  const { id } = useParams<{ id: string }>(); // id는 문자열로 들어옴
  const [targetPost, settargetPost] = useState<Posts>();
  const [comment, setcomment] = useState<CommentType[]>([]);
  const [commenting, setcommenting] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const user = localStorage.getItem("user");
  const nativeGate = useNavigate();
  const [UpdatingComment, setUpdatingComment] = useState<number>();
  const [UpdateCommenting, setUpdateCommenting] = useState<string>();
  const settingcomment = () => {
    if (user) console.log(JSON.parse(user));
    if (commenting == "") {
      alert("빈댓글은 작성할 수 없습니다.");
      return;
    }
    if (user && JSON.parse(user).nickname !== undefined) {
      POST({
        url: "/consultComment",
        data: {
          moimConsultId: id,
          nickname: JSON.parse(user).nickname,
          password: password,
          comments: commenting,
          profileImg: JSON.parse(user).profileImg,
        },
      }).then((res) => {
        setcomment([
          ...comment,
          {
            moimConsultCommentId: res.data[0].moimConsultCommentId,
            nickname: res.data[0].nickname,
            comments: res.data[0].comments,
            updateDate: res.data[0].updateDate,
            profileImg: res.data[0].profileImg,
          },
        ]);
        setcommenting("");
      });
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

    GET({
      url: "/consultComment/all",
      params: {
        MoimConsultId: id,
      },
    }).then((res) => {
      console.log(res.data);
      setcomment(res.data);
    });
    setcommenting("");
    setPassword("");
  }, []);

  const Backed = () => {
    nativeGate("/consult/");
  };
  const RemovingConsult = () => {
    const confirmed = window.confirm("정말 삭제하시겠습니까?");
    if (confirmed && user) {
      DELETE({
        url: "/consult",
        data: {
          moimConsultId: id,
          nickname: JSON.parse(user).nickname,
        },
      }).then((res) => {
        console.log(res);
        alert("삭제되었습니다.");
        nativeGate("/consult");
      });
    } else {
      alert("게시판을 삭제하는데 실패하였습니다.");
    }
  };

  const UpdatedComment = () => {
    console.log(UpdateCommenting);
    console.log(UpdatedComment);
  };

  const Removing = (idx: number) => {
    const confirmed = window.confirm("정말 삭제하시겠습니까?");
    const CheckedMemo = comment[idx].moimConsultCommentId;
    console.log(CheckedMemo);
    if (confirmed) {
      // 삭제 로직 실행
      const password = window.prompt(
        "댓글 작성 시 입력한 비밀번호를 입력하세요."
      );

      if (!password) {
        alert("비밀번호를 입력해야 삭제할 수 있습니다.");
        return;
      }
      DELETE({
        url: "/consultComment",
        data: {
          MoimConsultCommentId: CheckedMemo,
          Password: password,
        },
      }).then((res) => {
        console.log(res.resultType);
        if (res.resultType == "success") {
          const DeletedMemo = comment.filter((res) => {
            return res.moimConsultCommentId != CheckedMemo;
          });
          setcomment(DeletedMemo);
        }
      });
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
          <input
            type="password"
            placeholder="패스워드을 입력하세요"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button onClick={() => settingcomment()}>입력</button>
        </div>
        {user && targetPost?.Nickname == JSON.parse(user).nickname ? (
          <div className={styleds.commentRemoveContainer}>
            <button>수정</button>
            <button
              onClick={() => {
                RemovingConsult();
              }}
            >
              삭제
            </button>
          </div>
        ) : null}
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
                  <img src={c.profileImg} className={styleds.commentimg} />
                  <span style={{ fontSize: "25px" }}>{c.nickname}</span>
                </div>
                <span
                  style={{
                    textAlign: "center",
                    width: "300px",
                    padding: "10px",
                    boxShadow: "0 4px 10px rgba(0, 0, 0, 0.15)",
                  }}
                >
                  {c.updateDate.toLocaleString()}
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
                {UpdatingComment == idx ? (
                  <input
                    type="text"
                    placeholder="수정할 내용 입력"
                    value={UpdateCommenting}
                    onChange={(e) => {
                      setUpdateCommenting(e.target.value);
                    }}
                  />
                ) : (
                  c.comments
                )}
              </span>
              <div
                style={{
                  display: "flex",
                  justifyContent: "flex-end",
                  gap: "15px",
                }}
              >
                {UpdatingComment == idx ? (
                  <button
                    className={styleds.commentbutton}
                    style={{ backgroundColor: "green" }}
                    onClick={() => UpdatedComment()}
                  >
                    수정하기
                  </button>
                ) : (
                  <button
                    className={styleds.commentbutton}
                    style={{ backgroundColor: "green" }}
                    onClick={() => {
                      setUpdatingComment(idx);
                      setUpdateCommenting(c.comments);
                    }}
                  >
                    댓글 수정
                  </button>
                )}

                <button
                  className={styleds.commentbutton}
                  onClick={() => Removing(idx)}
                >
                  댓글 삭제
                </button>
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
