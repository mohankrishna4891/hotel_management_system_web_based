<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Bill</title>
</head>
<body>

<h1>Bill Details</h1>

<table border="1">

    <tr>
        <td>Bill ID</td>
        <td>${bill.billId}</td>
    </tr>

    <tr>
        <td>Booking ID</td>
        <td>${bill.booking.bookingId}</td>
    </tr>

    <tr>
        <td>Total Amount</td>
        <td>${bill.totalAmount}</td>
    </tr>

    <tr>
        <td>Tax</td>
        <td>${bill.tax}</td>
    </tr>

    <tr>
        <td>Discount</td>
        <td>${bill.discount}</td>
    </tr>

    <tr>
        <td>Payment Status</td>
        <td>${bill.paymentStatus}</td>
    </tr>

</table>

<br>

<a href="${pageContext.request.contextPath}/customer/payment?billId=${bill.billId}">
    Pay Bill
</a>

<br>

<a href="${pageContext.request.contextPath}/customer/bookings">

    Back To Bookings

</a>

</body>
</html>