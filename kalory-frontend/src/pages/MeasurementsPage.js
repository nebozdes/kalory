import Navigation from "../components/Navigation";
import WeightCheckForm from "../components/forms/WeightCheckForm";
import WeightTargetForm from "../components/forms/WeightTargetForm";
import MeasurementsTable from "../components/tables/MeasurementsTable";
import { Fragment } from "react";
import "./MeasurementsPage.css";

function MeasurementsPage() {
  return (
    <Fragment>
      <Navigation />
      <div className="measurements-container">
        <section className="measurements-list-container">
          <MeasurementsTable />
        </section>
        <section className="measurements-checks-form-container">
          <h3>Check your weight</h3>
          <WeightCheckForm />
        </section>
        <section className="measurements-targets-form-container">
          <h3>Set a deadline</h3>
          <WeightTargetForm />
        </section>
      </div>
    </Fragment>
  );
}

export default MeasurementsPage;
