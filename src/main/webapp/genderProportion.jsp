<!DOCTYPE html>
<html>
<head>
    <title>Gender Proportion</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h1>Patient Gender Proportion</h1>
<button onclick="history.back()">Go Back</button>
<li>
    <a href="index.html">Main Page</a>
</li>
<canvas id="myChart" style="width:100%;max-width:1400px"></canvas>
<script>
    const xValues = ["Male", "Female"];
    const yValues = <%=request.getAttribute("genderProportion")%>;
    const barColors = ["blue","pink"];


    new Chart("myChart", {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues
            }]
        },
        options: {
            legend: {display: false},
            title: {
                display: false,
                text: "Patient Age Distribution"
            }
        }
    });
</script>
</body>
</html>
