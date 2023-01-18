import className from "classnames";
import "./Button.css";

function Button({ children, primary, secondary, danger, ...rest }) {
  const classes = className(rest.className, "button-form", {
    "button-primary": primary,
    "button-secondary": secondary,
    "button-danger": danger,
    "button-disabled": rest.disabled,
  });

  return (
    <button {...rest} className={classes}>
      {children}
    </button>
  );
}

export default Button;

Button.propTypes = {
  checkVariationValue: ({ primary, secondary, danger }) => {
    const count = Number(!!primary) + Number(!!secondary) + Number(!!danger);

    if (count > 1) {
      throw new Error("Only one button type should present!");
    }
  },
};
