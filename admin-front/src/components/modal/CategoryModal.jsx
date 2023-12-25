import {SaveButton, Title} from "../Common";
import React, {useState} from "react";
import styled from "./Modal.module.css";
import {boardCategoryDelete, boardCategoryRegister, boardCategoryUpdate} from "../../api/board";

const CategoryModal = ({categories, setCategories, closeModal, boardId}) => {
    const [category, setCategory] = useState("");
    const [curCategories, setCurCategories] = useState(categories);
    const [inputValues, setInputValues] = useState(
        curCategories.map((item) => (boardId ? item.categoryName : item))
    );

    const handleInputChange = (index, value) => {
        const newInputValues = [...inputValues];
        newInputValues[index] = value;
        setInputValues(newInputValues);
    }

    const handleSetCategories = (itemCategories) => {
        setCurCategories(itemCategories);
        setCategories(itemCategories);
    }


    const handleCategoryDelete = async(index, categoryId) => {
        const params = {
            boardId: boardId,
            categoryId: categoryId,
        }

        const data = await boardCategoryDelete(params);
        if(data?.items) {
            handleSetCategories(data.items.categories);
        }
    }
    const handleCategoryUpdate = async(index, categoryId) => {
        const params = {
            boardId: boardId,
            categoryId: categoryId,
            categoryName: inputValues[index]
        }

        const data = await boardCategoryUpdate(params);
        if(data?.items) {
            handleSetCategories(data.items.categories);
        }
    }

    const handleCategorySave = () => {
        setCategories((el) => [...el, category]);
        setCurCategories((el) => [...el, category]);
        setCategory("");
    }

    const handleCategoryAdd = async() => {
        const params = {
            boardId: boardId,
            categoryName: category
        }

        const data = await boardCategoryRegister(params);
        if(data?.items) {
            handleSetCategories(data.items.categories);
            setCategory("");
        }
    }

    const handleCategoryChange = (event) => {
        setCategory(event.target.value);
    }

    return (
        <div className={styled.box}>
            <div className={styled.header}>
                <Title>카테고리</Title>
            </div>
            <div>
                <input
                    className={styled.input}
                    type={"text"}
                    value={category}
                    onChange={handleCategoryChange}
                />
                <SaveButton onClick={boardId ? handleCategoryAdd : handleCategorySave}>추가</SaveButton>
            </div>
            <div>
                <ul>
                    {
                        curCategories && curCategories.length > 0 ? (
                            curCategories.map((item, index) => (
                                <li key={index}>
                                    <input
                                        className={styled.input}
                                        defaultValue={boardId ? item.categoryName : item}
                                        onChange={event => handleInputChange(index, event.target.value)}
                                    />
                                    <button onClick={() => handleCategoryUpdate(index, item.id)}>수정</button>
                                    <button onClick={() => handleCategoryDelete(index, item.id)}>삭제</button>
                                </li>
                            ))
                        ) : (
                            <li>카테고리가 없습니다.</li>
                        )
                    }
                </ul>
            </div>
            <div className={styled.submit}>
                <button type={"button"} onClick={closeModal}>닫기</button>
            </div>
        </div>
    )
}

export default CategoryModal;