<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feed Read</title>
</head>
<body>
<div>
    feed 번호:
    <input type="text" name="feedId" value="${dto.feedId}" readonly>
</div>
<div>
    내용:
    <input type="text" name="content" value="${dto.content}" readonly>
</div>
<div>
    날짜:
    <input type="datetime-local" name="modifiedDate" value="${dto.modifiedDate}" readonly>
</div>
<div>
    <a href="/Feed/list">돌아가기</a>
</div>

</body>
</html>