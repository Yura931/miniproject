import styled from './Layout.module.css'
import Sidebar from "../Sidebar";
import {AddButton} from "../Common";
import useModal from "../../hook/useModal";
import {
    ResponsiveTable,
    TableBody,
    TableBox,
    TableContainer,
    TableHead,
    TableTd,
    TableTh,
    TableTr
} from "../Table.styled";
import {flexRender} from "@tanstack/react-table";
import Pagination from "../Pagination";
import {Outlet, useOutlet} from "react-router-dom";

const Layout = () => {
    let { props: { children: {props: {children: {props: {name}}}} } } = useOutlet();
    return (
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
    )
}
export default Layout;