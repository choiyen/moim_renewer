import styled from "@emotion/styled";
import star from "../../comon/frame/image/star.png";
import PasswordModal from "../Model/PasswordModel";
import { useNavigate } from "react-router-dom";
import { useModelStore } from "../../../types/State";
import { useEffect, useState } from "react";
import { GET } from "../../comon/axios/axiosInstance";

const ProfileWrapper = styled.img`
  width: 200px;
  height: 200px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid #ccc;
  display: block;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out;

  &:hover {
    transform: scale(1.05);
  }

  @media (max-width: 768px) {
    width: 120px;
    height: 120px;
  }

  @media (max-width: 480px) {
    width: 80px;
    height: 80px;
  }
`;

const WishlistItem = styled.img`
  width: 150px;
  height: 150px;
  object-fit: cover;
  border: 2px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease-in-out;

  &:hover {
    transform: scale(1.05);
  }

  @media (max-width: 768px) {
    width: 100px;
    height: 100px;
  }

  @media (max-width: 480px) {
    width: 80px;
    height: 80px;
  }
`;

const ScrollContainer = styled.div`
  overflow-x: auto;
  white-space: nowrap;
  padding: 10px 0;
`;

export const InlineFlex = styled.div`
  display: inline-flex;
  gap: 10px;
  align-items: center;
`;

export const NavButton = styled.button`
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  width: 40px;
  height: 40px;
  font-size: 24px;
  line-height: 0;
  user-select: none;

  &:hover {
    background-color: #0056b3;
  }

  @media (max-width: 480px) {
    width: 30px;
    height: 30px;
    font-size: 20px;
  }
`;

export const Section = styled.div`
  text-align: center;
  margin-top: 20px;
  background-color: #f0f0f0;
  padding: 40px;
  border-radius: 8px;
  width: 80vw;
  max-width: 1000px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-left: auto;
  margin-right: auto;
`;
const ProfileInfo = styled.div`
  background-color: azure;
  padding: 20px;
  border-radius: 8px;
  width: 30vw;
  p {
    font-size: 16px;
    color: #333;
    padding: 8px;
    background-color: yellowgreen;
  }

  p:nth-of-type(even) {
    background-color: #f8f9fa;
  }
  button {
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    margin-top: 10px;
    font-weight: 600;
    transition: background-color 0.2s ease-in-out;

    &:hover {
      background-color: #0056b3;
    }
  }
`;
const FooterContainer = styled.div`
  margin-top: 20px;
  width: 100%;
  text-align: center;
  background-color: #f8f9fa;
  padding: 40px 0;
  color: #555;
  font-size: 14px;
  user-select: none;
  margin-bottom: 50px;
`;

const FooterItem = styled.button`
  margin: 6px 0;
  cursor: pointer;
  transition: color 0.2s ease-in-out;
  background-color: transparent;
  border: none;
  padding: 6px 12px;
  font-size: 15px;
  color: #555;
  border-radius: 4px;

  &:hover {
    color: #007bff;
    background-color: #e6f0ff;
  }

  &:focus {
    outline: none;
    box-shadow: 0 0 0 2px #007bff88;
  }
`;

interface UserState {
  address: string;
  addressDetail: string;
  birthDay: string;
  gender: string;
  interests: string; // ✅ 특정 문자열 하나가 아니라 어떤 문자열도 허용
  intro: string;
  nickname: string;
  password: string;
  profileImg: string;
  provider: "DEFAULT" | "KAKAO" | "NAVER" | "GOOGLE"; // ✅ enum처럼 관리 가능
  review: number;
  type: "MEMBER" | "ADMIN"; // ✅ 사용자 유형 제한
  userId: string;
}

