import React from 'react';
import {Nav} from "react-bootstrap";
import cl from './MyNavbar.module.css'

const MyNavbar = ({links}) => {
    return (
        <div className={cl.navbar}>
            <div className={cl.navbar__links}>
                {
                    links.map(l =>
                        <div key={l.path} className={cl.navbar__link}>
                            <Nav.Link href={l.path}>{l.name}</Nav.Link>
                        </div>
                    )
                }
            </div>
        </div>
    );
};

export default MyNavbar;