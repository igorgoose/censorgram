import About from "../pages/About";
import Posts from "../pages/Posts";
import PostPage from "../components/PostPage";
import {Navigate} from "react-router-dom";
import React from "react";
import Login from "../components/Login";

export const privateRoutes = [
    {path: '/about', element: <About/>, exact: true},
    {path: '/posts', element: <Posts/>, exact: true},
    {path: '/posts/:id', element: <PostPage/>, exact: true},
    {path: '*', element: <Navigate to="/posts" replace/>}
]

export const publicRoutes = [
    {path: '/login', element: <Login/>, exact: true},
    {path: '*', element: <Navigate to="/login" replace/>}
]