import styled from "@emotion/styled";
const MoimDetailContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  min-height: 100vh;
  padding-bottom: 40px;
`;
const MoimLineContainer = styled.div`
  width: 70vw;
  height: 70vh;
  background-color: white;
`;

const MoimDetail = () => {
  return (
    <MoimDetailContainer>
      <MoimLineContainer>모임 상세 페이지</MoimLineContainer>
    </MoimDetailContainer>
  );
};
export default MoimDetail;
