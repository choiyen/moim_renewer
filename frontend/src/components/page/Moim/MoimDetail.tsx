import styled from "@emotion/styled";
import { MoimDataDummys } from "../../../types/MoimDataDummy";
import styleds from "../Moim/Components/moimDetall.module.css";
import { useState } from "react";
import { FaArrowLeft, FaHeart, FaRegHeart } from "react-icons/fa";
import { useNavigate, useParams } from "react-router-dom";
import MoimReview from "./MoimReview";
import { useMoimReviewStore } from "../../../types/State";

const MoimDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  min-height: 100vh;
`;
const MoimLineContainer = styled.div`
  display: flex;
  margin: 50px 0px;
  flex-direction: column;
  align-items: center;
  width: 70vw;
  background-color: white;
`;

const HeartIcon = styled.div`
  font-size: 20px;
  color: #ef4444;
  cursor: pointer;
  display: flex;
  margin-bottom: 50px;
  justify-content: flex-end;
`;

const MoimDetail = () => {
  const [liked, setLiked] = useState(false);
  const { id } = useParams();
  const { isReviewModalOpen, setReviewModalOpen } = useMoimReviewStore();
  const nativeGate = useNavigate();
  const Backed = () => {
    nativeGate(-1);
  };
  const Removing = () => {
    const confirmed = window.confirm("정말 삭제하시겠습니까?");
    console.log("삭제할 데이터 번호 :" + id);
    if (confirmed) {
      // 삭제 로직 실행
      alert("삭제되었습니다.");
    } else {
      // 취소했을 때 동작
      alert("삭제가 취소되었습니다.");
    }
  };
  return (
    <MoimDetailContainer>
      <div
        style={{
          width: "100%",
          padding: "50px",
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
      <MoimLineContainer>
        <div className={styleds.MoimDetailTitle}>
          {MoimDataDummys.MoimData.title}
        </div>
        <div className={styleds.MoimDetailCateGory}>
          {MoimDataDummys.MoimData.category} -
          {MoimDataDummys.MoimData.categoryDetail}
        </div>
        <div className={styleds.MoimDetailContent}>
          <img src={MoimDataDummys.MoimData.image} alt="" />
        </div>
        <div className={styleds.MoimDetailCards}>
          <HeartIcon
            onClick={(e) => {
              e.stopPropagation();
              setLiked(!liked);
            }}
          >
            {liked ? <FaHeart size={25} /> : <FaRegHeart size={25} />}
          </HeartIcon>
          <div className={styleds.tableContainer}>
            <table>
              <tbody>
                <tr>
                  <td>날짜</td>
                  <td>
                    {MoimDataDummys.MoimData.expirationDate.toLocaleDateString()}{" "}
                    부터 {MoimDataDummys.MoimData.evenDate.toLocaleDateString()}{" "}
                    까지
                  </td>
                </tr>
                <tr>
                  <td>주소지</td>
                  <td>{MoimDataDummys.MoimData.location}</td>
                </tr>
                <tr>
                  <td>온라인 여부</td>
                  <td>
                    {MoimDataDummys.MoimData.isOnline ? "온라인" : "오프라인"}
                  </td>
                </tr>
                <tr>
                  <td>지정 인원 수</td>
                  <td>
                    최소 {MoimDataDummys.MoimDetail.minPeople} 명부터 최대{" "}
                    {MoimDataDummys.MoimData.maxpeople} 명까지
                  </td>
                </tr>
                <tr>
                  <td>개최자</td>
                  <td>{MoimDataDummys.MoimData.organizer}</td>
                </tr>
                <tr>
                  <td>지정 Tag</td>
                  <td>
                    {MoimDataDummys.MoimData.tag.map((data, idx) => (
                      <span key={idx} className={styleds.tagSpan}>
                        {data}
                        {idx < MoimDataDummys.MoimData.tag.length - 1 && ", "}
                      </span>
                    ))}
                  </td>
                </tr>
                <tr>
                  <td>참가자</td>
                  <td>
                    {MoimDataDummys.MoimDetail.Members.map((data, idx) => (
                      <span key={idx} className={styleds.memberSpan}>
                        {data}
                        {idx < MoimDataDummys.MoimDetail.Members.length - 1 &&
                          ", "}
                      </span>
                    ))}
                  </td>
                </tr>
                <tr>
                  <td>참가비</td>
                  <td>
                    {MoimDataDummys.MoimDetail.Pay === 0
                      ? "무료"
                      : `${MoimDataDummys.MoimDetail.Pay}원`}
                  </td>
                </tr>
                <tr>
                  <td colSpan={2}>{MoimDataDummys.MoimDetail.content}</td>
                </tr>
              </tbody>
            </table>
          </div>
          <div className={styleds.buttonContainer}>
            <button>수정</button>
            <button onClick={() => setReviewModalOpen(!isReviewModalOpen)}>
              리뷰 작성
            </button>
            <button
              onClick={() => {
                Removing();
              }}
            >
              삭제
            </button>
          </div>
        </div>
      </MoimLineContainer>
      {isReviewModalOpen && (
        <MoimReview Titleday={MoimDataDummys.MoimData.expirationDate} />
      )}
    </MoimDetailContainer>
  );
};
export default MoimDetail;
