import { Fragment } from "react";
import ConsumedProductsForm from "../components/forms/ConsumedProductsForm";
import Navigation from "../components/Navigation";
import ConsumedProductsTable from "../components/tables/ConsumedProductsTable";
import "./DashboardPage.css";

function DashboardPage() {
  return (
    <Fragment>
      <Navigation />
      <div className="dashboard-container">
        <section className="dashboard-list-container">
          <ConsumedProductsTable />
        </section>
        <section className="dashboard-form-container">
          <h3>Track a product:</h3>
          <ConsumedProductsForm />
        </section>
      </div>
    </Fragment>
  );
}
export default DashboardPage;
