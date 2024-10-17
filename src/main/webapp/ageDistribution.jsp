<!DOCTYPE html>
<html>
<head>
    <title>Age Distribution</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>Patient Age Distribution</h1>
    <button onclick="history.back()">Go Back</button>
    <li>
        <a href="index.html">Main Page</a>
    </li>
<canvas id="myChart" style="width:100%;max-width:1600px"></canvas>
<script>
    const xValues = ["Age: 0-20", "Age: 20-40", "Age: 40-60", "Age: 60-80", "Age: 80+"];
    const yValues = <%=request.getAttribute("ageDistribution")%>;
    const barColors = ["red", "green","blue","orange","brown"];


    new Chart("myChart", {
        type: "bar",
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
