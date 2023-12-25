import {PaginationBox, PaginationContainer, PaginationList} from "./Pagination.styled";
import {useEffect, useState} from "react";
import {paginationRange} from "../utils/paginationRange";

const PaginationProvider = ({pageable, handleSetParams}) => {
    const [currentPage, setCurrentPage] = useState();
    const [totalPages, setTotalPages] = useState(Math.ceil(pageable.totalElements / pageable.size));
    const [range, setRange] = useState([]);

    useEffect(() => {
        setCurrentPage(pageable.number);
        setTotalPages(Math.ceil(pageable.totalElements / pageable.size));
    }, [pageable]);

    useEffect(() => {
        setRange(paginationRange(totalPages, currentPage, 10, 4));
    },[currentPage, totalPages])

    return (
        <PaginationContainer>
            <PaginationBox>
                <PaginationList
                    name={"pageNo"}
                    value={currentPage - 1}
                    onClick={handleSetParams}
                >&lt;</PaginationList>
                {
                    range.reduce((pre, cur, idx) => {
                        pre.push(
                            <PaginationList
                                name={"pageNo"}
                                value={
                                        (cur === ' ...') ? pre[pre.length-1].props.value + 1 :
                                            (cur === '... ') ? 0 : cur-1 }
                                onClick={handleSetParams}
                                style={
                                    (cur-1) === pageable.number ? {
                                        backgroundColor : '#a7d7c5',
                                        color : '#ffffff'
                                    } : null}
                            >
                                {cur}
                            </PaginationList>
                        );
                        return pre;
                    }, [])
                }

                <PaginationList
                    name={"pageNo"}
                    value={currentPage + 1}
                    onClick={handleSetParams}
                >&gt;</PaginationList>

            </PaginationBox>
        </PaginationContainer>
    )
}

export default PaginationProvider;