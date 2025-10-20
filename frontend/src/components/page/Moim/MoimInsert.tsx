import styled from "@emotion/styled";
import { Sparkles } from "lucide-react";
import MoimName from "./Components/MoimName";
import MoimPeople from "./Components/MoimPeople";
import { useMoimCountData, useMoimStore } from "../../../types/State";
import { MoimPay } from "./Components/MoimPay";
import MoimContent from "./Components/MoimContent";
import MoimJoins from "./Components/MoimJoins";
import MoimCategoryDetail from "./Components/MoimCategoryDetail";

const Container = styled.div`
  min-height: calc(90vh - 100px);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, #f8fafc, #e0f2fe);
  text-align: center;
  padding: 40px;
`;

const Title = styled.h1`
  display: flex;
  align-items: center;
  font-size: 36px;
  font-weight: 700;
  color: #1e293b;
  margin-bottom: 20px;
`;

const MoimInsert = () => {
  const Moimcount = useMoimCountData((state) => state.Moimcount);
  const setMoimCount = useMoimCountData((state) => state.setMoimCount);
  const MoimData = useMoimStore().MoimDataStore.moimData;
  const MoimDetailData = useMoimStore().MoimDataStore.moimDetail;

  const handleNext = () => {
    if (validateStep()) {
      setMoimCount(Moimcount + 1);
    } else {
      if (Moimcount == 0) {
        alert("모임 카테고리를 하나 선택 후 다음으로 넘겨주세요.");
      } else if (Moimcount == 1) {
        alert("모임 이름과 이미지를 모두 입력해 주세요.");
      } else if (Moimcount == 2) {
        alert("최대 인원이 최소 인원보다 작을 수 없습니다.");
      } else if (Moimcount == 3) {
        alert("모임 소개와 모임 내용을 모두 입력해 주세요.");
      } else if (Moimcount == 4) {
        alert("모임 참가비와 태그를 모두 입력해 주세요.");
      } else alert("현재 챕터에 입력되지 않은 값이 있습니다.");
    }
  };

  const validateStep = () => {
    if (Moimcount == 0) {
      if (MoimData.categoryDetail) {
        return true;
      }
    } else if (Moimcount == 1) {
      console.log(MoimData.title);
      if (
        MoimData.title &&
        MoimData.title != "" &&
        MoimData.image &&
        MoimData.image != ""
      ) {
        return true;
      }
      return false;
    } else if (Moimcount == 2) {
      if (MoimData.maxpeople < MoimDetailData.minPeople) {
        return false;
      }
      return true;
    } else if (Moimcount == 3) {
      if (
        MoimData.description &&
        MoimData.description != "" &&
        MoimDetailData.content &&
        MoimDetailData.content != ""
      ) {
        return true;
      }
      return false;
    } else if (Moimcount == 4) {
      if (MoimDetailData.Pay >= 0 && MoimData.tag.length > 0) {
        return true;
      } else return false;
    }
  };

  const renderStep = () => {
    switch (Moimcount) {
      case 0:
        return <MoimCategoryDetail />;
      case 1:
        return <MoimName />;
      case 2:
        return <MoimPeople />;
      case 3:
        return <MoimContent />;
      case 4:
        return <MoimPay />;
      default:
        return <MoimJoins />;
    }
  };

  return (
    <Container>
      <Title>
        <Sparkles size={36} />
        Moim Insert
        <Sparkles size={36} />
      </Title>

      {renderStep()}

      {Moimcount <= 4 && (
        <button
          style={{
            padding: "10px 20px",
            fontSize: "16px",
            backgroundColor: "#3b82f6",
            color: "white",
            border: "none",
            borderRadius: "8px",
            cursor: "pointer",
            marginTop: "20px",
          }}
          onClick={handleNext}
        >
          확인
        </button>
      )}
    </Container>
  );
};

export default MoimInsert;
