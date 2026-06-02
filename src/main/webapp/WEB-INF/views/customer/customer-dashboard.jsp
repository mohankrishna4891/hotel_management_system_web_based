<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<jsp:include
        page="/WEB-INF/views/common/header.jsp"/>

<html>
<head>
    <title>Customer Dashboard</title>
</head>
<body>

<h1>Customer Dashboard</h1>

<p>
Welcome,
${sessionScope.loggedInUser.name}
</p>

<p>
Role:
${sessionScope.loggedInUser.role}
</p>

<br><br>

<a href="${pageContext.request.contextPath}/customer/rooms">
    View Available Rooms
</a>

<br>

<a href="${pageContext.request.contextPath}/customer/bookings">

    My Bookings

</a>

<br>

<a href="${pageContext.request.contextPath}/logout">
    Logout
</a>

</body>
</html>

<jsp:include
        page="/WEB-INF/views/common/footer.jsp"/>