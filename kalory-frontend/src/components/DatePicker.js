import ReactDatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

const DatePicker = ({ value, onChange }) => {
  return (
    <ReactDatePicker
      selected={value}
      onChange={onChange}
      placeholder="2022-10-25"
      dateFormat="dd.MM.yyyy"
    />
  );
};

export default DatePicker;
