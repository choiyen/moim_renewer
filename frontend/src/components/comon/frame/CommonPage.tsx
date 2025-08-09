import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";

const CommonPage: React.FC = () => {
  return (
    <div style={{ paddingBottom: "80px" }}>
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
};

export default CommonPage;
