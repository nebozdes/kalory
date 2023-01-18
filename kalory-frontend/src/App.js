import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import { useGetCurrentUserQuery } from "./store";

import LoginPage from "./pages/LoginPage";
import DashboardPage from "./pages/DashboardPage";
import ProductsPage from "./pages/ProductsPage";
import MeasurementsPage from "./pages/MeasurementsPage";
import TagsPage from "./pages/TagsPage";

import AuthRoute from "./components/routes/AuthRoute";

function App() {
  const { data, isError, isFetching } = useGetCurrentUserQuery();

  if (isFetching) {
    return; // maybe show a global spinner?
  }

  const isAuthenticated = !isError;

  return (
    <Router>
      <Routes>
        <Route
          path="/login"
          element={<LoginPage isAuthenticated={isAuthenticated} />}
        />
        <Route
          path="/"
          element={
            <AuthRoute isAuthenticated={isAuthenticated}>
              <DashboardPage />
            </AuthRoute>
          }
        />
        <Route
          path="/dashboard"
          element={
            <AuthRoute isAuthenticated={isAuthenticated}>
              <DashboardPage />
            </AuthRoute>
          }
        />
        <Route
          path="/measurements"
          element={
            <AuthRoute isAuthenticated={isAuthenticated}>
              <MeasurementsPage />
            </AuthRoute>
          }
        />
        <Route
          path="/products"
          element={
            <AuthRoute isAuthenticated={isAuthenticated}>
              <ProductsPage />
            </AuthRoute>
          }
        />
        <Route
          path="/tags"
          element={
            <AuthRoute isAuthenticated={isAuthenticated}>
              <TagsPage />
            </AuthRoute>
          }
        />
      </Routes>
    </Router>
  );
}
export default App;
