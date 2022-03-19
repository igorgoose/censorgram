import React from 'react';
import classes from './Select.module.css'

const Select = ({options, defaultOption, onChange, ...props}) => {
    return (
        <select className={classes.select} onChange={e => onChange(e.target.value)} {...props}>
            <option key={defaultOption.value} value={defaultOption.value} disabled={defaultOption.disabled === true} defaultValue>
                {defaultOption.text}
            </option>
            {options.map(o =>
                <option key={o.value} value={o.value}>
                    {o.text}
                </option>
            )}
        </select>
    );
};

export default Select;