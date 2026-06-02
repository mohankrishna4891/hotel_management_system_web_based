<%@ page contentType="text/html;charset=UTF-8"
         isELIgnored="false" %>

<jsp:include
        page="/WEB-INF/views/common/header.jsp"/>

<html>
<body>

<h1>Receptionist Dashboard</h1>

<p>
Welcome,
${sessionScope.loggedInUser.name}
</p>

<br>

<a href="${pageContext.request.contextPath}/receptionist/bookings">

View All Bookings

</a>

<br><br>

<a href="${pageContext.request.contextPath}/logout">

Logout

</a>

</body>
</html>

<jsp:include
        page="/WEB-INF/views/common/footer.jsp"/>