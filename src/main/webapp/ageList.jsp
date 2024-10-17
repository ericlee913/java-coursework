<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<button onclick="history.back()">Go Back</button>
<div class="main">
    <h2>Patient List by Age:</h2>
    <ul>
        <%
            List<String> ids =  (List<String>) request.getAttribute("ids");
            List<String> ages =  (List<String>) request.getAttribute("ages");
            int index = 0;
            for (String id : ids)
            {
                String href = "patientData.html?id=" + id;
        %>
        <li><a href="<%=href%>"><%=id + " - " + ages.get(index)%></a></li>
        <% index++;
            } %>
        <li>
            <a href="index.html">Main Page</a>
        </li>
    </ul>
</div>
</body>
</html>
