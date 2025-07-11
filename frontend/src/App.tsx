import {
  createBrowserRouter,
  Navigate,
  RouterProvider,
} from "react-router-dom";

import "./App.css";
import CommonPage from "./components/comon/CommonPage";
import GlobalErrorBoundary from "./components/errors/GlobalErrorBoundary";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/home" replace />,
  },
  {
    path: "/home",
    element: <CommonPage />,
    errorElement: <GlobalErrorBoundary />,
    children: [],
  },
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
