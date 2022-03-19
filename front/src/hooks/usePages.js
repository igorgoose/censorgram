import {useMemo} from "react";

export default function usePages(totalPages) {
    return useMemo(() => {
        const pageNumbers = [];
        for (let i = 0; i < totalPages; i++) {
            pageNumbers.push(i + 1);
        }
        return pageNumbers;
    }, [totalPages])
}