<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<html>
<head>
    <title>My Bookings</title>
</head>
<body>

<h1>My Bookings</h1>

<table border="1">

    <tr>

        <th>Booking ID</th>
        <th>Room</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Status</th>
        <th>Bill</th>

    </tr>

    <c:forEach items="${bookings}"
               var="booking">

        <tr>

            <td>
                ${booking.bookingId}
            </td>

            <td>
                ${booking.room.roomNumber}
            </td>

            <td>
                ${booking.checkIn}
            </td>

            <td>
                ${booking.checkOut}
            </td>

            <td>
                ${booking.bookingStatus}
            </td>

            <td>

                <a href="${pageContext.request.contextPath}/customer/generate-bill?bookingId=${booking.bookingId}">

                    Generate Bill

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