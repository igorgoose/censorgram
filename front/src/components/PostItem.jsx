import React, {useState} from 'react';
import MainButton from "./ui/button/MainButton";
import {useNavigate} from "react-router-dom";

const PostItem = ({removePost, post}) => {
    const navigate = useNavigate();

    return (
        <div className="post" style={{backgroundColor: `#${post.status.color}`}}>
            <div className="post-content">
                <strong>{post.user.username}</strong>
                <div>{post.text}</div>
            </div>
            <MainButton
                style={{marginLeft: "auto", marginRight: "5px"}}
                onClick={() => navigate(`/posts/${post.id}`)}>
                Open
            </MainButton>
            <MainButton onClick={() => removePost(post)}>Remove</MainButton>
        </div>
    );
};

export default PostItem;