import {styled} from "styled-components";
import {darkGrey, heavyFont, lightFont, lightGreen, lightGrey, veryLightGreen, white} from "../styles/Variables.styled";

export const PaginationContainer = styled.div`
    display: flex;
    justify-content: flex-end;
    color: ${darkGrey};
    padding: 10px 30px;
    font-size: 15px;
    font-weight: ${lightFont};
`
export const PaginationBox = styled.ul`
    display: flex;
`

export const PaginationList = styled.li`
    background-color: ${lightGrey};
    padding: 10px 10px;
    
    &:nth-child(1) {
        border-top-left-radius: 5rem;
        border-bottom-left-radius: 5rem;
    }
    
    &:nth-last-child(1) {
        border-top-right-radius: 5rem;
        border-bottom-right-radius: 5rem;
    }
    
    &:hover {
        background-color: ${lightGreen};
        color: ${white};
        cursor: pointer;
    }
`