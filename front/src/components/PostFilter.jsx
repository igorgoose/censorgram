import React from 'react';
import FormInput from "./ui/input/FormInput";
import Select from "./ui/select/Select";

const PostFilter = ({filter, setFilter}) => {
    const options = [
        {value: "title", text: "By Title"},
        {value: "body", text: "By body"}
    ]
    const defaultOption = {value: "", text: "Sort by", disabled: true}

    return (
        <div className="post-filter">
            <FormInput
                fullLength={false}
                value={filter.search}
                onChange={e => setFilter({...filter, search: e.target.value})}
                placeholder="Search"/>
            <span style={{marginRight: "5px"}}>Sort by</span>
            <Select
                options={options}
                defaultOption={defaultOption}
                value={filter.sort}
                onChange={sortType => setFilter({...filter, sort: sortType})}/>
        </div>
    );
};

export default PostFilter;