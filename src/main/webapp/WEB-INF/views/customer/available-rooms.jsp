<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<html>
<head>
    <title>Available Rooms</title>
</head>
<body>

<h1>Available Rooms</h1>

<table border="1">

    <tr>

        <th>ID</th>
        <th>Room Number</th>
        <th>Type</th>
        <th>Price</th>
        <th>Action</th>

    </tr>

    <c:forEach items="${rooms}"
               var="room">

        <tr>

            <td>${room.roomId}</td>

            <td>${room.roomNumber}</td>

            <td>${room.roomType}</td>

            <td>${room.price}</td>

            <td>

                <a href="${pageContext.request.contextPath}/customer/book-room?roomId=${room.roomId}">
                    Book
                </a>

            </td>

        </tr>

    </c:forEach>

</table>

<br>

<a href="${pageContext.request.contextPath}/customer/dashboard">

    Back

</a>

</body>
</html>