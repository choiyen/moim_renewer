import { ModalBackground, ModalContainer } from "./PasswordModel";

//모임 세부 사항 접속 시에 모임 가입 신청이 들어갔는지 여부를 확인 한 후 들어갔으면 띄우는 모달창
const MoimJoinModel = () => {
  return (
    <ModalBackground>
      <ModalContainer>
        <div>현재 참가 대기 중인 모임</div>
        <div>기다리겠습니까? 아니면 참가 신청을 철회할까요?</div>
        <div>
          <button>확인</button>
          <button>취소</button>
        </div>
      </ModalContainer>
    </ModalBackground>
  );
};

export default MoimJoinModel;