const MoimProfile = () => {
  const nativegate = useNavigate();
  const [UserProfile, setUserProfile] = useState<UserState>({
    address: "",
    addressDetail: "",
    birthDay: "",
    gender: "",
    interests: "",
    intro: "",
    nickname: "",
    password: "",
    profileImg: "",
    provider: "DEFAULT",
    review: 0,
    type: "MEMBER",
    userId: "",
  });

  const handleProfileEdit = () => {
    nativegate("/profile/edit");
  };
  const handleConfirm = (
    currentPw: string,
    newPw: string,
    confirmPw: string
  ) => {
    // 검증 및 API 호출
    console.log(currentPw, newPw, confirmPw);
    useModelStore.getState().setPasswordModalOpen(false);
  };

  useEffect(() => {
    GET({
      url: "/user",
    })
      .then((res) => {
        setUserProfile(res.data[0]);
      })
      .catch((err) => {
        console.log(err);
        nativegate("/login");
      });
  }, []);

  return (
    <div
      style={{
        textAlign: "center",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        minHeight: "100vh",
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          padding: "20px",
          gap: "20px",
          flexWrap: "wrap",
          maxWidth: "900px",
          width: "90vw",
        }}
      >
        <div>
          <ProfileWrapper src={UserProfile.profileImg} alt="프로필 이미지" />
          <div
            style={{
              textAlign: "center",
              fontSize: "18px",
              fontWeight: "bold",
              marginTop: "10px",
            }}
          >
            {UserProfile.nickname}
          </div>
        </div>
        <ProfileInfo>
          <p>사용자 이메일</p>
          <p> {UserProfile.userId}</p>
          <p>가입일</p>
          {/* 가입일 추가 필요 */}
          <p> {UserProfile.birthDay}</p>
          <p>최근 활동</p>
          <p> {new Date().toLocaleString()}</p>
          {/* 로그인할 때 해당 값 변경, 로그아웃 시에 해당 값을 저장하는 구조로 */}
          <button
            style={{
              padding: "10px 20px",
              backgroundColor: "#007bff",
              color: "white",
              border: "none",
              borderRadius: "8px",
              cursor: "pointer",
              marginTop: "10px",
              marginRight: "25px",
            }}
            onClick={handleProfileEdit}
          >
            프로필 수정
          </button>
          <button
            style={{
              padding: "10px 20px",
              backgroundColor: "#dd8526",
              color: "white",
              border: "none",
              borderRadius: "8px",
              cursor: "pointer",
              marginTop: "10px",
            }}
            onClick={() => {
              useModelStore.getState().setPasswordModalOpen(true);
            }}
          >
            비밀번호 변경
          </button>
          {useModelStore.getState().isPasswordModalOpen && (
            <PasswordModal
              onClose={() =>
                useModelStore.getState().setPasswordModalOpen(false)
              }
              onConfirm={handleConfirm}
            />
          )}
        </ProfileInfo>
      </div>
      <div
        style={{
          width: "50vw",
          margin: "20px auto",
          textAlign: "left",
        }}
      >
        <h1 style={{ fontSize: "24px" }}>내 소개 글</h1>
        <p
          style={{
            fontSize: "16px",
            color: "#666",
            backgroundColor: "gainsboro",
            padding: "10px",
            borderRadius: "8px",
          }}
        >
          {UserProfile.intro}
        </p>
      </div>
      {/* 찜목록 섹션 */}
      <Section>
        <h3 style={{ fontSize: "18px", fontWeight: "bold" }}>찜목록</h3>
        <ScrollContainer>
          <InlineFlex>
            <NavButton>{"<"}</NavButton>
            <WishlistItem src={star} alt="찜목록 이미지" />
            <WishlistItem src={star} alt="찜목록 이미지" />
            <WishlistItem src={star} alt="찜목록 이미지" />
            <WishlistItem src={star} alt="찜목록 이미지" />
            <NavButton>{">"}</NavButton>
          </InlineFlex>
        </ScrollContainer>
      </Section>

      {/* 가입한 모임 목록 섹션 */}
      <Section>
        <h3 style={{ fontSize: "18px", fontWeight: "bold" }}>
          가입한 모임 목록
        </h3>
        <ScrollContainer>
          <InlineFlex>
            <NavButton>{"<"}</NavButton>
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <NavButton>{">"}</NavButton>
          </InlineFlex>
        </ScrollContainer>
      </Section>
      <Section>
        <h3 style={{ fontSize: "18px", fontWeight: "bold" }}>
          가입 대기 중인 모임
        </h3>
        <ScrollContainer>
          <InlineFlex>
            <NavButton>{"<"}</NavButton>
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <WishlistItem src={star} alt="가입한 모임 이미지" />
            <NavButton>{">"}</NavButton>
          </InlineFlex>
        </ScrollContainer>
      </Section>
      <FooterContainer>
        <FooterItem>서비스 소개</FooterItem>
        <FooterItem>공지사항</FooterItem>
        <FooterItem>자주 묻는 질문</FooterItem>
        <FooterItem>서비스 약관 및 정책</FooterItem>
        <FooterItem>개인정보 처리방침</FooterItem>
        <FooterItem>고객센터</FooterItem>
      </FooterContainer>
    </div>
  );
};

export default MoimProfile;
