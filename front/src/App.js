import './styles/App.css';
import React from "react";
import './styles/main.css';
import {BrowserRouter} from "react-router-dom";
import About from "./pages/About";
import Posts from "./pages/Posts";
import MyNavbar from "./components/ui/navbar/MyNavbar";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppRouter from "./components/AppRouter";

function App() {
    return (
        <BrowserRouter>
            <MyNavbar
            links={[
                {path: "/about", name: "About"},
                {path: "/posts", name: "Posts"}
            ]}
            />
            <AppRouter/>
        </BrowserRouter>
    );
}

export default App;
