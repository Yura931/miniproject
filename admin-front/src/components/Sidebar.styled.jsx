import {styled} from "styled-components";
import { Link } from "react-router-dom";
import {boxShadow, darkGrey, green, lightGrey, white} from "../styles/Variables.styled";

export const SidebarContainer = styled.div`
    background-color: ${white};
    flex: 0 0 auto;
    transition: width 0.25s ease-in-out 0s;
    box-shadow: ${boxShadow};
`

export const LogoContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-start;
    padding: 1.64rem 2rem 1.64rem 2rem;
    
    .title {
        font-size: 31.42px;
        color: ${green};
        padding: 0 0 0 1rem;
    }
`

export const BurgerContainer = styled.div`
    content: "";
    background-color: ${white};
    position: absolute;
    right: calc(-35px / 2);
    top: calc(8rem / 1.15);
    width: 35px;
    height: 35px;
    border-radius: 2rem;
    z-index: 1;
    
    &::after {
        content: "";
        position: absolute;
        right: 35px;
        top: calc(35px / 2);
        z-index: 0;
        width: calc(6rem + 15rem);
        background-color: ${lightGrey};
        height: 2px;
        transition: 0.5s all;
    }
`

export const BurgerTrigger = styled.div`
    content: "";
    width: 35px;
    height: 35px;
    background-color: red;
    cursor: pointer;
    z-index: 6;
    opacity: 0;
    position: relative;
`

export const BurgerMenu = styled.div`
    content: "";
    background-color: ${green};
    position: relative;
    z-index: 1;
    width: 20px;
    height: 3px;
    border-radius: 2rem;
    bottom: calc(35px / 2);
    left: 0.45rem;
    transition: 0.5s all;
    
    &::after {
        content: "";
        background-color: ${white};
        position: absolute;
        width: 20px;
        height: 3px;
        border-radius: 2rem;
        top: 0.32rem;
        transform: translateY(-0.4rem) rotate(45deg);
        transition: 0.5s all;
    }
    
    &::before {
        content: "";
        background-color: ${white};
        position: absolute;
        width: 20px;
        height: 3px;
        border-radius: 2rem;
        top: -0.52rem;
        transform: translateY(0.4rem) rotate(-45deg);
        transition: 0.5s all;
    }
`

export const ProfileContainer = styled.div`
    display: flex;
    align-items: center;
    justify-content: flex-start;
    padding: 6rem 0 0 0;
`

export const ContentsContainer = styled.div`
    padding: 1.64rem 2rem 1.64rem 2rem;
`

export const ContentsListContainer = styled.ul`
      display: flex;
      align-items: flex-start;
      justify-content: space-between;
      flex-direction: column;
      list-style: none;
      padding: 0;
      margin: 0;
`

export const ContentsList = styled.li`
    display: flex;
    align-items: center;
    justify-content: flex-start;
    flex-direction: row;
    margin: 1rem 1rem;
    width: 100%;
    border-radius: 1rem;
    cursor: pointer;
`

export const ContentsLink = styled(Link)`
    display: flex;
    align-items: center;
    justify-content: flex-start;
    flex-direction: row;
    width: 100%;
    border-radius: 1rem;
    cursor: pointer;
    
    &:hover {
        background-color: ${lightGrey};
        transition: 0.25s;
    }
`

export const ContentsTitle = styled.span`
    color: ${darkGrey};
    font-size: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin: 0 0 0 2rem;
`
