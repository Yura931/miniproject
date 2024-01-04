import React, {useEffect, useRef, useState} from 'react';
import {useLocation, useNavigate} from "react-router-dom";
import {
    LoginButton,
    LoginWrapper,
    SubmitContainer,
} from "./Login.styled";
import {login} from "../api/sign";
import axiosInstance from "../utils/axiosInstance";
import {FormBox, FormContainer, Input, InputBox, Inputs, Label, Title} from "../components/Common";


const Login = () => {
    const { pathname } = useLocation();
    const navigate = useNavigate();
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        let header = axiosInstance.defaults.headers["Authorization"];
    }, [])

    const idRef = useRef();
    const pwRef = useRef();

    const idChange = (event) => {
        setId((value) => event.target.value);
    }

    const pwChange = (event) => {
        setPassword((value) => event.target.value);
    }

    const onSignIn = async() => {
        await login(id, password)
            .then((data) => {
                navigate(pathname);
            });
    }

    return (
        <LoginWrapper>
            <FormBox>
                <FormContainer>
                    <Title>Sign Up</Title>
                    <InputBox>
                        <Inputs>
                            <Label>ID</Label>
                            <Input
                                autoComplete="off"
                                onChange={idChange}
                                ref={idRef}
                            />
                        </Inputs>
                        <Inputs>
                            <Label>PW</Label>
                            <Input
                                autoComplete="off"
                                onChange={pwChange}
                                ref={pwRef}
                            />
                        </Inputs>
                    </InputBox>
                    <SubmitContainer>
                        <LoginButton onClick={onSignIn}>Login</LoginButton>
                    </SubmitContainer>
                </FormContainer>
            </FormBox>
        </LoginWrapper>
    )
}

export default Login;