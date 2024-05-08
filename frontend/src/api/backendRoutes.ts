const BACKEND_PORT = 8081;
const BACKEND_PROTOCOL = "http"
const BACKEND_VERSION = "v1"
const BACKEND_BASE_ROUTE = BACKEND_PROTOCOL + "://localhost:" + BACKEND_PORT + "/api/" + BACKEND_VERSION + "/";

export const LOGIN_ENDPOINT = BACKEND_BASE_ROUTE + "user/login";