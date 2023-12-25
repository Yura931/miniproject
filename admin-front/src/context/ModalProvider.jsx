import React, {createContext, useState} from "react";

export const ModalStateContext = createContext([]);
export const ModalSetterContext = createContext([]);

const ModalProvider = ({children}) => {
    const [state, setState] = useState([]);

    return (
        <ModalSetterContext.Provider value={setState}>
            <ModalStateContext.Provider value={state}>
                {children}
            </ModalStateContext.Provider>
        </ModalSetterContext.Provider>
    );
}

export default ModalProvider;