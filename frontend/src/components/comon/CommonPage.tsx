import { Outlet } from "react-router-dom";

const CommonPage: React.FC = () => {
  return (
    <div>
      <Outlet /> {/* 자식 라우트들이 여기 렌더링됨 */}
    </div>
  );
};

export default CommonPage;
