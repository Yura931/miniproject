const COMMON = "/adm/v1/admin";

export const ACCOUNT = {
    LOGIN : "/adm/v1/auth/login",
    LOGOUT : `${COMMON}/logout`,
    REFRESH : `${COMMON}/refresh`
}

export const BOARD = {
    LIST : `${COMMON}/board`,
    FIND : (boardId) => `${COMMON}/board/${boardId}`,
    REGISTER : `${COMMON}/board/register`,
    UPDATE : (boardId) => `${COMMON}/board/${boardId}`,
    DELETE : (boardId) => `${COMMON}/board/${boardId}`,
    ENUMS : `${COMMON}/board/enums`
}
export const CATEGORY = {
    REGISTER : (boardId) => `${COMMON}/board/${boardId}/category/register`,
    UPDATE : (boardId, categoryId) => `${COMMON}/board/${boardId}/category/${categoryId}`,
    DELETE : (boardId, categoryId) => `${COMMON}/board/${boardId}/category/${categoryId}`
}

export const POST = {
    LIST : `${COMMON}/posts`
}