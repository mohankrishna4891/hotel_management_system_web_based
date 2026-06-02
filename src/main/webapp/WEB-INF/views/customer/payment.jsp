<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Payment</title>
</head>
<body>

<h1>Choose Payment Method</h1>

<form action="${pageContext.request.contextPath}/customer/pay-bill"
      method="post">

    <input type="hidden"
           name="billId"
           value="${bill.billId}">

    <input type="radio"
           name="paymentMethod"
           value="UPI"
           required>

    UPI

    <br>

    <input type="radio"
           name="paymentMethod"
           value="CARD">

    CARD

    <br>

    <input type="radio"
           name="paymentMethod"
           value="CASH">

    CASH

    <br><br>

    <button type="submit">

        Pay Now

    </button>

</form>

</body>
</html>