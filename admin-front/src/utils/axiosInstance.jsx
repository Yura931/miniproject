import React from "react";
import axios from "axios";

let accessToken = localStorage.getItem('accessToken') ? localStorage.getItem('accessToken') : null;

const axiosInstance = axios.create({
    timeout: 5000
});

axiosInstance.defaults.withCredentials = true;
axiosInstance.interceptors.request.use(request => {
    request.headers['Content-Type'] = 'application/json';
    if(accessToken != null) {
        request.headers['Authorization'] = `Bearer ${accessToken}`;
    }

    return request;
},
    (error) => {
        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        const statusCode = error.response?.status;
        if(statusCode === 401) {
            error.response.statusText = 'Unauthorized';
            error.response.status = 401;
        }

        return Promise.reject(error);
    }
);

export default axiosInstance;

