import Button from "../Button";
import { useDeleteProductMutation, useFetchProductsQuery } from "../../store";
import SortableTable from "./SortableTable";
import Pagination from "./Pagination";
import { useState } from "react";

const ProductsTable = () => {
  const [page, setPage] = useState(0);
  const { data, error, isFetching } = useFetchProductsQuery({
    page: page,
    limit: 10,
  });

  const [doDelete] = useDeleteProductMutation();

  if (isFetching) {
    return;
  }

  if (error) {
    return "Error while loading product data...";
  }

  const tableConfig = [
    {
      label: "Name",
      render: (row) => row.name,
      sortValue: (row) => row.name,
    },
    {
      label: "Base amount",
      render: (row) =>
        `${row.baseAmount} ${baseAmountTypeString(row.baseAmountType)}`,
    },
    {
      label: "Calories",
      render: (row) => row.baseCalories,
      sortValue: (row) => row.baseCalories,
    },
    {
      label: "Carbs",
      render: (row) => row.baseCarbs,
      sortValue: (row) => row.baseCarbs,
    },
    {
      label: "Lipids",
      render: (row) => row.baseLipids,
      sortValue: (row) => row.baseLipids,
    },
    {
      label: "Proteins",
      render: (row) => row.baseProteins,
      sortValue: (row) => row.baseProteins,
    },
    {
      type: "actions",
      render: (row) => {
        return (
          <Button danger onClick={() => handleDelete(row)}>
            Delete
          </Button>
        );
      },
    },
  ];

  const handleDelete = (product) => {
    doDelete(product);
    setPage(0);
  };

  const handlePageChange = (data) => {
    setPage(data.selected);
  };

  return (
    <div className="table-with-pagination-container">
      <Pagination
        onPageChange={handlePageChange}
        pageCount={data.totalPages}
        forcePage={page}
      />
      <SortableTable
        className="products-list"
        data={data.content}
        config={tableConfig}
        keyFn={(row) => row.id}
      />
    </div>
  );
};

const baseAmountTypeString = (baseAmountTypeEnum) => {
  switch (baseAmountTypeEnum) {
    case "GRAM":
      return "g";
    case "MILLI":
      return "ml";
    case "COUNT":
      return "pcs";
    default:
      return "g";
  }
};

export default ProductsTable;
