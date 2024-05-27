const BACKEND_PORT = 8081;
const BACKEND_PROTOCOL = "http"
const BACKEND_VERSION = "v1"
const BACKEND_BASE_ROUTE = BACKEND_PROTOCOL + "://backend:" + BACKEND_PORT + "/api/" + BACKEND_VERSION + "/";

export const LOGIN_ENDPOINT = BACKEND_BASE_ROUTE + "user/login";
export const REFRESH_ENDPOINT = BACKEND_BASE_ROUTE + "user/refresh";
export const SIGNIN_ENDPOINT = BACKEND_BASE_ROUTE + "user/signin";
export const CHURCH_ENDPOINT = BACKEND_BASE_ROUTE + "church";
export const ROLE_ENDPOINT = BACKEND_BASE_ROUTE + "role";
export const USER_ENDPOINT = BACKEND_BASE_ROUTE + "user";