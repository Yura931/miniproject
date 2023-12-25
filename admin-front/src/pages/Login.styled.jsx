import {styled} from "styled-components";
import {darkGreen, lightGreen, white} from "../styles/Variables.styled";

export const LoginWrapper = styled.div`
    display: flex;
    flex-direction: column;
    margin: auto;
    margin-top: 200px;
    padding-bottom: 30px;
`
export const SubmitContainer = styled.div`
    display: flex;
    gap: 30px;
    margin: 20px auto;
`
export const LoginButton = styled.button`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 300px;
    height: 40px;
    border-radius: 40px;
    background-color: ${lightGreen};
    border: none;
    outline: none;
    cursor: pointer;
    font-size: 1em;
    font-weight: 700;
    color: ${white}; 
    &:hover {
        background-color: ${darkGreen};
    }
    
`