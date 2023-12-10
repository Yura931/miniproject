import axios from "axios";


let navigateFunction = null;

export const setNavigateFunction = (navigate) => {
    navigateFunction = navigate;
}

const getToken = () => {
    return localStorage.getItem("token");
}

const instance = axios.create({
    timeout: 1000,
})

instance.defaults.withCredentials = true;
instance.interceptors.request.use(
    (config) => {
        const token = getToken();
        config.headers['Content-Type'] = 'application/json';
        if(token !== null) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;

    },
    (error) => {
        return Promise.reject(error);
    }
);

instance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        const statusCode = error.response?.status;
        if(statusCode === 401) {
            error.response.statusText = 'Unauthorized';
            error.response.stats = 401;
        }

        if(statusCode === 410 || statusCode === 415) {
            localStorage.clear();
            navigateFunction('/login');
        }
        return Promise.reject(error);
    }
);

export default instance;
