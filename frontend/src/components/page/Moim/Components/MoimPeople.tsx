import styled from "@emotion/styled";
import { useMoimStore } from "../../../../types/State";
import { shallow } from "zustand/shallow";

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
  font-size: 36px;
  font-weight: 700;
  color: #1e293b;
  margin-bottom: 20px;
`;

const NumberContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f0f4f8;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
`;

const NumberFieldSet = styled.fieldset`
  padding: 50px;
  margin-bottom: 30px;
  width: 100%;

  input {
    width: 50vw;
    padding: 10px;
    font-size: 16px;
    border: 1.5px solid #ccc;
    border-radius: 6px;
    outline: none;
    &:focus {
      border-color: #007bff;
    }
  }
  legend {
    font-size: 18px;
    font-weight: bold;
    color: #1e293b;
  }
`;

const ErrorMessage = styled.p`
  color: #ef4444;
  font-size: 14px;
  margin-top: 10px;
  text-align: center;
  background-color: #fee2e2;
  padding: 10px;
  border-radius: 6px;
  width: 70%;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border: 1px solid #f87171;
  margin-top: 20px;
  transition: background-color 0.3s, color 0.3s;
  &:hover {
    background-color: #fca5a5;
    color: #b91c1c;
  }
`;

const MoimPeople = () => {
  const { setMaxpeople, setMinPeople } = useMoimStore().MoimDataStore;
  const minPeople = useMoimStore().MoimDataStore.moimDetail.minPeople;
  const maxPeople = useMoimStore().MoimDataStore.moimData.maxpeople;
  return (
    <Container>
      <Title>모임에 참여하는 사람의 숫자는?</Title>
      <NumberContainer>
        {/* 여기에 모임 참여자 수를 입력하는 컴포넌트나 UI 요소를 추가할 수 있습니다. */}
        <NumberFieldSet>
          <legend>최소 참여자 수</legend>
          <input
            type="number"
            placeholder="최소 참여자 수 입력"
            value={minPeople}
            onChange={(e) => setMinPeople(Number(e.target.value))}
          />
        </NumberFieldSet>
        <NumberFieldSet>
          <legend>최대 참여자 수</legend>
          <input
            type="number"
            placeholder="최대 참여자 수 입력"
            value={maxPeople}
            onChange={(e) => setMaxpeople(Number(e.target.value))}
          />
        </NumberFieldSet>
        {minPeople > maxPeople && (
          <ErrorMessage>
            최소 참여자 수는 최대 참여자 수를 넘을 수 없습니다.
          </ErrorMessage>
        )}
      </NumberContainer>
    </Container>
  );
};
export default MoimPeople;
