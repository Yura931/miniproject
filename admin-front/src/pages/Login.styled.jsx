import {styled} from "styled-components";

// 색상 변수
const darkGreen = '#5c8d89';
const green = '#74b49b';
const lightGreen = '#a7d7c5';
const veryLightGreen = '#f4f9f4';
const darkGrey = '#828282';
const white = '#ffffff';
const lightGrey = '#f2f2f2';

// 폰트 변수
const fontFamily = '"Andada Pro", serif';
const lightFont = 600;
const heavyFont = 800;

// 로고 폰트 크기 계산
const logoFont = `calc(1.25 * ${31.42}px)`;

export const LoginWrapper = styled.div`
    display: flex;
    flex-direction: column;
    margin: auto;
    margin-top: 200px;
    padding-bottom: 30px;
`

export const FormContainer = styled.div`
    position: relative;
    width: 400px;
    height: 450px;
    background: transparent;
    border: 2px solid rgba(255, 255, 255, 0.5);
    border-radius: 20px;
    backdrop-filter: blur(8px);
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    background-color: ${white}
`

export const FormBox = styled.div`
    margin: 0 auto;
`

export const Title = styled.h2`
    font-size: 2em;
    text-align: center;
    color: ${darkGreen};
`

export const InputBox = styled.div`
    margin-top: 55px;
    display: flex;
    flex-direction: column;
    gap: 25px;
`

export const Inputs = styled.div`
    display: flex;
    align-items: center;
    margin: auto;
    width: 300px;
    height: 60px;
    background: ${lightGrey};
    border-radius: 6px;
`

export const Input = styled.input`
    height: 50px;
    width: 300px;
    background: transparent;
    border: none;
    outline: none;
    color: ${darkGreen};
    font-size: 19px;
    &:focus {
        color: ${darkGreen}
    }
`

export const Label = styled.label`
    margin: 0px 30px;
    color: ${darkGreen}
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