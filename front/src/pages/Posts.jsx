import React, {useEffect, useState} from "react";
import {usePosts} from "../hooks/usePosts";
import useFetch from "../hooks/useFetch";
import PostService from "../api/PostService";
import {calculateTotalPages} from "../utils/pages";
import MainButton from "../components/ui/button/MainButton";
import PostModal from "../components/ui/modal/PostModal";
import PostForm from "../components/PostForm";
import PostFilter from "../components/PostFilter";
import Loader from "../components/ui/loader/Loader";
import PostList from "../components/PostList";
import Pagination from "../components/ui/pagination/Pagination";
import '../styles/posts.css';

function Posts() {
    const [posts, setPosts] = useState([]);
    const [filter, setFilter] = useState({
        sort: '',
        search: ''
    });
    const [modalVisible, setModalVisible] = useState(false);
    const [totalPages, setTotalPages] = useState(0)
    const [currentPage, setCurrentPage] = useState(1)
    const [pageSize, setPageSize] = useState(5)
    const filteredPosts = usePosts(posts, filter.sort, filter.search);
    const [fetchPosts, postsLoading, fetchPostsError] = useFetch(async () => {
        const response = await PostService.getPage(currentPage, pageSize);
        console.log(response);
        setPosts(response.data);
        setTotalPages(calculateTotalPages(parseInt(response.headers["x-total-count"]), pageSize));
    })
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
            {fetchPostsError ?
                <h1>Error fetching posts: {fetchPostsError}</h1>
                :
                postsLoading ?
                    <Loader/>
                    :
                    <PostList removePost={removePost} posts={filteredPosts} title={"Your Posts"}/>
            }
            <Pagination currentPage={currentPage} setCurrentPage={setCurrentPage} totalPages={totalPages}/>
        </div>
    );
}

export default Posts;