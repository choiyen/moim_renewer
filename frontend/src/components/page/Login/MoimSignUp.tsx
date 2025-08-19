import styled from "@emotion/styled";
import { useUserStore } from "../../../types/State";
import { useEffect, useRef, useState } from "react";
import { GET } from "../../comon/axios/axiosInstance";
import { toast } from "react-toastify";
import { hopper } from "../../../types/MoimType";

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
const InputDateRow = styled.div`
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

const SignupInputDate = styled.input`
  flex: 1;
  height: 40px;
  padding: 0 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
  width: 100px;
`;

const CheckDuplicateButton = styled.button<{ disabled?: boolean }>`
  height: 40px;
  padding: 0 12px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
  white-space: nowrap;
  ${(props) =>
    props.disabled &&
    `
    opacity: 0.6;
    cursor: not-allowed;
    pointer-events: none;
    background-color: #cccccc; /* 큰따옴표 제거 */
    color: black;
  `}

  &:hover {
    background-color: ${(props) => (props.disabled ? "#cccccc" : "#218838")};
    box-shadow: ${(props) =>
      props.disabled ? "none" : "0 4px 8px rgba(0, 0, 0, 0.2)"};
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
  const setTermsChecked = useUserStore((state) => state.setChecked);
  const termsChecked = useUserStore((state) => state.userData.checked);
  const [termCheck, setTermCheck] = useState({
    Market: false,
    Service: false,
    Privacy: false,
  });
  const [isEmailDuplicationDisabled, setisEmailDuplicationDisabled] =
    useState(false);
  const nicknameButtonRef = useRef<HTMLButtonElement>(null);

  useEffect(() => {
    if (termCheck.Market && termCheck.Privacy && termCheck.Service) {
      setTermsChecked(true);
    } else {
      setTermsChecked(false);
    }
  }, [setTermsChecked, termCheck.Market, termCheck.Privacy, termCheck.Service]);
  const {
    userData,
    setEmail,
    setPassword,
    setConfirmPassword,
    setNickname,
    setProfileImage,
    setGender,
    setBirthdate,
    setAddress,
    setIntroduction,
  } = useUserStore();

  const handleEmailCheck = () => {
    GET({
      url: "/user/check",
      params: { email: userData.email },
    }).then((res) => {
      console.log(res);
      if (res.resultType == "checknot") {
        setisEmailDuplicationDisabled(true);
      } else if (res.resultType == "success") {
        setisEmailDuplicationDisabled(true);
        toast.error("중복된 이메일입니다. 다시 입력해주세요.", {
          position: "top-center",
          autoClose: 5000,
        });
      } else {
        toast.error(
          "설계에 반영되지 않은 결과 타입입니다. 발견시 관리자에게 문의해주세요",
          {
            position: "top-center",
            autoClose: 5000,
          }
        );
      }
    });
  };

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
            value={userData.email}
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
          <CheckDuplicateButton
            type="button"
            onClick={() => handleEmailCheck()}
            disabled={isEmailDuplicationDisabled}
          >
            {isEmailDuplicationDisabled ? "확인 완료" : "중복 확인"}
          </CheckDuplicateButton>
        </InputRow>

        <Label htmlFor="password">비밀번호</Label>
        <InputRow>
          <SignupInput
            type="password"
            placeholder="비밀번호"
            id="password"
            value={userData.password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </InputRow>

        <Label htmlFor="confirm-password">비밀번호 확인</Label>
        <InputRow>
          <SignupInput
            type="password"
            placeholder="비밀번호 확인"
            id="confirm-password"
            value={userData.confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </InputRow>
        <Label htmlFor="nickname">닉네임</Label>
        <InputRow>
          <SignupInput
            type="text"
            placeholder="닉네임"
            id="nickname"
            value={userData.nickname}
            onChange={(e) => setNickname(e.target.value)}
          />
          <CheckDuplicateButton type="button" ref={nicknameButtonRef}>
            중복 확인
          </CheckDuplicateButton>
        </InputRow>
        <Label htmlFor="profile-image">프로필 이미지</Label>
        <InputRow>
          <SignupInput
            type="file"
            id="profile-image"
            onChange={(e) => {
              const files = e.target.files;
              if (files && files[0]) {
                setProfileImage(files[0]);
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
              onChange={(e) => setGender(e.target.value)}
              value={userData.gender}
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
        <InputDateRow>
          <SignupInputDate
            type="text"
            placeholder="년"
            id="birthdate-year"
            value={userData.birthdate.year}
            onChange={(e) =>
              setBirthdate(
                e.target.value,
                userData.birthdate.month,
                userData.birthdate.day
              )
            }
          />
          <SignupInputDate
            type="text"
            placeholder="월"
            id="birthdate-month"
            value={userData.birthdate.month}
            onChange={(e) =>
              setBirthdate(
                userData.birthdate.year,
                e.target.value,
                userData.birthdate.day
              )
            }
          />
          <SignupInputDate
            type="text"
            placeholder="일"
            id="birthdate-day"
            value={userData.birthdate.day}
            onChange={(e) =>
              setBirthdate(
                userData.birthdate.year,
                userData.birthdate.month,
                e.target.value
              )
            }
          />
        </InputDateRow>
        <Label htmlFor="address">주소</Label>
        <AddressInputRow>
          <SignupInput
            type="text"
            placeholder="기본 주소"
            id="address"
            value={userData.address.basic}
            onChange={(e) =>
              setAddress(e.target.value, userData.address.detail)
            }
          />
          <SignupInput
            type="text"
            placeholder="상세 주소"
            id="address-detail"
            value={userData.address.detail}
            onChange={(e) => setAddress(userData.address.basic, e.target.value)}
          />
        </AddressInputRow>
        <Label>주요 관심사</Label>
        <SelectInput>
          {hopper.map((item, idx) => (
            <option key={idx} value={item.Category}>
              {item.Category}
            </option>
          ))}
        </SelectInput>
        <Label htmlFor="introduction">자기소개</Label>
        <InputRow>
          <SignupIntro
            placeholder="자기소개"
            id="introduction"
            value={userData.introduction}
            onChange={(e) => setIntroduction(e.target.value)}
          />
        </InputRow>

        <TermsWrapper>
          <TermsTitle>가입 약관 동의</TermsTitle>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-all"
              checked={termsChecked}
              onChange={(e) => {
                setTermsChecked(e.target.checked);
              }}
              onClick={() => {
                if (termsChecked) {
                  setTermCheck({
                    Market: false,
                    Privacy: false,
                    Service: false,
                  });
                }
              }}
            />
            <TermsLabel htmlFor="terms-all">
              모든 가입 약관에 동의합니다.
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-service"
              checked={termCheck.Service}
              onChange={(e) =>
                setTermCheck((state) => ({
                  ...state,
                  Service: e.target.checked,
                }))
              }
            />
            <TermsLabel htmlFor="terms-service">
              Moim 이용 약관에 동의합니다. (필수)
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-privacy"
              checked={termCheck.Privacy}
              onChange={(e) =>
                setTermCheck((state) => ({
                  ...state,
                  Privacy: e.target.checked,
                }))
              }
            />
            <TermsLabel htmlFor="terms-privacy">
              개인정보 수집 및 이용에 동의합니다. (필수)
            </TermsLabel>
          </TermsItem>

          <TermsItem>
            <SignupInput
              type="checkbox"
              id="terms-marketing"
              checked={termCheck.Market}
              onChange={(e) =>
                setTermCheck((state) => ({
                  ...state,
                  Market: e.target.checked,
                }))
              }
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
