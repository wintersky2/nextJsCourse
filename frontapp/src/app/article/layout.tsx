export default function ArticleLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <div>
            <nav>
                게시판 제목
            </nav>
            {children}
        </div>
    );
}