const AUTH_SERVICE = "/auth-service/api/v1";
const ADMIN_SERVICE = "/admin-service/api/v1";

export const ACCOUNT = {
    LOGIN : `${AUTH_SERVICE}/signIn`,
    LOGOUT : `${AUTH_SERVICE}/logout`,
    REFRESH : `${AUTH_SERVICE}/refresh`
}

export const BOARD = {
    LIST : `${ADMIN_SERVICE}/board`,
    FIND : (boardId) => `${ADMIN_SERVICE}/board/${boardId}`,
    REGISTER : `${ADMIN_SERVICE}/board/register`,
    UPDATE : (boardId) => `${ADMIN_SERVICE}/board/${boardId}`,
    DELETE : (boardId) => `${ADMIN_SERVICE}/board/${boardId}`,
    ENUMS : `${ADMIN_SERVICE}/board/enums`
}
export const CATEGORY = {
    REGISTER : (boardId) => `${ADMIN_SERVICE}/board/${boardId}/category/register`,
    UPDATE : (boardId, categoryId) => `${ADMIN_SERVICE}/board/${boardId}/category/${categoryId}`,
    DELETE : (boardId, categoryId) => `${ADMIN_SERVICE}/board/${boardId}/category/${categoryId}`
}

export const POST = {
    LIST : `${ADMIN_SERVICE}/posts`
}