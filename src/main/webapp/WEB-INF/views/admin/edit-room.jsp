<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Edit Room</title>
</head>
<body>

<h1>Edit Room</h1>

<form action="${pageContext.request.contextPath}/admin/edit-room"
      method="post">

    <input type="hidden"
           name="roomId"
           value="${room.roomId}">

    Room Number:

    <input type="text"
           name="roomNumber"
           value="${room.roomNumber}">

    <br><br>

    Room Type:

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

    Price:

    <input type="number"
           step="0.01"
           name="price"
           value="${room.price}">

    <br><br>

    Status:

    <select name="status">

        <option value="AVAILABLE">
            AVAILABLE
        </option>

        <option value="OCCUPIED">
            OCCUPIED
        </option>

    </select>

    <br><br>

    <button type="submit">

        Update Room

    </button>

</form>

</body>
</html>