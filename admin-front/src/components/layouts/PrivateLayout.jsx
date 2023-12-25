import Sidebar from "../Sidebar";
import React from "react";
import {Container, ContentBody, ContentHeader, ContentHeaderTitle, ContentWrapper} from "../Common";
import {Navigate, Outlet, useOutlet} from "react-router-dom";
import axiosInstance from "../../utils/axiosInstance";


const PrivateLayout = () => {
    let { props: { children: {props: {children: {props: {name}}}} } } = useOutlet();
    let accessToken = axiosInstance.defaults.headers["Authorization"];
    let auth = { 'token': accessToken };

    return (
        auth.token ?
            <>
                <Sidebar />
                <Container>
                    <ContentWrapper>
                        <ContentHeader>
                            <ContentHeaderTitle>{name}</ContentHeaderTitle>
                        </ContentHeader>
                        <ContentBody>
                            <Outlet />
                        </ContentBody>
                    </ContentWrapper>
                </Container>
            </>
            : <Navigate to='/login' />
    );
}

export default PrivateLayout;