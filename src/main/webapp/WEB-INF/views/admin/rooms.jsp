<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<html>
<head>
    <title>Rooms</title>
</head>
<body>

<h1>Room Management</h1>

<a href="${pageContext.request.contextPath}/admin/add-room">
    Add Room
</a>

<br><br>

<table border="1">

    <tr>
        <th>ID</th>
        <th>Room Number</th>
        <th>Type</th>
        <th>Price</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${rooms}"
               var="room">

        <tr>

            <td>${room.roomId}</td>

            <td>${room.roomNumber}</td>

            <td>${room.roomType}</td>

            <td>${room.price}</td>

            <td>${room.status}</td>

            <td>

                <td>

                    <a href="${pageContext.request.contextPath}/admin/edit-room?id=${room.roomId}">
                        Edit
                    </a>

                    |

                    <a href="${pageContext.request.contextPath}/admin/delete-room?id=${room.roomId}">
                        Delete
                    </a>

                </td>

            </td>

        </tr>

    </c:forEach>

</table>

<br>

<a href="${pageContext.request.contextPath}/admin/dashboard">
    Back
</a>

</body>
</html>