<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<html>
<head>
    <title>All Bookings</title>
</head>
<body>

<h1>All Bookings</h1>

<table border="1">

    <tr>

        <th>Booking ID</th>
        <th>Customer</th>
        <th>Room</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Status</th>
        <th>Action</th>

    </tr>

    <c:forEach items="${bookings}"
               var="booking">

        <tr>

            <td>${booking.bookingId}</td>

            <td>${booking.customer.name}</td>

            <td>${booking.room.roomNumber}</td>

            <td>${booking.checkIn}</td>

            <td>${booking.checkOut}</td>

            <td>${booking.bookingStatus}</td>

            <td>

                <a href="${pageContext.request.contextPath}/receptionist/checkin?bookingId=${booking.bookingId}">

                    Check-In

                </a>

                |

                <a href="${pageContext.request.contextPath}/receptionist/checkout?bookingId=${booking.bookingId}">

                    Check-Out

                </a>

            </td>

        </tr>

    </c:forEach>

</table>

<br>

<a href="${pageContext.request.contextPath}/receptionist/dashboard">

Back

</a>

</body>
</html>