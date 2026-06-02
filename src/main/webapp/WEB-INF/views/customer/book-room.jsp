<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Book Room</title>
</head>
<body>

<h1>Book Room</h1>

<form action="${pageContext.request.contextPath}/customer/book-room"
      method="post">

    <input type="hidden"
           name="roomId"
           value="${room.roomId}">

    <label>Check-In Date:</label>

    <input type="date"
           name="checkIn"
           required>

    <br><br>

    <label>Check-Out Date:</label>

    <input type="date"
           name="checkOut"
           required>

    <br><br>

    <button type="submit">

        Confirm Booking

    </button>

</form>

</body>
</html>