import "./LoginPage.css";
import logo from "../img/logo-no-background.png";
import LoginForm from "../components/forms/LoginForm";
import { Navigate } from "react-router";

function LoginPage({ isAuthenticated }) {
  if (isAuthenticated) {
    return <Navigate to="/" />;
  }
  return (
    <div className="container">
      <div className="login-container">
        <section className="logo-container">
          <img alt="logo" className="logo" src={logo} />
        </section>
        <LoginForm />
      </div>
    </div>
  );
}
export default LoginPage;
