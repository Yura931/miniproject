import React, {useEffect, useRef, useState} from 'react';
import {boardDelete, boardEnums, boardList} from "../api/board";
import {useNavigate} from "react-router-dom";
import Table from "../components/Table";
import useModal from "../hook/useModal";
import styled from '../components/layouts/Layout.module.css';
import {AddButton} from "../components/Common";
import {createColumnHelper} from "@tanstack/react-table";

const Board = () => {
let navigate = useNavigate();
const [boardType, setBoardType] = useState([]);
const [boardEnabled, setBoardEnabled] = useState([]);
const [boardSortConditions, setBoardSortConditions] = useState([]);
const [boardSearchConditions, setBoardSearchConditions] = useState([]);

const [list, setList] = useState([]);
    const [pageable, setPageable] = useState({});
    const [loading, setLoading] = useState(true);
const [params, setParams] = useState({
    searchWord: '',
    boardSortCondition: 'CREATE_AT',
    sortDirection: 'DESC',
    boardSearchCondition: 'ALL',
    pageNo: 0,
    pageSize: 5
});

const paramInput = useRef();


    const {boardSortCondition, pageNo, pageSize} = params;
    const handleSetParams = (event) => {

        const { value } = event.target;
        const name = event.target.getAttribute("name");
        setParams({
            ...params,
            [name]: value
        });

    }
    const getBoardList = async() => {
        const data = await boardList(params);
        if (data?.items) {
            const items = data.items;
            setList(items.contents);
            setPageable({
                number: items.number,
                size: items.size,
                totalElements: items.totalElements,
                totalPages: items.totalPages
            });
            setBoardType(items.boardTypeList);
            setBoardEnabled(items.boardEnabledList);
            setBoardSortConditions(items.boardSortConditionList);
            setBoardSearchConditions(items.boardSearchConditionList);
        }
        setLoading(false);
    }

    const handleSearch = () => {
        getBoardList();
    }
    useEffect(() => {
        getBoardList();
    }, [navigate, pageNo, boardSortCondition]);

    const columnHelper = createColumnHelper();


    const columns = [
        { id: 'rowNumber', header: '번호', accessorFn: (row, index) => (pageable.totalElements - (pageable.size * pageable.number) - index) },
        { id: 'boardTitle', header: '게시판 제목', accessorFn: (row) => row.boardTitle },
        { id: 'boardType', header: '게시판타입', accessorFn: (row) => {
                let filter = boardType.filter((type) => type.key == row.boardType)
                if(filter?.[0]) return filter[0].value;
            }},
        { id: 'boardEnabled', header: '사용여부', accessorFn: (row) => row.boardEnabled },
        { id: 'boardVisible', header: '표시여부', accessorFn: (row) => row.boardVisible },
        { id: 'createDate', header: '생성일', accessorFn: (row) => row.createDate},
        { id: 'createdBy', header: '작성자', accessorFn: (row) => row.createdBy},
        columnHelper.display({id: 'actions', header: '비고', cell: props => <ActionButton original={props.cell.row.original} />})
    ]
    const handleModifiedBoard = (boardId) => {

        return openModal( { type: "board", props: { boardEnabled, boardType, boardId, closeModal, setParams }});
    }
    const handleDeleteBoard = async (boardId) => {

        await boardDelete(boardId)
            .then(data => window.location.reload());
    }
    const ActionButton = ({original}) => {
        const boardId = original.id;
        return (
            <>
                <button onClick={() => handleModifiedBoard(boardId)}>수정</button>
                <button onClick={() => handleDeleteBoard(boardId)}>삭제</button>
            </>
        )
    }
    const { openModal, closeModal } = useModal();
    const showModal = () => openModal({ type: "board", props: {boardEnabled, boardType, setList, closeModal, setParams}});


    return (
        <div className={styled.contentItem}>
            <div className={styled.shadowBox}>
                <div className={styled.mainHeader}>
                    <AddButton onClick={showModal}>
                        등록
                    </AddButton>
                </div>
                <div className={styled.box}>
                    <div className={styled.row} style={{ position: 'relative', marginBottom: '4rem', marginTop: '2rem'}}>
                        <div className={styled.item50} style={{ position: 'absolute', left: '0.6rem'}}>
                            <select
                                className={styled.input}
                                name={"boardSortCondition"}
                                onChange={handleSetParams}
                            >
                                {
                                    boardSortConditions.length > 0 ? boardSortConditions.map((condition, index) => {
                                        return (
                                            <option key={index} value={condition.key}>{condition.value}</option>
                                        )
                                    })
                                    : null
                                }
                            </select>
                        </div>
                        <div className={styled.item50} style={{ position: 'absolute', right: '2rem'}}>
                            <select
                                className={styled.input}
                                name={"boardSearchCondition"}
                            >
                                {
                                    boardSearchConditions.map((condition, index) => {
                                        return (
                                            <option key={index} value={condition.key}>{condition.value}</option>
                                        )
                                    })
                                }
                            </select>
                            <input
                                className={styled.input}
                                name={"searchWord"}
                                value={params.searchWord}
                                onChange={handleSetParams}
                            />
                            <button onClick={handleSearch}>검색</button>
                        </div>
                    </div>
                </div>
                <div className={styled.mainBody}>
                    {
                        loading ? (<p>Loading...</p>) : (
                             <Table
                                data={list}
                                columns={columns}
                                boardType={boardType}
                                pageable={pageable}
                                handleSetParams={handleSetParams}
                            />
                        )
                    }
                </div>
            </div>
        </div>
    );
}

export default Board;