<%@ page import="java.util.ArrayList" %>
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
  <h1>Search Result</h1>
  <%
    Hashtable<String,ArrayList<String>> data = (Hashtable<String, ArrayList<String>>) request.getAttribute("result");
    if (data.size() !=0)
    {
    %>
    <ul>
      <%
        for (String id : data.keySet())
        {
          String href = "patientData.html?id=" + id;
      %>
      <li><a href="<%=href%>"><%=id + " - " + data.get(id)%></a></li>
     <% }
    } else
    {%>
      <p>Nothing found</p>
  <%}%>
      <li>
        <a href="index.html">Main Page</a>
      </li>
  </ul>
</div>
</body>
</html>