import React from 'react';
import classes from './FormInput.module.css';

const FormInput = ({fullLength = true, ...props}) => {
    const usedClasses = [classes.formInput]
    if (fullLength) {
        usedClasses.push(classes.formInputLong)
    }

    return (
        <input className={usedClasses.join(' ')} {...props}/>
    );
};

export default FormInput;