'use client'

import { useParams } from "next/navigation";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

export default function Update() {

    const params = useParams();
    const router = useRouter();

    const [article, setArticle] = useState([]);
    const [subject, setSubject] = useState('');
    const [content, setContent] = useState('');


    useEffect(() => {
        fetchArticle();
        setSubject(article.subject);
        setContent(article.content);
    }, []);

    const fetchArticle = async () => {
        const response = await fetch('http://localhost:8090/api/v1/articles/' + params.id)
            .then(res => res.json())
            .then(data => setArticle(data.data.article));
    }

    const subjectForm = e => {
        setSubject(e.target.value);
        console.log(e.target.value);
    };

    const contentForm = e => {
        setContent(e.target.value);
        console.log(e.target.value);
    };

    const updateArticle = async () => {
        const response = await fetch('http://localhost:8090/api/v1/articles/' + params.id, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                subject,
                content
            })
        });

        if (response.ok) {
            console.log("성공");
            fetchArticle();
        } else {
            console.log("실패")
        }
        console.log(response)
    }
    return (
        <div>
            <ul>
                <li>제목: {article.subject}</li>
                <li>내용: {article.content}</li>
            </ul>
            <form>
                <input type="text" onChange={subjectForm} value={subject} />
                <input type="text" onChange={contentForm} value={content} />
                <button type="button" onClick={updateArticle}>수정</button>
            </form>
        </div>
    );
}