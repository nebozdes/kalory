import { Link } from "react-router-dom";
import { useGetCurrentUserQuery, useLogoutMutation } from "../store";
import "./Navigation.css";

function Navigation() {
  const { data } = useGetCurrentUserQuery();
  const [doLogout] = useLogoutMutation();

  const links = [
    { label: "Dashboard", path: "/" },
    { label: "Measurements", path: "/measurements" },
  ];

  if (data?.roles?.indexOf("ADMINISTRATOR") > -1) {
    links.push({ label: "Products", path: "/products" });
    links.push({ label: "Tags", path: "/tags" });
  }

  const renderedLinks = links.map((link, index) => {
    return (
      <Link key={link.label} to={link.path} className="navigation-link">
        {link.label}
      </Link>
    );
  });

  const handleLogout = () => {
    doLogout();
  };

  return (
    <div className="navigation-container">
      <div className="logo-container">
        <span className="text-logo">KALORY</span>
      </div>
      <div className="links-container">{renderedLinks}</div>
      <div className="account-container">
        <div className="account-name">{data.login}</div>
        &nbsp;
        <div
          className="logout-button"
          onClick={handleLogout}
          data-tooltip="Logout"
        >
          (Logout)
        </div>
      </div>
    </div>
  );
}

export default Navigation;
