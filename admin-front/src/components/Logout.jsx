import {useEffect} from "react";
import {logout} from "../api/sign";
import {useNavigate} from "react-router-dom";

const Logout = () => {
    const navigate = useNavigate();
    const onClick = async () => {
        await logout()
            .then((data) => navigate("/login"));
    }
    return (
        <div>
            <button onClick={onClick}>Logout</button>
        </div>
    )
}

export default Logout;