import React from 'react';
import MainButton from "../button/MainButton";
import btnCl from "../button/MainButton.module.css";
import cl from "./Pagination.module.css";
import usePages from "../../../hooks/usePages";

const Pagination = ({currentPage, setCurrentPage, totalPages}) => {
    const pageNumbers = usePages(totalPages);

    return (
        <div className={cl.pageNumbers}>
            {pageNumbers.map(pn =>
                <MainButton
                    key={pn}
                    classes={pn === currentPage ? [btnCl.selected] : []}
                    onClick={() => setCurrentPage(pn)}
                >
                    {pn}
                </MainButton>)}
        </div>
    );
};

export default Pagination;