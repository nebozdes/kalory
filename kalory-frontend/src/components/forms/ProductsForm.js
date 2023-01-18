import { useState } from "react";
import { useCreateProductMutation } from "../../store";
import Button from "../Button";
import { validateForm, notEmpty, positive, positiveOrZero } from "./validation";

function ProductsForm() {
  const [doCreateProduct] = useCreateProductMutation();

  const [name, setName] = useState("");
  const [baseAmount, setBaseAmount] = useState();
  const [baseAmountType, setBaseAmountType] = useState("GRAM");
  const [baseCalories, setBaseCalories] = useState();
  const [baseCarbs, setBaseCarbs] = useState();
  const [baseLipids, setBaseLipids] = useState();
  const [baseProteins, setBaseProteins] = useState();

  const handleSubmit = (e) => {
    e.preventDefault();

    doCreateProduct({
      name,
      baseAmount,
      baseAmountType,
      baseCalories,
      baseCarbs,
      baseLipids,
      baseProteins,
    });
    resetForm();
  };

  const formValidationErrors = validateForm(
    [
      { key: "name", vFn: notEmpty },
      { key: "baseAmount", vFn: positive },
      { key: "baseAmountType", vFn: notEmpty },
      { key: "baseCalories", vFn: positive },
      { key: "baseCarbs", vFn: positiveOrZero },
      { key: "baseLipids", vFn: positiveOrZero },
      { key: "baseProteins", vFn: positiveOrZero },
    ],
    {
      name,
      baseAmount,
      baseAmountType,
      baseCalories,
      baseCarbs,
      baseLipids,
      baseProteins,
    }
  );

  const resetForm = () => {
    setName("");
    setBaseAmount();
    setBaseAmountType("GRAM");
    setBaseCalories();
    setBaseCarbs();
    setBaseLipids();
    setBaseProteins();
  };

  return (
    <section className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Product name</label>
          <input
            type="text"
            placeholder="e.g Coca-cola"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Base amount</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={baseAmount}
            onChange={(e) => setBaseAmount(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Base amount type</label>
          <select
            value={baseAmountType}
            onChange={(e) => setBaseAmountType(e.target.value)}
          >
            <option value="GRAM">Grams</option>
            <option value="MILLI">Millis</option>
            <option value="COUNT">Pieces</option>
          </select>
        </div>
        <div className="form-row">
          <label>Base calories</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={baseCalories}
            onChange={(e) => setBaseCalories(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Base carbs</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={baseCarbs}
            onChange={(e) => setBaseCarbs(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Base lipds</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={baseLipids}
            onChange={(e) => setBaseLipids(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Base proteins</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={baseProteins}
            onChange={(e) => setBaseProteins(e.target.value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="create-product-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Create product
          </Button>
        </div>
      </form>
    </section>
  );
}

export default ProductsForm;
