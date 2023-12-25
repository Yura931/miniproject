import {createPortal} from "react-dom";
import BoardModal from "./BoardModal";
import useModal from "../../hook/useModal";
import {ModalBody, ModalCloseBtn, ModalContents, ModalWrapper} from "./ModalContainer.styled";
import CategoryModal from "./CategoryModal";
import TestModal from "./TestModal";
import {FaRegWindowClose} from "react-icons/fa";

const MODAL_COMPONENTS = {
    board: BoardModal,
    category: CategoryModal,
    test: TestModal
};

const ModalContainer = () => {
    const { closeModal, modalState } = useModal();
    const renderModal = modalState.map(({ type, props }, index) => {
        const isModalOpen = !(type == null);
        if(!type) {
            return null;
        }
        const ModalComponent = MODAL_COMPONENTS[type];
        return (
            <ModalWrapper key={index} >
                <ModalBody
                    $ismodalopen={isModalOpen.toString()}
                >
                    <ModalContents>
                        <ModalCloseBtn onClick={closeModal} >
                            <FaRegWindowClose size={30} />
                        </ModalCloseBtn>
                        <ModalComponent {...props} />
                    </ModalContents>
                </ModalBody>
            </ModalWrapper>
        );
    });

    return createPortal(
        <>{renderModal}</>,
        document.getElementById('root')
    );
}

export default ModalContainer;
