/** @jsxImportSource @emotion/react */
import styled from "@emotion/styled";
import { FaGoogle } from "react-icons/fa";
import { SiNaver, SiKakao } from "react-icons/si";
import { useNavigate } from "react-router-dom";
import { useLoginStore } from "../../../types/State";
import { GET } from "../../comon/axios/axiosInstance";
import { useEffect } from "react";

const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: black;
  min-height: 80vh;
`;

const SocialButton = styled.a`
  display: flex;
  margin: 5px 0;
  align-items: center;
  justify-content: center; // 아이콘 + 텍스트 가운데 정렬
  width: 30vw; // ✅ 버튼 크기 일정하게 고정
  height: 50px; // ✅ 버튼 높이 고정
  border-radius: 8px;
  font-weight: bold;
  color: white;
  text-decoration: none;
  transition: background 0.2s ease-in-out;

  &:hover {
    opacity: 0.9;
  }

  svg {
    margin-right: 10px;
    font-size: 18px;
  }
  @media (max-width: 768px) {
    width: 60vw; // 모바일에서는 크게
  }
`;

const GoogleButton = styled(SocialButton)`
  background-color: #db4437;
`;

const NaverButton = styled(SocialButton)`
  background-color: #03c75a;
`;

const KakaoButton = styled(SocialButton)`
  background-color: #fee500;
  color: #381e1f;

  &:hover {
    background-color: #ffeb3b;
  }
`;
const Input = styled.input`
  width: 30vw;
  padding: 10px 14px;
  border-radius: 8px;
  border: 1px solid #ccc;
  margin-bottom: 10px;

  @media (max-width: 768px) {
    width: 60vw; // 모바일에서는 크게
  }
`;
const LoginButton = styled.button`
  width: 30vw;
  padding: 10px 14px;
  border-radius: 8px;
  background-color: #007bff;
  color: white;
  border: none;
  font-weight: bold;
  cursor: pointer;
  margin-bottom: 10px;

  @media (max-width: 768px) {
    width: 60vw; // 모바일에서는 크게
  }
`;
const SignupButton = styled.button`
  width: 30vw;
  padding: 10px 14px;
  border-radius: 8px;
  background-color: blueviolet;
  color: white;
  border: none;
  font-weight: bold;
  cursor: pointer;
  margin-bottom: 10px;

  @media (max-width: 768px) {
    width: 60vw; // 모바일에서는 크게
  }
`;
const OAUTH2_CONFIG = {
  kakao: {
    authorizeUrl: "https://kauth.kakao.com/oauth/authorize",
    clientId: import.meta.env.VITE_KOKAO_ID,
    redirectUri: "http://localhost:5173/login?OAuth2Type=kakao",
    responseType: "code",
    scope: "",
  },
  google: {
    authorizeUrl: "https://accounts.google.com/o/oauth2/v2/auth",
    clientId: import.meta.env.VITE_GOOGLE_ID,
    redirectUri: "http://localhost:5173/login?OAuth2Type=google",
    responseType: "code",
    scope: "openid email profile",
  },
  naver: {
    authorizeUrl: "https://nid.naver.com/oauth2.0/authorize",
    clientId: import.meta.env.VITE_NAVER_ID,
    redirectUri: "http://localhost:5173/login?OAuth2Type=naver",
    responseType: "code",
    scope: "",
    state: () => Math.random().toString(36).substring(2, 15), // CSRF 방지
  },
};

const MoimLogin = () => {
  const navigate = useNavigate();
  const handleAuth = (OAuth2Type: "kakao" | "google" | "naver") => {
    const config = OAUTH2_CONFIG[OAuth2Type];
    if (!config) {
      console.error("지원하지 않는 OAuth2 제공자:", OAuth2Type);
      return;
    }

    let url =
      `${config.authorizeUrl}?client_id=${config.clientId}` +
      `&redirect_uri=${encodeURIComponent(config.redirectUri)}` +
      `&response_type=${config.responseType || "code"}` +
      `&scope=${config.scope || ""}`;

    // 네이버는 state 필요
    if (OAuth2Type === "naver") {
      url += `&state=${OAUTH2_CONFIG.naver.state}`;
    }

    // 브라우저 리다이렉트
    window.location.href = url;
  };
  const { Login, setId, setPassword } = useLoginStore();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const code = params.get("code");
    const OAuth2Type = params.get("OAuth2Type"); // 추가
    if (code) {
      // 백엔드로 code 전송
      GET({ url: `/Auth/sign/${OAuth2Type}`, params: { code } })
        .then((res) => console.log("JWT + UserInfo:", res))
        .catch((err) => console.error(err));
    }
  }, []);

  return (
    <ButtonWrapper>
      <div className="lg:w-[40vw] sm:w-[80vw] md:w-[60vw] flex flex-col items-center gap-2 justify-center border-4 border-dashed border-gray-300 rounded-lg p-6">
        <div
          style={{
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <h1 className="text-2xl font-bold p-6 text-white">
            모임 커뮤니티 로그인
          </h1>
          {/* TODO: Replace with actual InputBox component */}
          <Input
            type="text"
            placeholder="아이디 또는 이메일"
            value={Login.id}
            onChange={(e) => setId(e.target.value)}
          />
          <Input
            type="password"
            placeholder="비밀번호"
            value={Login.password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <LoginButton>로그인</LoginButton>
          <SignupButton onClick={() => navigate("/signup")}>
            회원가입
          </SignupButton>
        </div>
        <GoogleButton onClick={() => handleAuth("google")}>
          <FaGoogle /> 구글로 로그인
        </GoogleButton>
        <NaverButton onClick={() => handleAuth("naver")}>
          <SiNaver /> 네이버로 로그인
        </NaverButton>
        <KakaoButton onClick={() => handleAuth("kakao")}>
          <SiKakao /> 카카오로 로그인
        </KakaoButton>
      </div>
    </ButtonWrapper>
  );
};

export default MoimLogin;
