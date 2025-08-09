import styled from "@emotion/styled";
import { useUserStore } from "../../../types/State";
import { useEffect, useState } from "react";

export const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: black;
  height: auto;
  padding: 100px;
`;

export const SignupTitle = styled.h1`
  color: white;
  font-size: 24px;
  margin-bottom: 20px;
`;

const SignupWrapper = styled.div`
  width: 50vw;
  display: flex;
  height: auto;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  background-color: #f0f0f0;
  padding: 20px;
  border-radius: 8px;
  border: 1px dotted #ccc;

  @media (max-width: 768px) {
    width: 80vw;
  }

  @media (max-width: 480px) {
    width: 90vw;
  }
`;

const InputRow = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
`;
const AddressInputRow = styled(InputRow)`
  flex-direction: column !important;
  align-items: stretch !important;
  gap: 8px;
  height: 100px !important;
`;

const SignupInput = styled.input`
  flex: 1;
  height: 40px;
  padding: 0 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
`;

const CheckDuplicateButton = styled.button`
  height: 40px;
  padding: 0 12px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  white-space: nowrap;

  &:hover {
    background-color: #218838;
  }

  @media (max-width: 768px) {
    font-size: 13px;
  }

  @media (max-width: 480px) {
    font-size: 12px;
    padding: 0 10px;
  }
`;

const SignButton = styled.button`
  width: 100%;
  height: 40px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-top: 20px;

  &:hover {
    background-color: #0056b3;
  }
`;

export const Label = styled.label`
  margin: 10px 0 5px 0;
`;

const SignupIntro = styled.textarea`
  width: 100%;
  height: 100px;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
`;

const TermsWrapper = styled.div`
  width: 100%;
  margin-top: 30px;
  display: flex;
  flex-direction: column !important;
  align-items: flex-start;
`;

const TermsTitle = styled.h2`
  font-size: 20px;
  color: #333;
  margin-bottom: 15px;
`;

const TermsItem = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 12px;

  /* 체크박스 크기 조절 (선택사항) */
  input[type="checkbox"] {
    width: 18px;
    height: 18px;
  }
`;

const TermsLabel = styled.label`
  font-size: 14px;
  color: #555;
  cursor: pointer;
`;
const SelectInput = styled.select`
  width: 100%;
  height: 40px;
  padding: 0 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
  font-size: 14px;
  background-color: white;
  cursor: pointer;
