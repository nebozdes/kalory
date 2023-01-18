import Button from "../Button";
import { useDeleteTagMutation, useFetchTagsQuery } from "../../store";
import SortableTable from "./SortableTable";
import Pagination from "./Pagination";
import { useState } from "react";

const ProductsTable = () => {
  const [page, setPage] = useState(0);
  const { data, error, isFetching } = useFetchTagsQuery({
    page: page,
    limit: 3,
  });

  const [doDelete] = useDeleteTagMutation();

  if (isFetching) {
    return;
  }

  if (error) {
    return "Error while loading tag data...";
  }

  const tableConfig = [
    {
      label: "Name",
      render: (row) => row.name,
      sortValue: (row) => row.name,
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

  const handleDelete = (tag) => {
    doDelete(tag);
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
        className="tags-list"
        data={data.content}
        config={tableConfig}
        keyFn={(row) => row.id}
      />
    </div>
  );
};

export default ProductsTable;
