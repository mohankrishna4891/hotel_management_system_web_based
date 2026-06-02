<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Dashboard</title>
</head>
<body>

<h2>Login Successful</h2>

<p>
    Welcome:
    ${sessionScope.loggedInUser.name}
</p>

<p>
    Role:
    ${sessionScope.loggedInUser.role}
</p>

<br><br>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>
</html>