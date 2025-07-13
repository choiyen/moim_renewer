import { Head } from "./Head";
import { FaSearch } from "react-icons/fa";

const Header: React.FC = () => {
  return (
    <header className="bg-gray-200 p-5 flex justify-between items-center">
      <Head
        title="Moim 프로젝트"
        description="많은 사람들과 만나보아요? Moim에서...!"
      />
      {/* 로고 */}
      <h1 className="text-xl font-bold text-gray-800">Moim</h1>
      {/* 검색창 */}
      <div className="flex items-center w-full max-w-md space-x-2 bg-black p-2 rounded-2xl h-14">
        <FaSearch className="text-white" size={20} />
        <input
          type="text"
          placeholder="찾으시는 모임을 입력해주세요"
          className="border border-gray-300 p-2 rounded-md w-full focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      </div>
    </header>
  );
};

export default Header;
