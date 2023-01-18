import Navigation from "../components/Navigation";
import TagsForm from "../components/forms/TagsForm";
import TagsTable from "../components/tables/TagsTable";
import { Fragment } from "react";
import "./TagsPage.css";

function TagsPage() {
  return (
    <Fragment>
      <Navigation />
      <div className="tags-container">
        <section className="tags-list-container">
          <TagsTable />
        </section>
        <section className="tags-form-container">
          <h3>Create a new tag</h3>
          <TagsForm />
        </section>
      </div>
    </Fragment>
  );
}

export default TagsPage;
