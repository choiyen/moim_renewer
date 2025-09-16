import {
  Sparkles,
  ChevronsRight,
  LogOut,
  Settings,
  Users,
  Home,
  Lock,
} from "lucide-react";
import { Head } from "./Head";
import styled from "@emotion/styled";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { FaCrown, FaBolt, FaUser } from "react-icons/fa";
import { DELETE, GET } from "../axios/axiosInstance";

interface Props {
  expanded: boolean;
}
interface RoleBadgeProps {
  role: "OWNER" | "MANAGER" | "MEMBER";
}

const Badge = styled.span<{ color: string }>`
  display: inline-flex;
  align-items: center;
  gap: 0.25rem;
  padding: 0.2rem 0.5rem;
  font-size: 1rem;
  font-weight: bold;
  background-color: ${(props) => props.color};
  color: white;
`;

const RoleBadge = ({ role }: RoleBadgeProps) => {
  let icon, color, label;

  switch (role) {
    case "OWNER":
      icon = <FaCrown />;
      color = "#FACC15"; // 노란색
      label = "운영자";
      break;
    case "MANAGER":
      icon = <FaBolt />;
      color = "#F97316"; // 주황색
      label = "매니저";
      break;
    case "MEMBER":
      icon = <FaUser />;
      color = "#3B82F6"; // 파란색
      label = "멤버";
      break;
  }

  return (
    <Badge color={color}>
      {icon} {label}
    </Badge>
  );
};

// Header: 너비 토글, 애니메이션 적용
const Header = styled.header<Props>`
  background-color: #f7fafc;
  width: ${(props) => (props.expanded ? "25vw" : "10vw")};
  height: 100vh;
  display: flex;
  flex-direction: column;
  transition: width 0.3s;
  justify-content: space-between;
  padding: 1rem;
`;

// Logo: 크기 토글, 중앙 정렬
const Logo = styled.h1<Props>`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 0.25rem;
  font-size: ${(props) => (props.expanded ? "1.5rem" : "0.75rem")};
  font-weight: bold;
  color: #4a5568;
  transition: font-size 0.3s;
  padding: 10px 0;
`;
const LogoutContainer = styled.div<Props>`
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-bottom: 40px;
  padding: 20px;

  & > span {
    font-size: ${(props) => (props.expanded ? "1rem" : "0.75rem")};
    color: #4a5568;
  }
`;

const ManagerContainer = styled.div<Props>`
  width: 100%;
  background-color: #e2e8f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 1rem;
  display: ${(props) => (props.expanded ? "block" : "none")};
  & > div {
    font-size: ${(props) => (props.expanded ? "1rem" : "0.875rem")};
  }
`;

// Container: 로고와 아이콘을 가로로 배치
const Container = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const TypeButton = styled.div<Props>`
  background-color: transparent;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 0.5rem;
  padding: 0.5rem 1rem;
  font-size: ${(props) => (props.expanded ? "1rem" : "0.7rem")};
  color: #4a5568;

  &:hover {
    background-color: #edf2f7;
  }
`;

interface UserProps {
  nickname: string;
  type: "MANAGER" | "MEMBER";
  userId: string;
}

const ManagerHeader = () => {
  const [expanded, setExpanded] = useState(true);
  const [loginState, setloginState] = useState(false);
  const [UserState] = useState<UserProps>(() => {
    const userString = localStorage.getItem("user");
    return userString
      ? JSON.parse(userString)
      : { nickname: "", type: "MEMBER", userId: "" };
  });

  useEffect(() => {
    GET({
      url: "/user/check",
    }).then((res) => {
      console.log(res);
      if (res.resultType == "success") setloginState(true);
      else setloginState(false);
    });
  }, []);

  const handleClick = () => {
    setExpanded((prev) => !prev); // 클릭 시 토글
  };
  const navigate = useNavigate();

  const Logining = () => {
    console.log("현재 로그인 상태 : " + loginState);
    if (loginState) {
      DELETE({
        url: "/user/signout",
      }).then((res) => {
        alert("로그아웃 기능 동작 완료");
        localStorage.clear();
        console.log(res);
        navigate("/home");
      });
    } else {
      navigate("/login");
    }
  };

  return (
    <Header expanded={expanded}>
      <Head
        title="Moim 프로젝트"
        description="많은 사람들과 만나보아요? Moim에서...!"
      />

      <Container>
        <Logo expanded={expanded}>
          {expanded ? (
            <>
              <Sparkles size={36} />
              Moim
              <Sparkles size={36} />
            </>
          ) : (
            <>
              <Sparkles size={18} />
              Moim
              <Sparkles size={18} />
            </>
          )}
        </Logo>

        <ChevronsRight
          size={expanded ? 32 : 24} // 아이콘 크기 증가
          style={{
            cursor: "pointer",
            transition: "transform 0.3s",
            transform: expanded ? "rotate(180deg)" : "rotate(0deg)", // 회전
          }}
          onClick={handleClick}
        />
      </Container>
      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "20px",
          padding: "10px 0",
        }}
      >
        <TypeButton
          expanded={expanded}
          onClick={() => navigate("/category/Consult")}
        >
          <Users size={expanded ? 32 : 24} />
          모임 권한 설정
        </TypeButton>
        <TypeButton
          expanded={expanded}
          onClick={() => navigate("/category/Moim")}
        >
          <Settings size={expanded ? 32 : 24} />
          운영자 설정
        </TypeButton>
        <TypeButton expanded={expanded} onClick={() => navigate("/home")}>
          <Home size={expanded ? 32 : 24} />
          홈으로
        </TypeButton>
      </div>
      <div>
        <LogoutContainer expanded={expanded} onClick={() => Logining()}>
          {loginState ? (
            <>
              <LogOut size={expanded ? 32 : 24} style={{ cursor: "pointer" }} />
              <span>로그아웃</span>
            </>
          ) : (
            <>
              <Lock size={expanded ? 32 : 24} style={{ cursor: "pointer" }} />
              <span>로그인</span>
            </>
          )}
        </LogoutContainer>
        <ManagerContainer expanded={expanded}>
          <div
            style={{
              display: "flex",
              alignItems: "center",
              justifyContent: "space-between",
              marginBottom: "5px",
            }}
          >
            <RoleBadge
              role={UserState.type as "OWNER" | "MANAGER" | "MEMBER"}
            />
            {UserState.nickname}
          </div>
          <div>{UserState.userId}</div>
        </ManagerContainer>
      </div>
    </Header>
  );
};

export default ManagerHeader;
