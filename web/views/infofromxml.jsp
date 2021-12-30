<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 28.11.2021
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LabTask6</title>
</head>
<body>

</div>
<%
    String result = (String) request.getAttribute("Result");
    out.println("<ui>");
    out.println("<li>" + result + "</li>");
    out.println("</ui>");

%>
</div>

</body>
</html>
