import { useEffect, useState } from "react";
import { FaComments, FaHome, FaMapMarkedAlt, FaUser } from "react-icons/fa";
import { Link } from "react-router-dom";
import { Lock } from "lucide-react";

interface UserProps {
  token: string | null; // 토큰이 없을 수도 있으니 null 허용
}

const Footer = ({ token }: UserProps) => {
  const [login, setLogin] = useState<boolean>(false);

  useEffect(() => {
    console.log("현재 토큰:", token);
    setLogin(!!token);
  }, [token]);

  return (
    <footer className="fixed bottom-0 left-0 w-full bg-gray-800 text-white p-3">
      <nav className="flex space-x-16 justify-evenly items-center h-16 p-4">
        <Link
          className="flex flex-col items-center space-y-1 cursor-pointer"
          to={"/home"}
        >
          <FaHome size={30} />
          <span>홈</span>
        </Link>
        <Link
          className="flex flex-col items-center space-y-1 cursor-pointer"
          to={"/map"}
        >
          <FaMapMarkedAlt size={30} />
          <span>지도</span>
        </Link>
        <Link
          className="flex flex-col items-center space-y-1 cursor-pointer"
          to={"/consult"}
        >
          <FaComments size={30} />
          <span>상담</span>
        </Link>

        {login ? (
          <Link
            className="flex flex-col items-center space-y-1 cursor-pointer"
            to={"/profile"}
          >
            <FaUser size={30} />
            <span>마이페이지</span>
          </Link>
        ) : (
          <Link
            className="flex flex-col items-center space-y-1 cursor-pointer"
            to={"/login"}
          >
            <Lock size={30} />
            <span>로그인</span>
          </Link>
        )}
      </nav>
    </footer>
  );
};
export default Footer;
