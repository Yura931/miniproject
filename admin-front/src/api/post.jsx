import { POST } from "../constants/ApiPath";
import axiosInstance from "../utils/axiosInstance";

export const postList = async (params) => {
    return await axiosInstance.get(POST.LIST, {
        params: params
    })
        .then(response => response.data.data)
        .catch(error => console.log(error));
}