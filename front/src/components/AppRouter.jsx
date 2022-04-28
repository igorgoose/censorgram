import React, {useContext} from 'react';
import {Route, Routes} from "react-router-dom";
import {privateRoutes, publicRoutes} from "../router";
import {AuthContext} from "../context/authContext";

const AppRouter = () => {
    const {authorized} = useContext(AuthContext);

    return (
        <Routes>
            {
                authorized ?
                    privateRoutes.map(r => <Route key={r.path} {...r}/>) :
                    publicRoutes.map(r => <Route key={r.path} {...r}/>)
            }
        </Routes>
    );
};

export default AppRouter;