import {
  ReferenceLine,
  LineChart,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  Line,
} from "recharts";
import { formatShort } from "../../utils";

function MeasurementsTrend({ checks, deadlines }) {
  const sortedChecks = checks.sort((a, b) => {
    return a.date - b.date;
  });

  const renderedDeadlines = deadlines.map((deadline) => {
    return (
      <ReferenceLine
        key={deadline.id}
        y={deadline.value}
        label={formatShort(deadline.date)}
        stroke="#994f42"
        strokeWidth={2}
      />
    );
  });

  return (
    <LineChart
      width={600}
      height={400}
      data={sortedChecks}
      margin={{ top: 5, right: 20, left: 10, bottom: 5 }}
    >
      <XAxis dataKey={(point) => formatShort(point.date)} />
      <YAxis
        dataKey="value"
        type="number"
        domain={["dataMin - 10", "dataMax + 10"]}
      />

      <Tooltip />
      <CartesianGrid stroke="#aaa" />
      <Line
        type="monotone"
        dataKey="value"
        stroke="#528265"
        yAxisId={0}
        strokeWidth={3}
      />
      {renderedDeadlines}
    </LineChart>
  );
}

export default MeasurementsTrend;
