import {BOARD, CATEGORY} from "../constants/ApiPath";
import axiosInstance from "../utils/axiosInstance";


export const boardList = async (params) => {
    return await axiosInstance.get(BOARD.LIST,
        { params: params}
    )
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardFindById = async (id) => {
    return await axiosInstance.get(BOARD.FIND(id))
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardRegister = async (params) => {
    return await axiosInstance.post(BOARD.REGISTER, params)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardUpdate = async (boardId, params) => {
    return await axiosInstance.put(BOARD.UPDATE(boardId), params)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardDelete = async (boardId) => {
    return await axiosInstance.delete(BOARD.DELETE(boardId))
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardEnums = async (params) => {
    return await axiosInstance.get(BOARD.ENUMS)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardCategoryRegister = async (params) => {
    return await axiosInstance.post(CATEGORY.REGISTER(params.boardId), params.categoryName)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardCategoryUpdate = async (params) => {
    return await axiosInstance.put(CATEGORY.UPDATE(params.boardId, params.categoryId), params.categoryName)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardCategoryDelete = async (params) => {
    return await axiosInstance.delete(CATEGORY.DELETE(params.boardId, params.categoryId))
        .then(response => response.data.data)
        .catch(error => console.log(error));
}

export const boardSelector = async () => {
    return await axiosInstance.get(BOARD.SELECTOR)
        .then(response => response.data.data)
        .catch(error => console.log(error));
}