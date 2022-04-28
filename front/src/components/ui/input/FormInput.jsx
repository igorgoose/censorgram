import React from 'react';

const FormInput = React.forwardRef((props, ref) => {
    return (
        <input ref={ref} {...props}/>
    );
});

export default FormInput;