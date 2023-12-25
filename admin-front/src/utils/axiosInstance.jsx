import React from "react";
import axios from "axios";
import qs from "qs";

const axiosInstance = axios.create({
    timeout: 5000
});

axiosInstance.defaults.withCredentials = true;
axiosInstance.defaults.paramsSerializer = params => {
    return qs.stringify(params);
}
axiosInstance.interceptors.request.use(request => {
    request.headers['Content-Type'] = 'application/json';
    return request;
},
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;

