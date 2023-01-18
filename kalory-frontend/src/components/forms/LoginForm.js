import { useState } from "react";
import { useLoginMutation } from "../../store";
import Button from "../Button";
import { validateForm, notEmpty } from "./validation";

function LoginForm() {
  const [doLogin] = useLoginMutation();

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    doLogin({ username, password });
  };

  const formValidationErrors = validateForm(
    [
      { key: "username", vFn: notEmpty },
      { key: "password", vFn: notEmpty },
    ],
    {
      username,
      password,
    }
  );

  return (
    <section className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Username</label>
          <input
            type="text"
            name="username"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Password</label>
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="login-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Login
          </Button>
        </div>
      </form>
    </section>
  );
}

export default LoginForm;
