<%@ page import="java.util.Hashtable" %>
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
  <h2>Patients:</h2>
  <ul>
    <%
      Hashtable<String, String> patients = (Hashtable<String, String>) request.getAttribute("patientList");
      for (String id : patients.keySet())
      {
        String href = "patientData.html?id=" + id;
    %>
    <li><a href="<%=href%>"><%=id + " - " + patients.get(id)%></a></li>
    <% } %>
  </ul>
</div>
</body>
</html>
