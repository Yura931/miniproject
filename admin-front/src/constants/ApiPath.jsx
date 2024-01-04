const AUTH_SERVICE = "/auth-service/api/v1";
const BOARD_SERVICE = "/board-service/api/v1";

export const ACCOUNT = {
    LOGIN : `${AUTH_SERVICE}/signIn`,
    LOGOUT : `${AUTH_SERVICE}/logout`,
    REFRESH : `${AUTH_SERVICE}/re-issue`
}

export const BOARD = {
    SELECTOR : `${BOARD_SERVICE}/board/selector`,
    LIST : `${BOARD_SERVICE}/board`,
    FIND : (boardId) => `${BOARD_SERVICE}/board/${boardId}`,
    REGISTER : `${BOARD_SERVICE}/board/register`,
    UPDATE : (boardId) => `${BOARD_SERVICE}/board/${boardId}`,
    DELETE : (boardId) => `${BOARD_SERVICE}/board/${boardId}`,
    ENUMS : `${BOARD_SERVICE}/board/enums`
}
export const CATEGORY = {
    REGISTER : (boardId) => `${BOARD_SERVICE}/board/${boardId}/category/register`,
    UPDATE : (boardId, categoryId) => `${BOARD_SERVICE}/board/${boardId}/category/${categoryId}`,
    DELETE : (boardId, categoryId) => `${BOARD_SERVICE}/board/${boardId}/category/${categoryId}`
}

export const POST = {
    LIST : `${BOARD_SERVICE}/posts`
}