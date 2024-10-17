<!DOCTYPE html>
<html>
<head>
    <title>Marital Status Proportion</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<h1>Marital Status Proportion</h1>
<button onclick="history.back()">Go Back</button>
<li>
    <a href="index.html">Main Page</a>
</li>
<canvas id="myChart" style="width:100%;max-width:1400px"></canvas>
<script>
    const xValues = ["Married", "Single","Not Revealed"];
    const yValues = <%=request.getAttribute("maritalProportion")%>;
    const barColors = ["blue","red","green"];


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
                text: "Marital proportion"
            }
        }
    });
</script>
</body>
</html>
