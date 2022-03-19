import React from 'react';
import {Route, Routes} from "react-router-dom";
import {routes} from "../router";

const AppRouter = () => {
    return (
        <Routes>
            {routes.map(r =>
                <Route {...r}/>
            )}
        </Routes>
    );
};

export default AppRouter;