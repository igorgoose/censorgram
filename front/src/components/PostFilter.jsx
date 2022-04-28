import React from 'react';
import FormInput from "./ui/input/FormInput";
import Select from "./ui/select/Select";
import inputCl from "./ui/input/FormInput.module.css";

const PostFilter = ({filter, setFilter}) => {
    const options = [
        {value: "username", text: "By Username"},
        {value: "text", text: "By Text"}
    ]
    const defaultOption = {value: "", text: "Sort by", disabled: true}

    return (
        <div className="post-filter">
            <FormInput
                value={filter.search}
                onChange={e => setFilter({...filter, search: e.target.value})}
                className={inputCl.formInput}
                placeholder="Search"/>
            <span style={{marginRight: "5px"}}>Sort by</span>
            <Select
                options={options}
                defaultOption={defaultOption}
                value={filter.sort}
                className={inputCl.formInput}
                onChange={sortType => setFilter({...filter, sort: sortType})}/>
        </div>
    );
};

export default PostFilter;