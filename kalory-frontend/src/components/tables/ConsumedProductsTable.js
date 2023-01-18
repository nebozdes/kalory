import Button from "../Button";
import {
  useDeleteConsumedProductMutation,
  useFetchConsumedProductsQuery,
} from "../../store";
import SortableTable from "./SortableTable";
import { useState } from "react";
import DatePagination from "./DatePagination";
import { formatForApi } from "../../utils";

const ConsumedProductsTable = () => {
  const [date, setDate] = useState(new Date());
  const { data, error, isFetching } = useFetchConsumedProductsQuery(
    formatForApi(date)
  );
  const [doDelete] = useDeleteConsumedProductMutation();

  if (isFetching) {
    return;
  }

  if (error) {
    return "Error while loading consumed product data...";
  }

  const tableConfig = [
    {
      label: "Product name",
      render: (row) => row.productName,
    },
    {
      label: "Consumed amount",
      render: (row) => row.consumptionAmount,
    },
    {
      label: "Comment",
      render: (row) => row.comment,
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

  const handleDelete = (consumedProduct) => {
    doDelete(consumedProduct);
  };

  const handleDateChange = (date) => {
    setDate(date);
  };

  return (
    <div className="table-with-pagination-container">
      <DatePagination onDateChange={handleDateChange} forcedDate={date} />
      <SortableTable
        data={data.content}
        config={tableConfig}
        keyFn={(row) => row.id}
      />
    </div>
  );
};

export default ConsumedProductsTable;
