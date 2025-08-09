import styled from "@emotion/styled";
import { useNavigate } from "react-router-dom";

const Container = styled.div`
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to bottom, #f8fafc, #e0f2fe);
  text-align: center;
  padding: 40px;
`;

const ErrorCode = styled.h1`
  font-size: 120px;
  font-weight: 900;
  color: #3b82f6;
  margin: 0;
`;

const ErrorMessage = styled.p`
  font-size: 24px;
  color: #334155;
  margin: 10px 0 30px;
`;

const BackButton = styled.button`
  padding: 12px 24px;
  font-size: 16px;
  background-color: #3b82f6;
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.2s ease;

  &:hover {
    background-color: #2563eb;
  }
`;

const MoimError = () => {
  const navigate = useNavigate();

  return (
    <Container>
      <ErrorCode>404</ErrorCode>
      <ErrorMessage>이런! 존재하지 않는 페이지입니다.</ErrorMessage>
      <BackButton onClick={() => navigate("/")}>홈으로 돌아가기</BackButton>
    </Container>
  );
};

export default MoimError;
