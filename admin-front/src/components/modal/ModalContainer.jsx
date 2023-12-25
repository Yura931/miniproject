import {useContext} from "react";
import {ModalStateContext} from "../../context/ModalProvider";
import {createPortal} from "react-dom";
import BoardModal from "./BoardModal";
import useModal from "../../hook/useModal";
import {ModalBody, ModalContents, ModalWrapper} from "./ModalContainer.styled";


const MODAL_COMPONENTS = {
    board: BoardModal,
};

const ModalContainer = () => {
    const { closeModal, modalState } = useModal();
    const { type, props } = useContext(ModalStateContext);
    const isModalOpen = Boolean(modalState.type);

    if(!type) {
        return null;
    }

    const Modal = MODAL_COMPONENTS[type];
    return createPortal(
        <ModalWrapper>
            <ModalBody modalState={isModalOpen}>
                <ModalContents>
                    <Modal {...props} onClose={closeModal} />
                </ModalContents>
            </ModalBody>
        </ModalWrapper>,
        document.getElementById('root')
    );


/*    const { closeModal } = useModal();

    const renderModal = modalList.map(( { type, props }) => {
        const ModalComponent = MODAL_COMPONENTS[type];
        return <ModalComponent key={type} {...props} onClose={closeModal} />;
    });
    return createPortal(<>{renderModal}</>, document.getElementById("modal"));*/
}

export default ModalContainer;
