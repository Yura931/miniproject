import React from "react";
import {styled} from 'styled-components';
import {
    boxShadow,
    darkBrown, darkGreen,
    darkGrey,
    heavyFont,
    lightGreen,
    lightGrey,
    veryLightGreen,
    white
} from "../styles/Variables.styled";
export const Container = styled.div`
    position: relative;
    transition: 300ms ease all;
    backface-visibility: hidden;
    min-height: calc(100% - 3.35rem);
    padding: calc(2rem + 4rem) 2rem 0;
    margin-left: 18rem;
    
    @media screen and (max-width: 768px) {
        margin-left: 0;
    }
`

export const ContentWrapper = styled.div`
    width: 100%;
`

export const ContentHeader = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin-bottom: 1rem;
    width: 100%;
    color: ${darkGrey};
`

export const ContentHeaderTitle = styled.h2`
    font-size: 35px;
`

export const ContentBody = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: ${white};
    background-clip: border-box;
    flex-wrap: wrap;
    margin-bottom: 2rem;
    box-shadow: ${boxShadow};
    border-radius: 5px;
    width: 100%;
`

export const Contents = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: ${white};
    background-clip: border-box;
    flex-wrap: wrap;
    margin-bottom: 2rem;
`

export const Wrapper = styled.div`
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: ${white};
    background-clip: border-box;
    flex-wrap: wrap;
    margin-bottom: 2rem;
    box-shadow: ${boxShadow};
`

export const ButtonHeader = styled.div`
    position: relative;
    border-radius: 0.428rem 0.428rem 0 0;
    min-height: 10vh;
    border-bottom: 3px solid ${veryLightGreen};
    padding: 0.8rem 0.8rem;
    width: 100%;
`

export const AddButton = styled.button`
    position: absolute;
    width: 8rem;
    height: 2.4rem;
    border-radius: 4px;
    right: 2rem;
    top: 1rem;
    color: ${white};
    background-color: ${lightGreen};
    font-weight: ${heavyFont};
    cursor: pointer;
`
export const BoxHeader = styled.div`
    position: relative;
    display: flex;
    justify-content: space-between;
    min-height: 15vh;
    padding: 1.1rem 0.8rem;
    width: 100%;
`

export const SelectBox = styled.div`
    position: relative;
    width: 7rem;
    height: 35px;
    border-radius: 4px;
    display: flex;
    border: 1px solid ${darkBrown};
    
`
export const SelectTag = styled.select`
    width: inherit;
    height: inherit;
    background: transparent;
    border: 0 none;
    outline: 0 none;
    padding: 0 5px;
    position: relative;
    z-index: 3;
`
export const SelectOption = styled.option`
    background: ${veryLightGreen};
    color: ${darkGrey};
    padding: 3px 0;
    font-size: 16px;
`

export const SelectArrow = styled.span`
    position: absolute;
    top: 0;
    right: 0;
    z-index: 1;
    width: 35px;
    height: 33px;
    display: flex;
    justify-content: center;
    align-items: center;
`

export const SearchBox = styled.div`
    width: 30rem;
    height: 35px;
    position: relative;
    display: flex;
`

export const SearchSelectBox = styled.div`
    position: relative;
    width: 7rem;
    height: inherit;
    border: 1px solid ${darkBrown};
    border-top-left-radius: 4px;
    border-bottom-left-radius: 4px;
    border-right: none;
`

export const SearchSelectTag = styled.select`
    width: inherit;
    height: inherit;
    background: transparent;
    border: 0 none;
    outline: 0 none;
    padding: 0 5px;
    position: relative;
    z-index: 3;
`

export const SearchInput = styled.input`
    position: relative;
    width: 100%;
    min-width: 0;
    height: inherit;
    border-top-right-radius: 4px;
    border-bottom-right-radius: 4px;
    border: 1px solid ${darkBrown};
    padding: 0.8rem 0.4rem;
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
    height: ${({ height }) => height ? height : '60px'};
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
    color: ${darkGreen};
`

export const SaveButton = styled.button`
    width: 8rem;
    height: 2.4rem;
    border-radius: 4px;
    right: 0.8rem;
    color: ${white};
    background-color: ${lightGreen};
    font-weight: ${heavyFont};
    cursor: pointer;
`
