import './styles/App.css';
import React, {useEffect, useState} from "react";
import './styles/main.css';
import {BrowserRouter} from "react-router-dom";
import MyNavbar from "./components/ui/navbar/MyNavbar";
import 'bootstrap/dist/css/bootstrap.min.css';
import AppRouter from "./components/AppRouter";
import {AuthContext} from "./context/authContext";
import Loader from "./components/ui/loader/Loader";

function App() {
    const [authorized, setAuthorized] = useState(false);
    const [authLoading, setAuthLoading] = useState(true);

    useEffect(() => {
        if (localStorage.getItem('auth') === "true") {
            setAuthorized(true);
        }
        setAuthLoading(false);
    }, [])

    return (
        <AuthContext.Provider value={{authorized, setAuthorized, authLoading, setAuthLoading}}>
            <BrowserRouter>
                <MyNavbar
                    links={[
                        {path: "/posts", name: "Posts"},
                        {path: "/about", name: "About"}
                    ]}
                />
                {authLoading ? <Loader/> : <AppRouter/>}
            </BrowserRouter>
        </AuthContext.Provider>
    );
}

export default App;
