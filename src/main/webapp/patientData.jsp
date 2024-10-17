<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Patient Data</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<button onclick="history.back()">Go Back</button>
<div class="main">
    <h2>Patient Data:</h2>
    <ul>
        <%
            ArrayList<String> patientData = (ArrayList<String>) request.getAttribute("patientData");
            if (patientData != null) {
                for (String data : patientData) {
        %>
        <li><%= data %></li>
        <%
            }
        } else {
        %>
        <p>No patient data found.</p>
        <%
            }
        %>
    </ul>
</div>
<li>
    <a href="index.html">Main Page</a>
</li>
</body>
</html>
