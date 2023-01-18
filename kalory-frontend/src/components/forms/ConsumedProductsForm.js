import { useState } from "react";
import {
  useCreateConsumedProductMutation,
  useFetchProductsQuery,
} from "../../store";
import Button from "../Button";
import DatePicker from "../DatePicker";
import { validateForm, notEmpty, positive } from "./validation";

function ConsumedProductsForm() {
  const [doCreateConsumedProduct] = useCreateConsumedProductMutation();
  const { data, error, isFetching } = useFetchProductsQuery({
    page: 0,
    limit: 100, // TODO here should be possibiity to search through products as auto-complete
  });

  const [productId, setProductId] = useState();
  const [consumptionDate, setConsumptionDate] = useState();
  const [consumptionAmount, setConsumptionAmount] = useState();
  const [comment, setComment] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    doCreateConsumedProduct({
      productId,
      consumptionDate,
      consumptionAmount,
      comment,
    });
    resetForm();
  };

  const formValidationErrors = validateForm(
    [
      { key: "productId", vFn: notEmpty },
      { key: "consumptionDate", vFn: notEmpty },
      { key: "consumptionAmount", vFn: positive },
    ],
    {
      productId,
      consumptionDate,
      consumptionAmount,
      comment,
    }
  );

  const resetForm = () => {
    setProductId(undefined);
    setConsumptionDate(undefined);
    setConsumptionAmount(undefined);
    setComment("");
  };

  const isProductDropdownDisabled = error || isFetching;
  const renderedProducts = isProductDropdownDisabled
    ? []
    : data?.content?.map((product) => {
        return (
          <option key={product.id} value={product.id}>
            {product.name}
          </option>
        );
      });

  return (
    <section className="form-container">
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <label>Product</label>
          <select
            value={productId}
            onChange={(e) => setProductId(e.target.value)}
            disabled={isProductDropdownDisabled}
          >
            <option disabled selected>
              Select product
            </option>
            {renderedProducts}
          </select>
        </div>
        <div className="form-row">
          <label>Consumption date</label>
          <DatePicker
            value={consumptionDate}
            onChange={(value) => setConsumptionDate(value)}
          />
        </div>
        <div className="form-row">
          <label>Consumption amount</label>
          <input
            type="number"
            placeholder="e.g 123"
            value={consumptionAmount}
            onChange={(e) => setConsumptionAmount(e.target.value)}
          />
        </div>
        <div className="form-row">
          <label>Comment</label>
          <input
            type="text"
            placeholder="e.g Breakfast"
            value={comment}
            onChange={(e) => setComment(e.target.value)}
          />
        </div>
        <div className="form-row form-button-row">
          <Button
            className="create-consumed-product-button"
            disabled={formValidationErrors.length > 0}
            primary
          >
            Add a track
          </Button>
        </div>
      </form>
    </section>
  );
}

export default ConsumedProductsForm;
