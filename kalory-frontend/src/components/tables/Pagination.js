import ReactPaginate from "react-paginate";
import "./Pagination.css";

function Pagination({ onPageChange, pageCount, forcePage }) {
  return (
    <div className="pagination-container">
      <ReactPaginate
        breakLabel="..."
        nextLabel="next  >>"
        onPageChange={onPageChange}
        pageRangeDisplayed={5}
        pageCount={pageCount}
        previousLabel="<<  prev"
        forcePage={forcePage}
        renderOnZeroPageCount={null}
        activeClassName="pagination-active-page"
      />
    </div>
  );
}

export default Pagination;
