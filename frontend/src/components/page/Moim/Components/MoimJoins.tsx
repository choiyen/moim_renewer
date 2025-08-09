import styled from "@emotion/styled";
import { useMoimStore } from "../../../../types/State";

const MoimJoinsContainer = styled.div`
  width: 70vw;
  max-width: 800px;
  background-color: #f0f4f8;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
`;

const MoimJoinFeldSet = styled.fieldset``;
const MoimJoinTiTle = styled.legend`
  font-size: 24px;
`;

const MoimJoins = () => {
  const { moimData, moimDetail } = useMoimStore().MoimDataStore;
  return (
    <MoimJoinsContainer>
      <MoimJoinFeldSet>
        <MoimJoinTiTle>모임 생성 정보</MoimJoinTiTle>
        <div> 모임 이름 : {moimData.title}</div>
        <div> 모임 소개 : {moimData.description}</div>
        <div> 모임 테그 : {moimData.tag}</div>
        <div>
          모임 이미지 : <img src={moimData.image} alt="" />
        </div>
        <div>
          모임 가입인원 : {moimDetail.minPeople} 이상 {moimData.maxpeople} 이하
        </div>
        <div>모임 참가비 : {moimDetail.Pay}</div>
        <div>모임 내용 : {moimDetail.content}</div>
        <button>등록 신청</button>
      </MoimJoinFeldSet>
    </MoimJoinsContainer>
  );
};

export default MoimJoins;