`;

const MoimSignUp = () => {
  // Update the selector to use the correct property names from your user store
  // For example, if your store has 'termsAgreed' and 'setTermsAgreed', use those:
  const [termsChecked, setTermsChecked] = useUserStore((state) => [
    state.userData.checked,
    state.setChecked,
  ]);
  const [termServiceChecked, setTermServiceChecked] = useState(false);
  const [termPrivacyChecked, setTermPrivacyChecked] = useState(false);
  const [termMarketingChecked, setTermMarketingChecked] = useState(false);
  useEffect(() => {
    if (termServiceChecked && termPrivacyChecked && termMarketingChecked) {
      setTermsChecked(true);
    }
  }, [termServiceChecked, termPrivacyChecked, termMarketingChecked]);

  useEffect(() => {
    if (termsChecked) {
      // 모든 약관에 동의한 경우 추가 작업 수행
      setTermServiceChecked(true);
      setTermPrivacyChecked(true);
      setTermMarketingChecked(true);
    }
  }, [termsChecked]);

  return (
    <ButtonWrapper>
      <SignupTitle>Moim 회원가입</SignupTitle>
      <SignupWrapper>
        <Label htmlFor="email">이메일</Label>
        <InputRow>
          <SignupInput
            type="text"
            placeholder="이메일"
            id="email"
            value={useUserStore.getState().userData.email}
            onChange={(e) => useUserStore.getState().setEmail(e.target.value)}
          />
          <CheckDuplicateButton type="button">이메일 확인</CheckDuplicateButton>
        </InputRow>

        <Label htmlFor="password">비밀번호</Label>
        <InputRow>
          <SignupInput
            type="password"
            placeholder="비밀번호"
            id="password"
            value={useUserStore.getState().userData.password}
            onChange={(e) =>
              useUserStore.getState().setPassword(e.target.value)
            }
          />
        </InputRow>

        <Label htmlFor="confirm-password">비밀번호 확인</Label>
        <InputRow>
          <SignupInput
            type="password"
            placeholder="비밀번호 확인"
            id="confirm-password"
            value={useUserStore.getState().userData.confirmPassword}
            onChange={(e) =>
              useUserStore.getState().setConfirmPassword(e.target.value)
            }
          />
        </InputRow>
        <Label htmlFor="nickname">닉네임</Label>
        <InputRow>
          <SignupInput
            type="text"
            placeholder="닉네임"
            id="nickname"
            value={useUserStore.getState().userData.nickname}
            onChange={(e) =>
              useUserStore.getState().setNickname(e.target.value)
            }
          />
          <CheckDuplicateButton type="button">중복 확인</CheckDuplicateButton>
        </InputRow>
        <Label htmlFor="profile-image">프로필 이미지</Label>
        <InputRow>
          <SignupInput
            type="file"
            id="profile-image"
            onChange={(e) => {
              const files = e.target.files;
              if (files && files[0]) {
                useUserStore.getState().setProfileImage(files[0]);
              }
            }}
          />
        </InputRow>
        <Label htmlFor="gender">성별</Label>
        <InputRow>
          <InputRow>
            <SelectInput
              id="gender"
              name="gender"
              defaultValue=""
              onChange={(e) =>
                useUserStore.getState().setGender(e.target.value)
              }
              value={useUserStore.getState().userData.gender}
            >
              <option value="" disabled>
                성별을 선택하세요
              </option>
              <option value="male">남자</option>
              <option value="female">여자</option>
              <option value="other">기타</option>
            </SelectInput>
          </InputRow>
        </InputRow>
        <Label htmlFor="birthdate">생년월일</Label>
        <InputRow>
          <SignupInput
            type="text"
            placeholder="년"
            id="birthdate-year"
            value={useUserStore.getState().userData.birthdate.year}
            onChange={(e) =>
              useUserStore
                .getState()
                .setBirthdate(
                  e.target.value,
                  useUserStore.getState().userData.birthdate.month,
                  useUserStore.getState().userData.birthdate.day
                )
            }
          />
          <SignupInput
            type="text"
            placeholder="월"
            id="birthdate-month"
            value={useUserStore.getState().userData.birthdate.month}
            onChange={(e) =>
              useUserStore
                .getState()
                .setBirthdate(
                  useUserStore.getState().userData.birthdate.year,
                  e.target.value,
                  useUserStore.getState().userData.birthdate.day
                )
            }
          />
          <SignupInput
            type="text"
            placeholder="일"
            id="birthdate-day"
            value={useUserStore.getState().userData.birthdate.day}
            onChange={(e) =>
              useUserStore
                .getState()
                .setBirthdate(
                  useUserStore.getState().userData.birthdate.year,
                  useUserStore.getState().userData.birthdate.month,
                  e.target.value
                )
            }
          />
        </InputRow>
        <Label htmlFor="address">주소</Label>
        <AddressInputRow>
          <SignupInput
            type="text"
            placeholder="기본 주소"
            id="address"
            value={useUserStore.getState().userData.address.basic}
            onChange={(e) =>
              useUserStore
                .getState()
                .setAddress(
                  e.target.value,
                  useUserStore.getState().userData.address.detail
                )
            }
          />
          <SignupInput
            type="text"
            placeholder="상세 주소"
            id="address-detail"
            value={useUserStore.getState().userData.address.detail}
            onChange={(e) =>
              useUserStore
                .getState()
                .setAddress(
                  useUserStore.getState().userData.address.basic,
                  e.target.value
                )
            }
          />
        </AddressInputRow>
        <Label htmlFor="introduction">자기소개</Label>
        <InputRow>
          <SignupIntro
            placeholder="자기소개"
            id="introduction"
            value={useUserStore.getState().userData.introduction}
            onChange={(e) =>
              useUserStore.getState().setIntroduction(e.target.value)
            }
          />
        </InputRow>
        <TermsWrapper>
          <TermsTitle>가입 약관 동의</TermsTitle>

          <TermsItem>
            <SignupInput type="checkbox" id="terms-all" />
            <TermsLabel htmlFor="terms-all">
              모든 가입 약관에 동의합니다.
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-service"
              checked={termServiceChecked}
              onChange={(e) => setTermServiceChecked(e.target.checked)}
            />
            <TermsLabel htmlFor="terms-service">
              Moim 이용 약관에 동의합니다. (필수)
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-privacy"
              checked={termPrivacyChecked}
              onChange={(e) => setTermPrivacyChecked(e.target.checked)}
            />
            <TermsLabel htmlFor="terms-privacy">
              개인정보 수집 및 이용에 동의합니다. (필수)
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-marketing"
              checked={termMarketingChecked}
              onChange={(e) => setTermMarketingChecked(e.target.checked)}
            />
            <TermsLabel htmlFor="terms-marketing">
              마케팅 활용 및 광고성 정보 수신에 동의합니다. (선택)
            </TermsLabel>
          </TermsItem>
        </TermsWrapper>

        <SignButton type="submit">회원가입</SignButton>
      </SignupWrapper>
    </ButtonWrapper>
  );
};

export default MoimSignUp;
