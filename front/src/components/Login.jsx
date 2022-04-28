import React, {useContext} from 'react';
import {Button} from "react-bootstrap";
import FormInput from "./ui/input/FormInput";
import BorderedForm from "./ui/form/BorderedForm";
import {AuthContext} from "../context/authContext";
import AuthService from "../api/AuthService";
import { useForm } from "react-hook-form";
import inputCl from "./ui/input/FormInput.module.css"

const Login = () => {
    const {setAuthorized} = useContext(AuthContext);
    const { register, handleSubmit, formState: { errors }} = useForm();

    const login = async data => {
        try {
            const response = await AuthService.login(data.username, data.password);
            setAuthorized(true);
            console.log(response)
            localStorage.setItem("auth", "true");
        } catch (e) {
            console.log(e);
            setAuthorized(false);
        }
    }

    return (
        <BorderedForm style={{width: "max(300px, 40%)"}} onSubmit={handleSubmit(login)}>
            <FormInput
                ref={React.createRef()}
                {...register("username", {required: true})}
                type="text"
                className={[inputCl.formInput, inputCl.formInputLong].join(' ')}
                placeholder="Username"/>
            <FormInput
                ref={React.createRef()}
                {...register("password", {required: true})}
                type="password"
                className={[inputCl.formInput, inputCl.formInputLong].join(' ')}
                placeholder="Password"/>
            <Button type="submit">Log in</Button>
        </BorderedForm>

    );
};

export default Login;