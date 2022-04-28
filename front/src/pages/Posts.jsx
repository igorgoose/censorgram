import React, {useContext, useEffect, useRef, useState} from "react";
import {usePosts} from "../hooks/usePosts";
import useFetch from "../hooks/useFetch";
import PostService from "../api/PostService";
import MainButton from "../components/ui/button/MainButton";
import PostModal from "../components/ui/modal/PostModal";
import PostForm from "../components/PostForm";
import PostFilter from "../components/PostFilter";
import Loader from "../components/ui/loader/Loader";
import PostList from "../components/PostList";
import '../styles/posts.css';
import useLoadObserver from "../hooks/useLoadObserver";
import {AuthContext} from "../context/authContext";

function Posts() {
    const [posts, setPosts] = useState([]);
    const [filter, setFilter] = useState({
        sort: '',
        search: ''
    });
    const {setAuthorized} = useContext(AuthContext);
    const [modalVisible, setModalVisible] = useState(false);
    const [totalPages, setTotalPages] = useState(0)
    const [currentPage, setCurrentPage] = useState(1)
    const [pageSize] = useState(20)
    const filteredPosts = usePosts(posts, filter.sort, filter.search);
    const [fetchPosts, postsLoading, fetchPostsError] = useFetch(async () => {
        try {
            console.log("fetching posts")
            const response = await PostService.getMyPostsPage(currentPage, pageSize);
            console.log("response", response);
            setPosts([...posts, ...response.data.content]);
            setTotalPages(response.data.totalPages);
        } catch (e) {
            console.log(e)
            console.log({...e})
            if (!e.response || e.response.status === 401) {
                setAuthorized(false);
                localStorage.removeItem("auth");
            }
        }
    })
    const loadIndicatorRef = useRef();

    useLoadObserver(postsLoading, loadIndicatorRef, currentPage >= totalPages, () => {
        setCurrentPage(currentPage + 1);
    });

    useEffect(() => fetchPosts(), [currentPage]);

    function addPost(post) {
        setPosts([...posts, {id: Date.now(), ...post}]);
        setModalVisible(false);
    }

    function removePost(post) {
        setPosts(posts.filter(p => p.id !== post.id))
    }

    return (
        <div className="Posts">
            <PostModal visible={modalVisible} setVisible={setModalVisible}>
                <PostForm create={addPost}/>
            </PostModal>
            <div style={{display: "flex", alignItems: "baseline"}}>
                <MainButton onClick={() => setModalVisible(true)}>
                    Create Post
                </MainButton>
                <PostFilter filter={filter} setFilter={setFilter}/>
            </div>
            <PostList loadIndicatorRef={loadIndicatorRef} removePost={removePost} posts={filteredPosts}
                      title={"Your Posts"}/>
            <div>
                {fetchPostsError ?
                    <h3>Error fetching posts: {fetchPostsError.message}</h3>
                    :
                    postsLoading && <Loader/>
                }
            </div>
        </div>
    );
}

export default Posts;