<%@ page contentType="text/html;charset=UTF-8"
         language="java"
         isELIgnored="false" %>

<html>
<head>
    <title>Revenue Report</title>
</head>
<body>

<h1>Revenue Report</h1>

<pre>
${report}
</pre>

<br>

<a href="${pageContext.request.contextPath}/admin/dashboard">
    Back
</a>

</body>
</html>