<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Feed Modify/Remove</title>
</head>
<body>

<form id="form1" action="/Feed/modify" method="post">
    <div>
        <input type="hidden" name="feedId" value="${dto.feedId}">
    </div>
    <div>
        <label for="content">Content:</label>
        <input type="text" id="content" name="content" value="${dto.content}">
    </div>
    <div>
        <label for="modifiedDate">Modified Date:</label>
        <input type="datetime-local" id="modifiedDate" name="modifiedDate" value="${dto.modifiedDate}" readonly>
    </div>

    <div>
        <button type="submit">Modify</button>
    </div>
</form>


</body>
</html>