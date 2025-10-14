import { Outlet } from "react-router-dom";
import ManagerHeader from "./ManagerHeader";
import PrivateRoute from "./PrivateRoute";

const CommonManagerPage: React.FC = () => {
  return (
    <div
      style={{
        display: "flex",
      }}
    >
      <ManagerHeader />
      <PrivateRoute name={"관리자 페이지"}>
        <Outlet />
      </PrivateRoute>
    </div>
  );
};

export default CommonManagerPage;
