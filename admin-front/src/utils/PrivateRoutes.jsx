import React from "react";
import {Navigate, Outlet} from "react-router-dom";
import Sidebar from "../components/Sidebar";
import PrivateLayout from "../components/layouts/PrivateLayout";

const PrivateRoutes = ({ children, ...rest }) => {
    let auth = { 'token': true };

    return (
        auth.token ?
            <div>
                <PrivateLayout>
                    <Outlet />
                </PrivateLayout>
            </div>
            : <Navigate to='/login' />
    );
}

export default PrivateRoutes;
