'use client'
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ArticleDetail() {
    const params = useParams();

    const [article, setArticle] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8090/api/v1/articles/' + params.id)
            .then((res) => res.json())
            .then((data) => setArticle(data.data.article));
    }, []);

    return (
        <div>
            <ul>
                <li>{article.id} 번 게시글 입니다!!</li>
                <li>제목: {article.subject}</li>
                <li>내용: {article.content}</li>
                <li>작성일: {article.createdDate}</li>
                <li>수정일: {article.modifiedDate}</li>
            </ul>
        </div>
    );
}