import styled from "@emotion/styled";

const ModalBackdrop = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
`;

const ModalBox = styled.div`
  background-color: white;
  padding: 24px;
  border-radius: 8px;
  min-width: 300px;
  text-align: center;
`;

const Button = styled.button<{ variant?: string }>`
  padding: 8px 16px;
  margin: 8px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  background-color: ${({ variant }) =>
    variant === "cancel" ? "#ccc" : "#f55"};
  color: white;
`;

interface ConfirmModalProps {
  message: string;
  onConfirm: () => void;
  onCancel: () => void;
}

const ConfirmModal = ({ message, onConfirm, onCancel }: ConfirmModalProps) => {
  return (
    <ModalBackdrop>
      <ModalBox>
        <p>{message}</p>
        <div>
          <Button onClick={onConfirm}>확인</Button>
          <Button variant="cancel" onClick={onCancel}>
            취소
          </Button>
        </div>
      </ModalBox>
    </ModalBackdrop>
  );
};

export default ConfirmModal;
