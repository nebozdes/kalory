import { useState } from "react";
import {
  useDeleteWeightCheckMutation,
  useDeleteWeightTargetMutation,
  useFetchWeightChecksQuery,
  useFetchWeightTargetsQuery,
} from "../../store";
import { combineWeightData, formatShort } from "../../utils";
import Button from "../Button";
import MeasurementsTrend from "../trends/MeasurementsTrend";
import SortableTable from "./SortableTable";

function MeasurementsTable() {
  const [showTable, setShowTable] = useState(true);

  const {
    data: checksData,
    error: checksError,
    isFetching: checksAreFetching,
  } = useFetchWeightChecksQuery();
  const {
    data: deadlineData,
    error: deadlineError,
    isFetching: deadlineAreFetching,
  } = useFetchWeightTargetsQuery();

  const [doDeleteCheck] = useDeleteWeightCheckMutation();
  const [doDeleteDeadline] = useDeleteWeightTargetMutation();

  if (checksAreFetching || deadlineAreFetching) {
    return;
  }

  if (checksError || deadlineError) {
    return "Error while loading measurements data...";
  }

  const mappedChecksData = checksData.content.map((check) => {
    return {
      id: check.id,
      value: check.value,
      date: new Date(check.checkTime),
      type: "check",
      key: "check" + check.id,
    };
  });
  const mappedDeadlinesData = deadlineData.content.map((deadline) => {
    return {
      id: deadline.id,
      value: deadline.value,
      date: new Date(deadline.deadline),
      type: "deadline",
      key: "deadline" + deadline.id,
    };
  });

  const handleDelete = (weight) => {
    switch (weight.type) {
      case "deadline":
        doDeleteDeadline(weight);
        return;
      case "check":
        doDeleteCheck(weight);
        return;
      default:
        return;
    }
  };

  return (
    <div>
      <div
        onClick={() => setShowTable(!showTable)}
        className="measurements-view-toggler"
      >
        {showTable ? "Show timeline" : "Show table"}{" "}
      </div>
      <div className="measurements-table-trend-container">
        {showTable
          ? renderTable(mappedChecksData, mappedDeadlinesData, handleDelete)
          : renderTrend(mappedChecksData, mappedDeadlinesData)}
      </div>
    </div>
  );
}

const renderTable = (checksData, deadlinesData, handleDelete) => {
  const data = combineWeightData(checksData, deadlinesData);

  const tableConfig = [
    {
      label: "Value",
      render: (row) => {
        switch (row.type) {
          case "deadline":
            return `Target: ${row.value} kgs`;
          case "check":
            return `Check: ${row.value} kgs`;
          default:
            return "???";
        }
      },
    },
    {
      label: "Date",
      render: (row) => formatShort(row.date),
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

  return (
    <SortableTable data={data} config={tableConfig} keyFn={(row) => row.key} />
  );
};

const renderTrend = (checksData, deadlinesData) => {
  return <MeasurementsTrend checks={checksData} deadlines={deadlinesData} />;
};

export default MeasurementsTable;
