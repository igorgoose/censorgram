import React, {useState} from 'react';
import FormInput from "./ui/input/FormInput";
import MainButton from "./ui/button/MainButton";

const PostForm = ({create}) => {
    const [post, setPost] = useState({
        title: '',
        body: ''
    });

    function addPost(event) {
        event.preventDefault();
        create(post);
        setPost({title: '', body: ''});
    }

    return (
        <form>
            <FormInput
                value={post.title}
                onChange={e => setPost({...post, title: e.target.value})}
                type="text"
                placeholder="Title"/>
            <FormInput
                value={post.body}
                onChange={e => setPost({...post, body: e.target.value})}
                type="text"
                placeholder="Body"/>
            <MainButton onClick={addPost}>Create post</MainButton>
        </form>
    );
};

export default PostForm;