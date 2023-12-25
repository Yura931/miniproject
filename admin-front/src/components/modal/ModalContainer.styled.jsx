import {styled} from "styled-components";
import {darkGrey, white} from "../../styles/Variables.styled";

export const ModalWrapper = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1032;
`

export const ModalBody = styled.div`
    position: absolute;
    border-radius: 10px;
    padding: 1.5rem;
    background-color: ${white};
    width: 65rem;
    background-color: rgb(255, 255, 255);
    border-radius: 10px;
    box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
    animation: ${(props) => props.$ismodalopen === 'true' ? 'modal-show 0.5s' : 'modal-hide 0.5s'};
    
    @keyframes modal-show {
        from {
            opacity: 0;
            margin-top: -50px;
        }
        to {
            opacity: 1;
            margin-top: 0;
        }
    }

    @keyframes modal-hide {
        from {
            opacity: 1;
            margin-top: 0;
        }
        to {
            opacity: 0;
            margin-top: -50px;
        }
    }
`

export const ModalCloseBtn = styled.button`
    color: ${darkGrey};
    background-color: transparent;
    position: absolute;
    right: 1.5rem;
    top: 1.5rem;
    :hover {
        cursor: pointer;
    }
`
export const ModalHeader = styled.div`
    display: flex;
`

export const ModalContents = styled.div`
    display: flex;
    //flex-direction: column;
    //align-items: center;
    height: 100%;
`

