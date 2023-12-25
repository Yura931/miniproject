import styled from './TestModal.module.css';
const TestModal = (props) => {

    return (
        <div className={styled.box}>
            <div className={styled.header}>
                <div className={styled.item100}>
                    <h1 style={{ textAlign: 'center' }}>게시판 관리 등록</h1>
                </div>
            </div>
            <div className={styled.body}>
                <form className={styled.form}>
                    <div className={styled.row}>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>게시판 사용여부</label>
                                <div>
                                    <input type={"radio"} />
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>게시판 표시여부</label>
                                <div>
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                    <input type={"radio"} />
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>게시판 분류</label>
                                <div>
                                    <select className={styled.input}>
                                        <option>선택</option>
                                        <option>선택</option>
                                        <option>선택</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div className={styled.item50}>
                            <div className={styled.column}>
                                <label>게시판 제목</label>
                                <input className={styled.input}/>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>게시판 설명</label>
                                <textarea className={styled.textarea}>

                                </textarea>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>카테고리</label>
                                <div>
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                    <input type={"radio"} />
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>파일첨부</label>
                                <div>
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                    <input type={"radio"} />
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>댓글 사용</label>
                                <div>
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                    <input type={"radio"} />
                                </div>
                            </div>
                        </div>
                        <div className={styled.item100}>
                            <div className={styled.column}>
                                <label>대댓글 사용</label>
                                <div>
                                    <label>사용</label>
                                    <input type={"radio"} />
                                    <label>사용 안함</label>
                                    <input type={"radio"} />
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default TestModal;