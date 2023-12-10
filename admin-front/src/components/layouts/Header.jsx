import React from "react";
import {useContext} from "react";
import {Link} from "react-router-dom";

const Header = () => {
    let token = localStorage.getItem("accessToken");
    return (
        <div className='header'>
            <Link to="/">Home</Link>
            <span> | </span>
            {token ? (
                <p>Logout</p>
            ): (
                <Link to="/login">Login</Link>
            )}
            {token && <p>Hello </p>}
        </div>
    )
}

export default Header;