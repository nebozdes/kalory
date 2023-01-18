import { useState } from "react";
import { useCreateTagMutation } from "../../store";
import Button from "../Button";
import { validateForm } from "./validation";

function TagsForm() {
  const [doCreateProduct] = useCreateTagMutation();

  const [tagName, setTagName] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    doCreateProduct({
      name: tagName,
    });
    resetForm();
  };

  const formValidationErrors = validateForm(
    [{ key: "tagName", vFn: (value) => value.length > 0 }],
    {
      tagName,
    }
  );

  const resetForm = () => {
    setTagName("");
  };

  return (
    <section className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Product name</label>
          <input
            type="text"
            name="Tag name"
            placeholder="e.g Gluten"
            value={tagName}
            onChange={(e) => setTagName(e.target.value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="create-tags-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Create tag
          </Button>
        </div>
      </form>
    </section>
  );
}

export default TagsForm;
