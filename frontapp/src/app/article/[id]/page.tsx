'use client'
import { useParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function ArticleDetail() {
    const params = useParams();
    const router = useRouter();

    const [article, setArticle] = useState([]);

    useEffect(() => {
        fetchArticle();
    }, []);

    const fetchArticle = async () => {
        const response = await fetch('http://localhost:8090/api/v1/articles/' + params.id)
            .then(res => res.json())
            .then(data => setArticle(data.data.article));
        console.log(article);
        console.log(response);
    }

    const updateRoute = () => {
        router.push(`/article/${params.id}/update`);
    }

    const deleteArticle = async () => {
        const response = await fetch('http://localhost:8090/api/v1/articles/' + params.id, {
            method: "DELETE"
        });
        if (response.ok) {
            console.log("성공");
            router.push("/article");
            // 리다이렉트 필요!!!!
        } else {
            console.log("실패");
        }
        console.log(response);
    }
    return (
        <div>
            <ul>
                <li>{article.id} 번 게시글 입니다!!</li>
                <li>제목: {article.subject}</li>
                <li>내용: {article.content}</li>
                <li>작성일: {article.createdDate}</li>
                <li>수정일: {article.modifiedDate}</li>
            </ul>

            <button onClick={updateRoute}>수정 페이지</button>
            <button onClick={deleteArticle}>삭제</button>
        </div>
    );
}