import React, {useRef, useState} from 'react';
import {useNavigate} from "react-router-dom";
import {
    FormBox,
    FormContainer,
    Input,
    InputBox,
    Inputs,
    Label,
    LoginButton,
    LoginWrapper,
    SubmitContainer,
    Title
} from "./Login.styled";
import {login} from "../api/sign";


const Login = () => {
    let navigate = useNavigate();
    const [id, setId] = useState("");
    const [password, setPassword] = useState("");

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
            .then((response) => {
                navigate('/');
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