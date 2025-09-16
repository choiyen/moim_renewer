import { Outlet } from "react-router-dom";
import Footer from "./Footer";
import Header from "./Header";
import { useState } from "react";

const CommonPage: React.FC = () => {
  const [token] = useState<string | null>(localStorage.getItem("accessToken"));

  return (
    <div style={{ paddingBottom: "80px" }}>
      <Header />
      <Outlet />
      <Footer token={token} />
    </div>
  );
};

export default CommonPage;
