
var radius = Math.min(width, height) / 2;

var color = d3.scale.ordinal()
    .range(["#00ff00", "#ff0000"]);

var arcPie = d3.svg.arc()
    .outerRadius(radius - 10)
    .innerRadius(0);

var labelArc = d3.svg.arc()
    .outerRadius(radius - 40)
    .innerRadius(radius - 40);

var pie = d3.layout.pie()
    .sort(null)
    .value(function(d) { return d.percent; });

var pieChart = d3.select("#pie").append("svg")
    .attr("width", width)
    .attr("height", height)
  .append("g")
    .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")");

d3.csv("/percentSentiment", type, function(error, data) {
  if (error) throw error;

  var g = pieChart.selectAll(".arc")
      .data(pie(data))
    .enter().append("g")
      .attr("class", "arcPie");

  g.append("path")
      .attr("d", arcPie)
      .style("fill", function(d) { return color(d.data.label); });

  g.append("text")
      .attr("transform", function(d) { return "translate(" + labelArc.centroid(d) + ")"; })
      .attr("dy", ".35em")
      .text(function(d) { return d.data.label; });
});

function type(d) {
  d.percent = +d.percent;
  return d;
}
