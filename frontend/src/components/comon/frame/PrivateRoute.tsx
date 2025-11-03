import { Navigate } from "react-router-dom";
import { isTokenExpired } from "../axios/jwtutil";
import type { JSX } from "react";

const PrivateRoute = ({
  children,
  name,
}: {
  children: JSX.Element;
  name: string;
}) => {
  const token = localStorage.getItem("accessToken");

  if (!token || isTokenExpired(token)) {
    // 토큰이 없거나 만료된 경우
    localStorage.removeItem("accessToken");
    localStorage.removeItem("user");
    alert(name + "은 로그인 후 이용 가능한 기능입니다.");
    return <Navigate to="/login" replace />;
  }

  return children;
};

export default PrivateRoute;
