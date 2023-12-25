import {useLocation, useNavigate} from "react-router-dom";
import {useEffect} from "react";
import axios from "axios";
import axiosInstance from "./axiosInstance";
import {ACCOUNT} from "../constants/ApiPath";

const TokenRefresher = () => {
    const navigate = useNavigate();
    const { pathname } = useLocation();
    useEffect(() => {

        const handlePageRefresh = async () => {
            axiosInstance.defaults.headers["Authorization"] = undefined;
            const newToken = await handleTokenRefresh();
            if(newToken !== undefined) {
                axiosInstance.defaults.headers["Authorization"] = `Bearer ${newToken}`;
                navigate(pathname);
            }
        }

        const refreshInstance = axios.create({
            headers: { "Content-Type": "application/json"},
            timeout: 5000,
        });

        const handleTokenExpiration = async (errorConfig) => {
            const newToken = await handleTokenRefresh();
            errorConfig.headers["Authorization"] = `Bearer ${newToken}`;
            return refreshInstance(errorConfig);
        }

        const handleTokenRefresh = async () => {
            return await axios.post(ACCOUNT.REFRESH)
                .then((response) => {
                    const { token } = response.data.data;
                    return token;
                })
                .catch((error) => {
                    const status = error.response?.status;
                    switch (status) {
                        case 484:
                            navigate('/login');
                    }
                });
        }

        const interceptor = axiosInstance.interceptors.response.use(
            async (response) => {
                return response;
            },
            async (error) => {
                const {config} = error;
                const status = error.response?.status;
                switch (status) {
                    case 481:
                    case 483:
                        return handleTokenExpiration(config);
                    case 484:
                        navigate('/login');
                    case 401:
                        navigate('/login');
                }
                return Promise.reject(error);
            }
        );

        handlePageRefresh().then();

        return () => {
            axiosInstance.interceptors.response.eject(interceptor);
        }
    }, []);
}

export default TokenRefresher;