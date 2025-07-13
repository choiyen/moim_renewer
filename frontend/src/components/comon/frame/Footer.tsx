import { FaComments, FaHome, FaMapMarkedAlt, FaUser } from "react-icons/fa";
import { Link } from "react-router-dom";

const Footer = () => {
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
        <Link
          className="flex flex-col items-center space-y-1 cursor-pointer"
          to={"/profile"}
        >
          <FaUser size={30} />
          <span>마이페이지</span>
        </Link>
      </nav>
    </footer>
  );
};
export default Footer;
