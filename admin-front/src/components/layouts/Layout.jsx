import styled from './Layout.module.css'
import Sidebar from "../Sidebar";
import {Navigate, Outlet, useOutlet} from "react-router-dom";
import axiosInstance from "../../utils/axiosInstance";
import React from "react";

const Layout = () => {
    let { props: { children: {props: {children: {props: {name}}}} } } = useOutlet();
    let accessToken = axiosInstance.defaults.headers["Authorization"];
    let auth = { 'token': accessToken };
    console.log('accessToken', accessToken);
    return (
        auth?.token ?
        <div className={styled.wrapper}>
            <Sidebar />
            <div className={styled.contentWrapper}>
                <header>

                </header>
                <main className={styled.contentMain}>
                    <div className={styled.contentContainer}>
                        <div className={styled.contentItem}>
                            <div>
                                <h5>{name}</h5>
                            </div>
                        </div>
                        <Outlet />
                    </div>
                </main>
                <footer>

                </footer>
            </div>
        </div>
            : <Navigate to='/login' />
    )
}
export default Layout;