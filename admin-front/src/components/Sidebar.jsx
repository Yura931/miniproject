import {useState} from "react";
import { BsFileEarmarkPost } from "react-icons/bs";
import {BsPostcard} from "react-icons/bs";
import {MdDashboardCustomize, MdProductionQuantityLimits} from "react-icons/md";
import {
    ContentsContainer,
    ContentsLink,
    ContentsList,
    ContentsListContainer,
    ContentsTitle,
    LogoContainer,
    SidebarContainer
} from "./Sidebar.styled";
import Logout from "./Logout";
import {darkGrey} from "../styles/Variables.styled";

const Sidebar = () => {
    const [closeMenu, setCloseMenu] = useState(false);
    const handleCloseMenu = () => {
        setCloseMenu(!closeMenu);
    }
    return (
        <SidebarContainer className={closeMenu === false ? "sidebar" : "sidebar active"}>
            <LogoContainer className={closeMenu === false ? "logoContainer" : "logoContainer active"}>
                <h2 className="title">YuL.</h2>
            </LogoContainer>
{/*            <BurgerContainer className={closeMenu === false ? "burgerContainer" : "burgerContainer active"}>
                <BurgerTrigger className="burgerTrigger" onClick={() => {handleCloseMenu()}}></BurgerTrigger>
                <BurgerMenu className="burgerMenu"></BurgerMenu>
            </BurgerContainer>
            <ProfileContainer className={closeMenu === false ? "profileContainer" : "profileContainer active"}>
                <img src={Profile} alt="profile" className="profile"/>
                <div className="profileContents">
                    <p className="name">Hello, name</p>
                    <p>email@email.com</p>
                </div>
            </ProfileContainer>*/}
            <ContentsContainer className={closeMenu === false ? "contentsContainer" : "contentsContainer active"}>
                <ContentsListContainer>
                    <ContentsLink to="/">
                        <ContentsList className="active">
                            <MdDashboardCustomize size={30} style={{ color: `${darkGrey}`}} />
                            <ContentsTitle>Dashboard</ContentsTitle>
                        </ContentsList>
                    </ContentsLink>
                    <ContentsLink to="/board">
                        <ContentsList>
                            <BsPostcard size={30} style={{ color: `${darkGrey}`}} />
                            <ContentsTitle>게시판관리</ContentsTitle>
                        </ContentsList>
                    </ContentsLink>
                    <ContentsLink to="/post">
                        <ContentsList>
                            <BsFileEarmarkPost  size={30} style={{ color: `${darkGrey}`}} />
                            <ContentsTitle>게시물관리</ContentsTitle>
                        </ContentsList>
                    </ContentsLink>
                    {
                        [...Array(1).keys()].map((key) => {
                            return (
                                <ContentsLink to="/" key={key}>
                                    <ContentsList>
                                        <MdProductionQuantityLimits size={30} style={{ padding: '0 0.1rem 0 0.2rem', color: `${darkGrey}`}} />
                                        <ContentsTitle>상품관리</ContentsTitle>
                                    </ContentsList>
                                </ContentsLink>
                            )
                        })
                    }

                </ContentsListContainer>
            </ContentsContainer>
            <div className={closeMenu === false ? "contentsContainer" : "contentsContainer active"}>
                <div>
                    <Logout />
                </div>
            </div>
        </SidebarContainer>
    )
}

export default Sidebar;