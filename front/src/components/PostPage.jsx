import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import useFetch from "../hooks/useFetch";
import PostService from "../api/PostService";
import Loader from "./ui/loader/Loader";
import PostItem from "./PostItem";

function PostPage() {
    const params = useParams();
    const [post, setPost] = useState(null);
    const [comments, setComments] = useState(null);
    const [fetchPost, postLoading, postsError] = useFetch(async (postId) => {
        const response = await PostService.getById(postId);
        setPost(response.data);
    })
    const [fetchComments, commentsLoading, commentsError] = useFetch(async (postId) => {
        const response = await PostService.getCommentsByPostId(postId);
        setComments(response.data);
    })
    useEffect(() => {
        fetchPost(params.id);
        fetchComments(params.id);
    }, [])

    return (
        <div>
            <h1>Post {params.id}</h1>
            <div>
                {(postLoading || !post) ? <Loader/> :
                    <PostItem post={post}/>}
            </div>
            <div>
                {commentsLoading || !comments ? <Loader/> :
                    <div>
                        <h4>Comments</h4>
                        {comments.map(c =>
                        <div key={c.id}>
                            <h5>{c.name}</h5>
                            <p>body</p>
                        </div>
                        )}
                    </div>
                }
            </div>
        </div>
    );
}

export default PostPage;