import {useEffect, useState} from "react";
import {postList} from "../api/post";
import styled from "../components/layouts/Layout.module.css";
const Post = () => {
    const [params, setParams] = useState({
        pageNo: 0,
        pageSize: 10
    });
    useEffect(() => {
        const getPostList = async () => {
            const data = await postList(params);
            console.log('data', data);
        }

        getPostList();
    }, []);

    return (
        <div className={styled.contentItem}>
            <div className={styled.shadowBox}>
                <div>

                </div>
                <div className={styled.box}>

                </div>
            </div>
        </div>
    )
}

export default Post;