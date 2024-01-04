import {SelectOption, Title} from "../Common";
import React, {useEffect, useState} from "react";
import {SaveButton} from "../Table.styled";
import {SubmitContainer} from "../../pages/Login.styled";
import {boardFindById, boardRegister, boardUpdate} from "../../api/board";
import styled from "./Modal.module.css";
import useModal from "../../hook/useModal";

const BoardModal = (props) => {
    const [boardId, setBoardId] = useState(props.boardId ? props.boardId : "");
    const [boardEnabled, setBoardEnabled] = useState("");
    const [boardVisible, setBoardVisible] = useState("");
    const [boardType, setBoardType] = useState("");
    const [boardTitle, setBoardTitle] = useState("");
    const [boardDescription, setBoardDescription] = useState("");
    const [boardCategoryEnabled, setBoardCategoryEnabled] = useState("");
    const [boardFileEnabled, setBoardFileEnabled] = useState("");
    const [boardCommentEnabled, setBoardCommentEnabled] = useState("");
    const [boardReplyCommentEnabled, setBoardReplyCommentEnabled] = useState("");
    const [categories, setCategories] = useState([]);

    useEffect(() => {

        const handleBoardFind = async () => {
            const data = await boardFindById(props.boardId);
            const items = data?.items;
            setBoardInfo(items);
            setCategories(items.categories);
        }

        if (props.boardId !== undefined) {
            handleBoardFind();
        }

    }, []);

    const setBoardInfo = (items) => {
        setBoardEnabled(items.boardEnabled);
        setBoardVisible(items.boardVisible);
        setBoardType(items.boardType);
        setBoardTitle(items.boardTitle);
        setBoardDescription(items.boardDescription);
        setBoardCategoryEnabled(items.boardCategoryEnabled);
        setBoardFileEnabled(items.boardFileEnabled);
        setBoardCommentEnabled(items.boardCommentEnabled);
        setBoardReplyCommentEnabled(items.boardReplyCommentEnabled);
    }

    const handleSave = async () => {
        const params = {
            boardEnabled: boardEnabled,
            boardVisible: boardVisible,
            boardType: boardType,
            boardTitle: boardTitle,
            boardDescription: boardDescription,
            boardCategoryEnabled: boardCategoryEnabled,
            boardFileEnabled: boardFileEnabled,
            boardCommentEnabled: boardCommentEnabled,
            boardReplyCommentEnabled: boardReplyCommentEnabled,
            categories: categories
        }
        await boardRegister(params)
            .then(data => {
                props.setParams({
                    searchWord: '',
                    boardSortCondition: 'CREATE_AT',
                    sortDirection: 'DESC',
                    boardSearchCondition: 'ALL',
                    pageNo: 0,
                    pageSize: 5
                });
                props.closeModal();
                window.location.reload();
            });
    }

    const handleModified = async () => {
        const params = {
            boardEnabled: boardEnabled,
            boardVisible: boardVisible,
            boardType: boardType,
            boardTitle: boardTitle,
            boardDescription: boardDescription,
            boardCategoryEnabled: boardCategoryEnabled,
            boardFileEnabled: boardFileEnabled,
            boardCommentEnabled: boardCommentEnabled,
            boardReplyCommentEnabled: boardReplyCommentEnabled,
        }

        await boardUpdate(props.boardId, params)
            .then(data => {
                if(data?.items) {
                    const items = data.items;
                    setBoardInfo(items);
                }
            });
    }

    const BoardType = () => (
        <>
            {props.boardType.map((el, index) => (
                <SelectOption
                    value={el.key}
                    key={index}
                    selected={el.key === boardType}
                >
                    {el.value}
                </SelectOption>
            ))}
        </>
    )

    const { openModal, closeModal } = useModal();
    const categoryModal = () => openModal({ type: "category", props: {categories, setCategories, closeModal, boardId}});
    return (
        <div className={styled.box}>
            <div className={styled.header}>
                <Title>게시판</Title>
            </div>
            <div className={styled.body}>
                <form className={styled.form}>
                    <div className={styled.row}>
                        <div className={styled.item100}>
                            <label>게시판 사용 여부</label>
                            <div>
                                <input
                                    name={"boardEnabled"}
                                    value={"Y"}
                                    type={"radio"}
                                    checked={"Y" === boardEnabled}
                                    onChange={(event) => setBoardEnabled(event.target.value)}
                                />
                                <label>사용</label>
                            </div>
                            <div>
                                <input
                                    name={"boardEnabled"}
                                    value={"N"}
                                    type={"radio"}
                                    checked={"N" === boardEnabled}
                                    onChange={(event) => setBoardEnabled(event.target.value)}
                                />
                                <label>사용안함</label>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <label>게시판 표시 여부</label>
                            <div>
                                <input
                                    name={"boardVisible"}
                                    value={"Y"}
                                    type={"radio"}
                                    checked={"Y" === boardVisible}
                                    onChange={(event) => setBoardVisible(event.target.value)}
                                />
                                <label>사용</label>
                            </div>
                            <div>
                                <input
                                    name={"boardVisible"}
                                    value={"N"}
                                    type={"radio"}
                                    checked={"N" === boardVisible}
                                    onChange={(event) => setBoardVisible(event.target.value)}
                                />
                                <label>사용안함</label>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <label>게시판 분류</label>
                            <div>
                                <select className={styled.input}
                                        onChange={event=> setBoardType(event.target.value)}>
                                    <SelectOption>선택</SelectOption>
                                    <BoardType/>
                                </select>
                            </div>
                        </div>
                        <div className={styled.item50}>
                            <div className={styled.column}>
                                <label>게시판 제목</label>
                                <input value={boardTitle} className={styled.input} onChange={event => setBoardTitle(event.target.value)} />
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>게시판 설명</label>
                                <textarea value={boardDescription} className={styled.textarea} onChange={event => setBoardDescription(event.target.value)}>

                                </textarea>
                            </div>
                        </div>
                        <div className={styled.item50}>
                            <div className={styled.column}>
                                <label>카테고리</label>
                                <div>
                                    <input
                                        name={"boardCategoryEnabled"}
                                        value={"Y"}
                                        type={"radio"}
                                        checked={"Y" === boardCategoryEnabled}
                                        onChange={(event) => setBoardCategoryEnabled(event.target.value)}
                                    />
                                    <label>사용</label>
                                </div>
                                <div>
                                    <input
                                        name={"boardCategoryEnabled"}
                                        value={"N"}
                                        type={"radio"}
                                        checked={"N" === boardCategoryEnabled}
                                        onChange={(event) => setBoardCategoryEnabled(event.target.value)}
                                    />
                                    <label>사용안함</label>
                                </div>

                            </div>
                        </div>
                        <div className={styled.item50}>
                            <div className={styled.column}>
                                <label>카테고리 추가</label>
                                <div>
                                    <select className={styled.input}>
                                        <SelectOption>카테고리</SelectOption>
                                        {
                                            categories && categories.length > 0 ?
                                                categories.map((item, index) =>
                                                    <SelectOption key={index}>{boardId ? item.categoryName : item}</SelectOption>
                                                ) : null
                                        }
                                    </select>
                                    <button type={"button"} onClick={categoryModal}>편집</button>
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>파일첨부</label>
                                <div>
                                    <input
                                        name={"boardFileEnabled"}
                                        value={"Y"}
                                        type={"radio"}
                                        checked={"Y" === boardFileEnabled}
                                        onChange={(event) => setBoardFileEnabled(event.target.value)}
                                    />
                                    <label>사용</label>
                                </div>
                                <div>
                                    <input
                                        name={"boardFileEnabled"}
                                        value={"N"}
                                        type={"radio"}
                                        checked={"N" === boardFileEnabled}
                                        onChange={(event) => setBoardFileEnabled(event.target.value)}
                                    />
                                    <label>사용안함</label>
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>댓글 사용</label>
                                <div>
                                    <input
                                        name={"boardCommentEnabled"}
                                        value={"Y"}
                                        type={"radio"}
                                        checked={"Y" === boardCommentEnabled}
                                        onChange={(event) => setBoardCommentEnabled(event.target.value)}
                                    />
                                    <label>사용</label>
                                </div>
                                <div>
                                    <input
                                        name={"boardCommentEnabled"}
                                        value={"N"}
                                        type={"radio"}
                                        checked={"N" === boardCommentEnabled}
                                        onChange={(event) => setBoardCommentEnabled(event.target.value)}
                                    />
                                    <label>사용안함</label>
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>대댓글 사용</label>
                                <div>
                                    <input
                                        name={"boardReplyCommentEnabled"}
                                        value={"Y"}
                                        type={"radio"}
                                        checked={"Y" === boardReplyCommentEnabled}
                                        onChange={(event) => setBoardReplyCommentEnabled(event.target.value)}
                                    />
                                    <label>사용</label>
                                </div>
                                <div>
                                    <input
                                        name={"boardReplyCommentEnabled"}
                                        value={"N"}
                                        type={"radio"}
                                        checked={"N" === boardReplyCommentEnabled}
                                        onChange={(event) => setBoardReplyCommentEnabled(event.target.value)}
                                    />
                                    <label>사용안함</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <SubmitContainer>
                {
                    props.boardId ?
                        <SaveButton onClick={handleModified}>수정</SaveButton>
                    : <SaveButton onClick={handleSave}>저장</SaveButton>
                }
            </SubmitContainer>
        </div>
    )
};

export default BoardModal;