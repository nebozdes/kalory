import { useState } from "react";
import { useCreateWeightTargetMutation } from "../../store";
import Button from "../Button";
import DatePicker from "../DatePicker";
import { notNull, positive, validateForm } from "./validation";

function WeightTargetForm() {
  const [doCreateWeightCheck] = useCreateWeightTargetMutation();

  const [value, setValue] = useState("");
  const [deadline, setDeadline] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();

    doCreateWeightCheck({
      value,
      deadline,
    });
    resetForm();
  };

  const formValidationErrors = validateForm(
    [
      { key: "value", vFn: positive },
      { key: "deadline", vFn: notNull },
    ],
    {
      value,
      deadline,
    }
  );

  const resetForm = () => {
    setValue("");
    setDeadline(undefined);
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
          <label>Deadline time</label>
          <DatePicker
            value={deadline}
            onChange={(value) => setDeadline(value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="create-target-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Create deadline
          </Button>
        </div>
      </form>
    </section>
  );
}

export default WeightTargetForm;
