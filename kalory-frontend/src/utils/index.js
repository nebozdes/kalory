const combineWeightData = (checks, deadlines) => {
  return [...checks, ...deadlines].sort((a, b) => {
    return a.date - b.date;
  });
};

const formatForApi = (date) => {
  return date.toISOString().slice(0, 10);
};

const formatShort = (date) => {
  var options = {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
  };
  return date.toLocaleDateString("ru", options);
};

export { combineWeightData, formatShort, formatForApi };
