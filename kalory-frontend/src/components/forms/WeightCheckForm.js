import { useState } from "react";
import { useCreateWeightCheckMutation } from "../../store";
import Button from "../Button";
import { notNull, positive, validateForm } from "./validation";

import DatePicker from "../DatePicker";

function WeightCheckForm() {
  const [doCreateWeightCheck] = useCreateWeightCheckMutation();

  const [value, setValue] = useState("");
  const [checkTime, setCheckTime] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();

    doCreateWeightCheck({
      value,
      checkTime: checkTime,
    });
    resetForm();
  };

  const formValidationErrors = validateForm(
    [
      { key: "value", vFn: positive },
      { key: "checkTime", vFn: notNull },
    ],
    {
      value,
      checkTime,
    }
  );

  const resetForm = () => {
    setValue("");
    setCheckTime(undefined);
  };

  return (
    <section className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Weight value (in kgs)</label>
          <input
            type="number"
            name="Weight value"
            placeholder="e.g 65"
            value={value}
            onChange={(e) => setValue(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Check time</label>
          <DatePicker
            value={checkTime}
            onChange={(value) => setCheckTime(value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="create-check-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Save check
          </Button>
        </div>
      </form>
    </section>
  );
}

export default WeightCheckForm;
