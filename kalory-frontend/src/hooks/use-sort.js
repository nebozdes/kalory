import { useState } from "react";

function useSort(data, config) {
  const [sortBy, setSortBy] = useState(null);
  const [sortOrder, setSortOrder] = useState(null);

  const setSortColumn = (label) => {
    if (sortBy !== label) {
      setSortBy(label);
      setSortOrder("asc");
    } else if (sortOrder === "desc") {
      setSortBy(null);
      setSortOrder(null);
    } else if (sortOrder === "asc") {
      setSortOrder("desc");
      setSortBy(label);
    } else if (!sortOrder) {
      setSortOrder("asc");
      setSortBy(label);
    }
  };

  let sortedData = data;

  if (sortBy && sortOrder) {
    const { sortValue } = config.find((column) => {
      return column.label === sortBy;
    });

    const order = sortOrder === "asc" ? 1 : -1;

    sortedData = [...data].sort((a, b) => {
      const valueA = sortValue(a);
      const valueB = sortValue(b);
      if (typeof valueA === "string") {
        return valueA.localeCompare(valueB) * order;
      }
      return (valueA - valueB) * order;
    });
  }

  return {
    sortBy,
    sortOrder,
    sortedData,
    setSortColumn,
  };
}

export default useSort;
