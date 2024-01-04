import {useEffect, useState} from "react";
import {postList} from "../api/post";
import styled from "../components/layouts/Layout.module.css";
import {createColumnHelper} from "@tanstack/react-table";
import Table from "../components/Table";
import {boardSelector} from "../api/board";
const Post = () => {
    const [list, setList] = useState([]);
    const [pageable, setPageable] = useState({});
    const [selector, setSelector] = useState([]);
    const [params, setParams] = useState({
        pageNo: 0,
        pageSize: 10
    });

    const handleSetParams = (event) => {

        const { value } = event.target;
        const name = event.target.getAttribute("name");
        setParams({
            ...params,
            [name]: value
        });

    }

    useEffect(() => {
        const getPostList = async () => {
            const data = await postList(params);
            if (data?.items) {
                const items = data.items;
                setList(items.contents);
                setPageable({
                    number: items.number,
                    size: items.size,
                    totalElements: items.totalElements,
                    totalPages: items.totalPages
                });
            }
        }

        getPostList();
    }, []);

    useEffect(() => {
        const getBoardSelector = async () => {
            const data = await boardSelector();
            if (data?.items) {
                const items = data.items;
                console.log('items', items);
                setSelector(items.boardSelector);
            }
        }

        getBoardSelector();
    }, []);

    const columnHelper = createColumnHelper();
    const columns = [
        columnHelper.accessor("id", {
            header: "번호",
            cell: props => "",
        }),
        columnHelper.accessor("boardCategory", {
            header: "카테고리",
            cell: ({ getValue }) => getValue
        }),
        columnHelper.accessor("postTitle", {
            header: "제목",
            cell: ({ getValue }) => getValue
        }),
        columnHelper.accessor("createdBy", {
            header: "작성자",
            cell: ({ getValue }) => getValue
        }),
        columnHelper.accessor("createDate", {
            header: "작성일",
            cell: ({ getValue }) => getValue
        }),
    ];
    console.log('columns', columnHelper);

    return (
        <div className={styled.contentItem}>
            <div className={styled.shadowBox}>
                <div>

                </div>
                <div className={styled.box}>
                    <div className={styled.row}>
                        <div className={styled.item50}>
                            <select
                                className={styled.input}
                            >
                                <option>전체</option>
                                {
                                    selector.map((board, index) => {
                                       return (
                                           <option key={index} value={board.id}>{board.boardTitle}</option>
                                       )
                                    })
                                }

                            </select>
                        </div>
                    </div>
                </div>
                <div className={styled.mainBody}>
                    {
                        <Table
                                data={list}
                                columns={columns}
                                pageable={pageable}
                                handleSetParams={handleSetParams}
                        />
                    }

                </div>
            </div>
        </div>
    )
}

export default Post;