import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";

import "./App.css";
import CommonPage from "./components/comon/frame/CommonPage";
import GlobalErrorBoundary from "./components/errors/GlobalErrorBoundary";
import MainPage from "./components/page/Main/MainPage";
import MoimInsert from "./components/page/Moim/MoimInsert";
import MoimEdit from "./components/page/Moim/MoimEdit";
import MoimError from "./components/page/Error/MoimError";
import MoimProfile from "./components/page/Login/MoimProFile";
import MoimReview from "./components/page/Moim/MoimReview";
import MoimLogin from "./components/page/Login/MoimLogin";
import MoimSignUp from "./components/page/Login/MoimSignUp";
import MoimProfileEdit from "./components/page/Login/MoimProfileEdit";
import MoimConsult from "./components/page/Consult/MoimConsult";
import MoimDetail from "./components/page/Moim/MoimDetail";
import ConsultInsert from "./components/page/Consult/ConsultInsert";
import MoimMap from "./components/page/Map/MoimMap";
import ConsultSelect from "./components/page/Consult/ConsultSelect";
import { ToastContainer } from "react-toastify";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/home" replace />,
  },
  {
    element: <CommonPage />,
    errorElement: <GlobalErrorBoundary />,
    children: [
      { path: "/home", element: <MainPage /> }, // 홈 페이지
      { path: "/map", element: <MoimMap /> }, // 지도 페이지
      { path: "/consult", element: <MoimConsult /> }, // 상담 페이지
      { path: "/consult/insert", element: <ConsultInsert /> }, //상담 생성 페이지
      { path: "/login", element: <MoimLogin /> }, // 로그인 페이지
      { path: "/profile", element: <MoimProfile /> }, // 마이페이지
      { path: "/profile/edit", element: <MoimProfileEdit /> }, // 프로필 수정 페이지
      { path: "/signup", element: <MoimSignUp /> }, // 회원가입 페이지
      { path: "/moim/insert", element: <MoimInsert /> }, // 모임 등록 페이지
      { path: "/moim/edit", element: <MoimEdit /> }, // 모임 수정 페이지
      { path: "/moim/detail/:id", element: <MoimDetail /> }, // 모임 상세 페이지
      {
        path: "/consult/select/:id",
        element: <ConsultSelect />,
      },
      {
        path: "/moim/review/:id",
        element: <MoimReview Titleday={new Date()} />,
      }, // 모임 리뷰 페이지
      { path: "/*", element: <MoimError /> }, // 잘못된 경로 접근 시 에러 페이지
    ],
  },
]);

function App() {
  return (
    <>
      <RouterProvider router={router} />
      <ToastContainer
        position="top-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        limit={1} // 한 번에 하나만 표시
      />
      ;
    </>
  );
}

export default App;
