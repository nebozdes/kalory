import ReactPaginate from "react-paginate";
import "./Pagination.css";
import { formatShort } from "../../utils";

function DatePagination({ onDateChange, forcedDate }) {
  const renderDate = (date, className) => {
    return (
      <li>
        <a className={className} onClick={() => onDateChange(date)}>
          {formatShort(date)}
        </a>
      </li>
    );
  };

  return (
    <div className="pagination-container">
      <ul>
        {renderDate(addNDays(forcedDate, -3))}
        {renderDate(addNDays(forcedDate, -2))}
        {renderDate(addNDays(forcedDate, -1))}
        {renderDate(forcedDate, "pagination-active-page")}
        {renderDate(addNDays(forcedDate, 1))}
        {renderDate(addNDays(forcedDate, 2))}
        {renderDate(addNDays(forcedDate, 3))}
      </ul>
    </div>
  );
}

const addNDays = (date, n) => {
  let result = new Date(date);
  result.setDate(result.getDate() + n);
  return result;
};

export default DatePagination;
