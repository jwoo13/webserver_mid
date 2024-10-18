
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="utf-8">
    <title>list 페이지</title>
</head>
<body>
<h1>Feed List</h1>


<h3>* 새 글 작성 *</h3>
<form action="/Feed/create" method="post">
    <div>
        <input type="text" name="content" placeholder="INSERT CONTENT">
    </div>
    <div>
        <button type="reset">RESET</button>
        <button type="submit">REGISTER</button>
    </div>
</form>

<ul>
    <c:forEach items="${FeedList}" var="feed">
        <li>
            <hr>
            <h2>글 내용:<a href="/Feed/read?feedId=${feed.feedId}">클릭</a></h2>

            <strong>- 수정시각:</strong> ${feed.modifiedDate}
            <br>
            <strong>- 내용:</strong> ${feed.content}
            <br>

            <form action="/Feed/remove" method="post">
                <input type="hidden" name="feedId" value="${feed.feedId}">
                <button type="submit">게시물 삭제</button>
            </form>

            <a href="/Feed/modify?feedId=${feed.feedId}" class="button">게시물 수정</a>

            <br>
        </li>
    </c:forEach>
</ul>

</body>
</html>



