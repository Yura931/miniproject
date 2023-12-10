import Icon from '../images/Icon.svg';
import DashBoard from '../images/dashboard.svg';
import Profile from '../images/profile.png'
import {useState} from "react";
import {useLocation} from "react-router-dom";

const Sidebar = () => {
    const location = useLocation();

    const [closeMenu, setCloseMenu] = useState(false);

    const handleCloseMenu = () => {
        setCloseMenu(!closeMenu);
    }
    return (
        <div className={closeMenu === false ? "sidebar" : "sidebar active"}>
            <div className={closeMenu === false ? "logoContainer" : "logoContainer active"}>
                <img src={Icon} alt="icon" className="logo"/>
                <h2 className="title">evergreen.</h2>
            </div>
            <div className={closeMenu === false ? "burgerContainer" : "burgerContainer active"}>
                <div className="burgerTrigger" onClick={() => {handleCloseMenu()}}></div>
                <div className="burgerMenu"></div>
            </div>
            <div className={closeMenu === false ? "profileContainer" : "profileContainer active"}>
                <img src={Profile} alt="profile" className="profile"/>
                <div className="profileContents">
                    <p className="name">Hello, name</p>
                    <p>email@email.com</p>
                </div>
            </div>
            <div className={closeMenu === false ? "contentsContainer" : "contentsContainer active"}>
                <ul>
                    <li className="active">
                        <img src={DashBoard} alt="dashboard"/>
                        <a href="/">Dashboard</a>
                    </li>
                    <li>
                        <img src={DashBoard} alt="dashboard"/>
                        <a href="/Board">Board</a>
                    </li>
                    <li>
                        <img src={DashBoard} alt="dashboard"/>
                        <a href="/">Settings</a>
                    </li>
                </ul>
            </div>
        </div>
    )
}

export default Sidebar;