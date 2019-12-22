<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--
We made the page for the user`s editing (edit.jsp)
-->
<html>
<head>
    <title>User Management/Edit</title>
    </head>
<body>
<jsp:useBean id="user" class="com.annalohvinenko.usermanagement.User" scope="session"/>
<form method="post" action="/edit">
    <fmt:formatDate value='${user.dateOfBirth}' type='date' dateStyle='medium' var="dateStr"/>
    <input type="hidden" name="id" value="${user.id}">
    <table>
        <tr>
            <th>First name</th>
            <td><input type="text" name="firstName" value="${user.firstName}"></td>
        </tr>
        <tr>
            <th>Last name</th>
            <td><input type="text" name="lastName" value="${user.lastName}"></td>
        </tr>
        <tr>
            <th>Date of birth</th>
            <td><input type="text" name="dateOfBirth" value="${dateStr}"></td>
        </tr>
    </table>
    <input type="submit" name="ok" value="Ok">
    <input type="submit" name="cancel" value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}")
    </script>
</c:if>
</body>
</html>