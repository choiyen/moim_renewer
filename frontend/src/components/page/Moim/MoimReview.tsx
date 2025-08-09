import styled from "@emotion/styled";
import React, { useEffect, useState } from "react";
import { FaStar, FaStarHalfAlt, FaRegStar } from "react-icons/fa";

const MoimReviewTitle = styled.h2`
  margin-bottom: 20px;
  font-size: 24px;
  color: #333;
  text-align: center;
`;

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

const ModalContent = styled.div`
  background: white;
  padding: 30px 25px;
  border-radius: 8px;
  width: 400px;
  max-width: 90%;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
`;

const ReviewTextarea = styled.textarea`
  width: 100%;
  height: 100px;
  margin-top: 15px;
  margin-bottom: 20px;
  padding: 10px;
  font-size: 16px;
  border: 1.5px solid #ccc;
  border-radius: 6px;
  resize: vertical;

  &:focus {
    border-color: #007bff;
    outline: none;
  }
`;

const ButtonRow = styled.div`
  display: flex;
  justify-content: flex-end;
  gap: 12px;
`;

const ConfirmButton = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background-color: #0056b3;
  }
`;

const CancelButton = styled.button`
  background-color: #d53d3d;
  color: white;
  border: none;
  border-radius: 6px;
  padding: 10px 20px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background-color: #a22a2a;
  }
`;

const StarsContainer = styled.div`
  display: flex;
  gap: 4px;
  font-size: 24px;
  color: #f5a623;
  justify-content: center;
  margin-top: 10px;
  user-select: none;
  cursor: pointer;
`;

interface MoimReviewProps {
  Titleday: Date;
}

interface MoimPorps {
  Title: string;
  review: string;
}

const newMoimReview: MoimPorps = {
  Title: "야구 보러가실 분?",
  review: "사직 야구장에 야구 보러가실 분 5명 구합니다.",
};

const StarRating: React.FC<{
  rating: number;
  onChange?: (r: number) => void;
}> = ({ rating, onChange }) => {
  const stars = [];

  // 별점 클릭 시 점수 설정
  const handleClick = (index: number) => {
    if (onChange) {
      const newRating = rating === index + 1 ? index + 0.5 : index + 1;
      onChange(newRating);
    }
  };

  for (let i = 0; i < 5; i++) {
    if (rating >= i + 1) {
      stars.push(
        <FaStar
          key={i}
          onClick={() => handleClick(i)}
          style={{ cursor: onChange ? "pointer" : "default" }}
          title={`${i + 1}점`}
        />
      );
    } else if (rating >= i + 0.5) {
      stars.push(
        <FaStarHalfAlt
          key={i}
          onClick={() => handleClick(i)}
          style={{ cursor: onChange ? "pointer" : "default" }}
          title={`${i + 0.5}점`}
        />
      );
    } else {
      stars.push(
        <FaRegStar
          key={i}
          onClick={() => handleClick(i)}
          style={{ cursor: onChange ? "pointer" : "default" }}
          title={`${i}점`}
        />
      );
    }
  }

  return <StarsContainer>{stars}</StarsContainer>;
};

const MoimReview = ({ Titleday }: MoimReviewProps) => {
  const [isModalOpen, setIsModalOpen] = useState(true);
  const [rating, setRating] = useState(0);
  const [reviewText, setReviewText] = useState("");

  useEffect(() => {
    const now = new Date();
    if (now < Titleday) {
      setIsModalOpen(false); // 날짜 지나지 않았으면 모달 닫기
    } else {
      setIsModalOpen(true); // 날짜 지났으면 모달 열기
    }
  }, [Titleday]);

  const handleConfirm = () => {
    alert(`별점: ${rating}\n리뷰: ${reviewText}`);
    setIsModalOpen(false);
  };

  return (
    <div>
      <MoimReviewTitle>모임</MoimReviewTitle>
      {isModalOpen && (
        <ModalBackground onClick={() => setIsModalOpen(false)}>
          <ModalContent onClick={(e) => e.stopPropagation()}>
            <div style={{ fontWeight: "bold", fontSize: "18px" }}>
              모임 이름: {newMoimReview.Title}
            </div>
            <div style={{ marginBottom: "10px" }}>
              모임 소개: {newMoimReview.review}
            </div>

            <div>모임 별점 (클릭하여 평가)</div>
            <StarRating rating={rating} onChange={setRating} />

            <ReviewTextarea
              placeholder="모임에 관련된 리뷰를 입력해주세요"
              value={reviewText}
              onChange={(e) => setReviewText(e.target.value)}
            />

            <ButtonRow>
              <ConfirmButton onClick={handleConfirm}>등록하기</ConfirmButton>
              <CancelButton onClick={() => setIsModalOpen(false)}>
                닫기
              </CancelButton>
            </ButtonRow>
          </ModalContent>
        </ModalBackground>
      )}
    </div>
  );
};

export default MoimReview;
