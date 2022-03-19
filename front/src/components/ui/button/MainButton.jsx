import React from 'react';
import cl from "./MainButton.module.css";

const MainButton = ({children, classes=[], ...props}) => {
    return (
        <button className={[cl.mainBtn, ...classes].join(" ")} {...props}>
            {children}
        </button>
    );
};

export default MainButton;