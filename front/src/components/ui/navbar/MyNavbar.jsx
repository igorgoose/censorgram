import React, {useContext} from 'react';
import cl from './MyNavbar.module.css'
import {AuthContext} from "../../../context/authContext";
import {Link} from "react-router-dom";
import {useCookies} from "react-cookie";
import {appConfig} from "../../../config/config";

const MyNavbar = ({links}) => {
    const {authorized, setAuthorized} = useContext(AuthContext);
    const [cookies, setCookieValue, removeCookie] = useCookies([appConfig.auth.cookieName]);

    const logout = () => {
        setAuthorized(false);
        console.log(cookies)
        console.log(document.cookie)
        removeCookie(appConfig.auth.cookieName);
        localStorage.removeItem("auth");
    }

    return (
        <div className={cl.navbar}>
            <h3>Censorgram</h3>
            {authorized &&
                <div className={cl.navbar__links}>
                    {
                        links.map(l =>
                            <div key={l.path}>
                                <Link className={cl.navbar__link} to={l.path}>{l.name}</Link>
                            </div>
                        )
                    }
                    <div className={cl.navbar__link} onClick={logout}>Log Out</div>
                </div>
            }
        </div>
    );
};

export default MyNavbar;