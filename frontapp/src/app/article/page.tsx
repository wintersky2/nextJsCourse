"use client"

import Link from "next/link";
import { useEffect, useState } from "react";

export default function Article() {

    const [articles, setArticles] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8090/api/v1/articles')
            .then(res => res.json())
            .then(data => setArticles(data.data.articles));
    }, []);

    return (
        <div>
            <ul>
                {(articles).map((article: any) => <li>{article.id} 번 / 제목: {article.subject} / 내용: {article.content}</li>)}
            </ul>
        </div>
    );
}