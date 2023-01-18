import Navigation from "../components/Navigation";
import ProductsForm from "../components/forms/ProductsForm";
import ProductsTable from "../components/tables/ProductsTable";
import { Fragment } from "react";
import "./ProductsPage.css";

function ProductsPage() {
  return (
    <Fragment>
      <Navigation />
      <div className="products-container">
        <section className="products-list-container">
          <ProductsTable />
        </section>
        <section className="products-form-container">
          <h3>Create a new product</h3>
          <ProductsForm />
        </section>
      </div>
    </Fragment>
  );
}

export default ProductsPage;
