import React from 'react';
import PostItem from "./PostItem";
import {CSSTransition, TransitionGroup} from "react-transition-group";

const PostList = ({removePost, posts, title}) => {
    if (posts.length === 0) {
        return (
            <div>
                <h1 style={{textAlign: "center"}}>No Posts Yet</h1>
            </div>
        )
    }

    return (
        <div>
            <h1 style={{textAlign: "center"}}>{title}</h1>
            <TransitionGroup>
                {posts.map(p =>
                    <CSSTransition key={p.id} timeout={500} classNames="post">
                        <PostItem removePost={removePost} post={p}/>
                    </CSSTransition>
                )}
            </TransitionGroup>
        </div>
    );
};

export default PostList;