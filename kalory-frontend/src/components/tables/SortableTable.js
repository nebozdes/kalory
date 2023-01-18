import Table from "./Table";
import useSort from "../../hooks/use-sort";
import { GoArrowSmallUp, GoArrowSmallDown } from "react-icons/go";
import "./SortableTable.css";

function SortableTable(props) {
  const { data, config } = props;

  const { sortedData, sortBy, sortOrder, setSortColumn } = useSort(
    data,
    config
  );

  const updatedConfig = config.map((column, index) => {
    if (!column.sortValue) {
      return column;
    }
    return {
      ...column,
      header: () => (
        <th
          className="sortable-table-header"
          onClick={() => setSortColumn(column.label)}
        >
          <div className="icons-container">
            {column.label}
            {getIcons(column.label, sortBy, sortOrder)}
          </div>
        </th>
      ),
    };
  });

  return (
    <div>
      <Table {...props} data={sortedData} config={updatedConfig} />
    </div>
  );
}

function getIcons(label, sortBy, sortOrder) {
  if (label !== sortBy || !sortOrder) {
    return (
      <div>
        <GoArrowSmallUp />
        <GoArrowSmallDown />
      </div>
    );
  }
  if (sortOrder === "asc") {
    return (
      <div>
        <GoArrowSmallDown />
      </div>
    );
  }
  if (sortOrder === "desc") {
    return (
      <div>
        <GoArrowSmallUp />
      </div>
    );
  }
}
export default SortableTable;
