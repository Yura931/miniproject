import React, {createElement} from 'react'
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import DashBoard from "../pages/DashBoard";
import Board from "../pages/Board";
import Login from "../pages/Login";
import TokenRefresher from "../utils/TokenRefresher";
import ModalProvider from "../context/ModalProvider";
import ModalContainer from "./modal/ModalContainer";
import PrivateLayout from "./layouts/PrivateLayout";
import LayoutTest from "../pages/LayoutTest";
import Layout from "./layouts/Layout";
import Post from "../pages/Post";


const AppRouter = () => {

    const AppProvider = ({ contexts, children }) => {
        return contexts.reduce(
            (prev, context) => createElement(context, {
                children: prev
            }),
            children
    )};
    return (
        <Router>
            <AppProvider contexts={[ModalProvider]}>
                <TokenRefresher />
                <Routes>
                    <Route path="/" element={<Layout />}>
                        <Route path="/" element={<DashBoard name={'대시보드'} />} />
                        <Route path="/board" element={<Board name={'게시판관리'} />} />
                        <Route path="/post" element={<Post name={'게시물관리'} />} />
                    </Route>
                    <Route path="/" element={<Layout />}>
                        <Route path="/test" element={<LayoutTest name={'테스트'} />} />
                    </Route>
                    <Route element={<Login />} path='/login'></Route>
                </Routes>
                <ModalContainer />
            </AppProvider>
        </Router>
        )
}

export default AppRouter;