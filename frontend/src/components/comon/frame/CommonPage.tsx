import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";

const CommonPage: React.FC = () => {
  return (
    <div>
      <Header />
      <Outlet /> {/* 자식 라우트들이 여기 렌더링됨 */}
      <Footer />
    </div>
  );
};

export default CommonPage;
