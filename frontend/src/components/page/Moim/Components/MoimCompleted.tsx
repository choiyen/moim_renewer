import styled from "@emotion/styled";
const MoimContentContainer = styled.div`
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

const MoimCompleted = () => {
  return (
    <MoimContentContainer>모임 등록이 완료되었습니다! 🎉</MoimContentContainer>
  );
};

export default MoimCompleted;
