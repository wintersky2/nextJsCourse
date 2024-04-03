"use client"

import Link from "next/link";
import {useEffect, useState } from "react";

export default function Article() {

    const [articles, setArticles] = useState([]);
    const [subject, setSubject] = useState('');
    const [content, setContent] = useState('');
    
    useEffect(() => {
        fetchArticle();
    }, []);

    const fetchArticle = () => {
        fetch('http://localhost:8090/api/v1/articles')
            .then(res => res.json())
            .then(data => setArticles(data.data.articles));
    }

    const subjectForm = e => {
        setSubject(e.target.value);
        console.log(e.target.value);
    };

    const contentForm = e => {
        setContent(e.target.value);
        console.log(e.target.value);
    };

    const postArticle = async () => {
        const response = await fetch('http://localhost:8090/api/v1/articles', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                subject,
                content
            })
        });

        if (response.ok) {
            console.log("성공")
            fetchArticle();
        } else {
            console.log("실패")
        }
        console.log(response)
    }


    return (
        <div>
            <ul>
                {(articles).map((article) => <li><Link href={`article/${article.id}`}>{article.id} 번</Link> / 제목: {article.subject} / 내용: {article.content}</li>)}
            </ul>
            <form>
                <input type="text" onChange={subjectForm} />
                <input type="text" onChange={contentForm} />
                <button type="button" onClick={postArticle}>등록</button>
            </form>

        </div>
    );
}