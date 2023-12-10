import React, {useEffect} from 'react';
import axiosInstance from "../utils/axiosInstance";

const DashBoard = () => {
    useEffect( () => {
        axiosInstance.get('/api/v1/admin/listTest', {

        })
            .then(response => {
                console.log(response);
            })
            .catch(error => console.log(error))
        console.log('dashboard');
    }, []);

    return (
        <div>
            <h1>dashboard</h1>
        </div>
    )
}

export default DashBoard;