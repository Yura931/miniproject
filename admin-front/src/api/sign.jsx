import axiosInstance from "../utils/axiosInstance";
import {ACCOUNT} from "../constants/ApiPath";
import React from "react";


export const login = async (email, pw) => {
    await axiosInstance.post(ACCOUNT.LOGIN, {
        email: email,
        password: pw
    })
    .then(response => {
        console.log('response', response);
        const { token } = response.data.data.items;
        axiosInstance.defaults.headers["Authorization"] = `Bearer ${token}`;
        return response.data;
    })
}

export const logout = async () => {
    await axiosInstance.post(ACCOUNT.LOGOUT)
        .then((response) => {
            axiosInstance.defaults.headers["Authorization"] = undefined;
            return response.data;
        })
}

