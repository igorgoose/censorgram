import React from 'react';
import MainButton from "./ui/button/MainButton";
import {useNavigate} from "react-router-dom";

const PostItem = ({removePost, post}) => {
    let navigate = useNavigate();
    console.log(post)

    return (
        <div className="post">
            <div className="post-content">
                <strong>{post.title}</strong>
                <div>{post.body}</div>
            </div>
            <MainButton
                style={{marginRight: "5px"}}
                onClick={() => navigate(`/posts/${post.id}`)}>
                Open
            </MainButton>
            <MainButton onClick={() => removePost(post)}>Remove</MainButton>
        </div>
    );
};

export default PostItem;