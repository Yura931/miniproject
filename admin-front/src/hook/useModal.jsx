import {useContext} from "react";
import {ModalSetterContext, ModalStateContext} from "../context/ModalProvider";

const useModal = () => {
    const setModalState = useContext(ModalSetterContext);
    const modalState = useContext(ModalStateContext);

    const openModal = ({ type, props }) => {
        setModalState((state) => [...state, {type, props}] )
    };

    const closeModal = () => {
        setModalState((state) => state.slice(0, -1));
    };

    return { openModal, closeModal, modalState };
}

export default useModal;