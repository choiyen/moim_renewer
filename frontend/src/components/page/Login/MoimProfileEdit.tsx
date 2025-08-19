import styled from "@emotion/styled";
import { ButtonWrapper, Label, SignupTitle } from "./MoimSignUp";

const ProfileEditContainer = styled.div`
  width: 70vw;
  height: auto;
  background-color: aliceblue;
`;

const MoimProfileEdit = () => {
  return (
    <ButtonWrapper>
      <SignupTitle>Moim 회원정보</SignupTitle>
      <ProfileEditContainer>
        <Label htmlFor="email">이메일</Label>
        <div>cs1459@naver.com</div>
        <Label htmlFor="nickname">닉네임</Label>
        <input type="text" id="nickname" />
        <button>중복확인</button>
      </ProfileEditContainer>
    </ButtonWrapper>
  );
};

export default MoimProfileEdit;
