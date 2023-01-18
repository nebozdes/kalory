import { Fragment } from "react";
import "./Table.css";

function Table({ data, config, keyFn }) {
  const renderedHeaders = config.map((column, index) => {
    if (column.type === "actions") {
      return <th key={column.label + index} className="actions"></th>;
    }

    return column.header ? (
      <Fragment key={column.label}>{column.header()}</Fragment>
    ) : (
      <th key={column.label}>{column.label}</th>
    );
  });

  const renderedRows = data.map((row, index) => {
    const renderedCells = config.map((column, index) => {
      const additionalClass = column.type === "actions" ? "actions" : "";
      return (
        <td key={column.label + row.id} className={additionalClass}>
          {column.render(row)}
        </td>
      );
    });

    return <tr key={keyFn(row)}>{renderedCells}</tr>;
  });

  return (
    <table className="table">
      <thead>
        <tr className="table-header">{renderedHeaders}</tr>
      </thead>
      <tbody>{renderedRows}</tbody>
    </table>
  );
}
export default Table;
