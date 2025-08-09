import styled from "@emotion/styled";
import { Sparkles } from "lucide-react";
import MoimName from "./Components/MoimName";
import MoimPeople from "./Components/MoimPeople";
import { useMoimCountData, useMoimStore } from "../../../types/State";
import { MoimPay } from "./Components/MoimPay";
import MoimContent from "./Components/MoimContent";
import MoimJoins from "./Components/MoimJoins";

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
      if (Moimcount == 1) {
        alert("에러 문구를 만족하지 않습니다. 조건을 맞춰주세요");
      } else alert("현재 챕터에 입력되지 않은 값이 있습니다.");
    }
  };

  const validateStep = () => {
    if (Moimcount == 0) {
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
    } else if (Moimcount == 1) {
      if (MoimData.maxpeople < MoimDetailData.minPeople) {
        return false;
      }
      return true;
    } else if (Moimcount == 2) {
      if (
        MoimData.description &&
        MoimData.description != "" &&
        MoimDetailData.content &&
        MoimDetailData.content != ""
      ) {
        return true;
      }
      return false;
    } else if (Moimcount == 3) {
      if (MoimDetailData.Pay >= 0 && MoimData.tag.length > 0) {
        return true;
      } else return false;
    }
  };

  const renderStep = () => {
    switch (Moimcount) {
      case 0:
        return <MoimName />;
      case 1:
        return <MoimPeople />;
      case 2:
        return <MoimContent />;
      case 3:
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

      {Moimcount <= 3 && (
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
