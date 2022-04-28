import React from 'react';
import cl from './SimpleForm.module.css'

const SimpleForm = ({children, ...props}) => {
    return (
        <form className={cl.simpleForm} {...props}>
            {children}
        </form>
    );
};

export default SimpleForm;