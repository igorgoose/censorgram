import React from 'react';
import cl from "./SimpleForm.module.css";
import cls from "./BorderedForm.module.css";

const BorderedForm = ({children, colored = true, ...props}) => {
    const classes = [cl.simpleForm, cls.borderedForm]
    if (colored) {
        classes.push(cls.colored)
    }

    return (
        <form className={classes.join(' ')} {...props}>
            {children}
        </form>
    );
};

export default BorderedForm;