<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Add Room</title>
</head>
<body>

<h1>Add Room</h1>

<form action="${pageContext.request.contextPath}/admin/add-room"
      method="post">

    <label>Room Number:</label>
    <input type="text"
           name="roomNumber"
           required>

    <br><br>

    <label>Room Type:</label>

    <select name="roomType">

        <option value="STANDARD">
            STANDARD
        </option>

        <option value="DELUXE">
            DELUXE
        </option>

        <option value="SUITE">
            SUITE
        </option>

    </select>

    <br><br>

    <label>Price:</label>

    <input type="number"
           step="0.01"
           name="price"
           required>

    <br><br>

    <button type="submit">
        Add Room
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/admin/rooms">
    Back
</a>

</body>
</html>