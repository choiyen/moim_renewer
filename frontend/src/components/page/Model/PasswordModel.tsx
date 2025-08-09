import styled from "@emotion/styled";
import React from "react";

export const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 반투명 검정 */
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

export const ModalContainer = styled.div`
  background-color: white;
  border-radius: 8px;
  width: 400px;
  max-width: 90%;
  padding: 30px 25px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.2);
`;

const ModalTitle = styled.h2`
  margin-bottom: 20px;
  font-size: 22px;
  color: #333;
  text-align: center;
`;

const Input = styled.input`
  width: 100%;
  height: 40px;
  margin-bottom: 15px;
  padding: 0 10px;
  font-size: 16px;
  border: 1.5px solid #ccc;
  border-radius: 5px;

  &:focus {
    border-color: #007bff;
    outline: none;
  }
`;

const ButtonRow = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
`;

const CancelButton = styled.button`
  background-color: #d53d3d;
  border: none;
  border-radius: 5px;
  padding: 10px 25px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background-color: #999;
  }
`;

const ConfirmButton = styled.button`
  border: none;
  border-radius: 5px;
  padding: 10px 25px;
  color: white;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background-color: #0056b3;
  }
`;

interface PasswordModalProps {
  onClose: () => void;
  onConfirm: (currentPw: string, newPw: string, confirmPw: string) => void;
}

const PasswordModal: React.FC<PasswordModalProps> = ({
  onClose,
  onConfirm,
}) => {
  const [currentPw, setCurrentPw] = React.useState("");
  const [newPw, setNewPw] = React.useState("");
  const [confirmPw, setConfirmPw] = React.useState("");

  const handleConfirm = () => {
    onConfirm(currentPw, newPw, confirmPw);
  };

  return (
    <ModalBackground>
      <ModalContainer>
        <ModalTitle>비밀번호 변경</ModalTitle>
        <Input
          type="password"
          placeholder="현재 비밀번호"
          value={currentPw}
          onChange={(e) => setCurrentPw(e.target.value)}
        />
        <Input
          type="password"
          placeholder="새 비밀번호"
          value={newPw}
          onChange={(e) => setNewPw(e.target.value)}
        />
        <Input
          type="password"
          placeholder="새 비밀번호 확인"
          value={confirmPw}
          onChange={(e) => setConfirmPw(e.target.value)}
        />
        <ButtonRow>
          <ConfirmButton onClick={handleConfirm}>변경하기</ConfirmButton>
          <CancelButton onClick={onClose}>취소</CancelButton>
        </ButtonRow>
      </ModalContainer>
    </ModalBackground>
  );
};

export default PasswordModal;
