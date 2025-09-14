import { Outlet } from "react-router-dom";
import ManagerHeader from "./ManagerHeader";

const CommonManagerPage: React.FC = () => {
  return (
    <div
      style={{
        display: "flex",
      }}
    >
      <ManagerHeader />
      <Outlet />
    </div>
  );
};

export default CommonManagerPage;
