<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Payment Successful</title>
</head>
<body>

<h1>

    Payment Successful

</h1>

<p>

    Bill ID:
    ${bill.billId}

</p>

<p>

    Payment Status:
    ${bill.paymentStatus}

</p>

<br>

<a href="${pageContext.request.contextPath}/customer/bookings">

    Back To Bookings

</a>

</body>
</html>