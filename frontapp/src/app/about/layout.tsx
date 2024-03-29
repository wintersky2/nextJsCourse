export default function AboutLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <div>
            <nav>
                소개 제목
            </nav>
            {children}
        </div>
    );
}