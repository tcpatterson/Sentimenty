var margin = {top: 20, right: 30, bottom: 30, left: 30},
    width = 320 - margin.left - margin.right,
    height = 200 - margin.top - margin.bottom;

var parseDate = d3.time.format("%I-%M-%S-%m-%d-%Y").parse,
    bisectDate = d3.bisector(function(d) { return d.date; }).left,
    formatValue = d3.format(",.2f"),
    formatCurrency = function(d) { return "$" + formatValue(d); },
    historyDate = d3.time.format("%m/%d/%Y").parse;

var x = d3.time.scale()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .ticks(d3.time.hours, 1)
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("right");

var line = d3.svg.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.close); });

var svg = d3.select("#stockChart").append("svg")
    .attr("width", 340)
    .attr("height", height + margin.top + margin.bottom + 50)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

$( document ).ready(function() {
    $.get( "/stocks", function( data ) {
      global = data;

      drawChartToday(global[0]);
    });

});

$( "#oneDay" ).click(function() {
    xAxis = d3.svg.axis()
        .ticks(d3.time.hours, 1)
        .scale(x)
        .orient("bottom");
    drawChartToday(global[0]);
});

$( "#oneMonth" ).click(function() {
    xAxis = d3.svg.axis()
        .ticks(d3.time.days, 5)
        .scale(x)
        .orient("bottom");
    drawChartOld(global[1], 20);
});

$( "#oneYear" ).click(function() {
    xAxis = d3.svg.axis()
        .ticks(d3.time.months, 3)
        .scale(x)
        .orient("bottom");
    drawChartOld(global[1], 250);
});

$( "#fiveYears" ).click(function() {
    xAxis = d3.svg.axis()
        .ticks(d3.time.months, 6)
        .scale(x)
        .orient("bottom");
    drawChartOld(global[1], 1200);
});

function drawChartToday(data) {
    svg.selectAll("*").remove();
        data.forEach(function(d) {
            d.date = parseDate(d.time.hour + "-" + d.time.minute + "-" + d.time.second + "-4-23-16");
            d.close = +d.value;
        });

        data.sort(function(a, b) {
            return a.date - b.date;
        });

        var last = data[data.length-1];
        var secondToLastClose = data[data.length-2].close;
        var lastClose = last.close;
        d3.select('#close').text(lastClose);
        if(lastClose >= secondToLastClose ) {
            d3.select('#arrow').attr("class", "glyphicon glyphicon-arrow-up green")
        } else {
            d3.select('#arrow').attr("class", "glyphicon glyphicon-arrow-down red")
        }

        x.domain([data[0].date, data[data.length - 1].date]);
        y.domain([
            d3.min(data, function(d) { return d.close; }) -5,
            d3.max(data, function(d) { return d.close; }) +5
        ]);

        svg.append("g")
          .attr("class", "x axis")
          .attr("transform", "translate(0," + height + ")")
          .call(xAxis)
          .selectAll("text")
          .attr("y", 0)
          .attr("x", 9)
          .attr("dy", ".35em")
          .attr("transform", "rotate(45)")
          .style("text-anchor", "start");

        svg.append("g")
          .attr("class", "y axis")
          .attr("transform", "translate(" + width + " ,0)")
          .call(yAxis)
        .append("text")
          .attr("transform", "rotate(-90)")
          .attr("y", 40)
          .attr("x", -50)
          .attr("dy", ".71em")
          .style("text-anchor", "end")
          .text("Price (USD)");

        svg.append("path")
          .datum(data)
          .attr("class", "line")
          .attr("d", line);

        svg.append("text")
          .attr("x", (width / 2))
          .attr("y", 0 - (margin.top / 2))
          .classed('title', true)
          .attr("text-anchor", "middle")
          .style("font-size", "12px")
          .text("One Day Stock Performance");

        var focus = svg.append("g")
          .attr("class", "focus")
          .style("display", "none");

        focus.append("circle")
          .attr("r", 4.5);

        focus.append("text")
          .attr("x", 9)
          .attr("dy", ".35em");

        svg.append("rect")
          .attr("class", "overlay")
          .attr("width", width)
          .attr("height", height)
          .on("mouseover", function() { focus.style("display", null); })
          .on("mouseout", function() { focus.style("display", "none"); })
          .on("mousemove", mousemove);

        function mousemove() {
            var x0 = x.invert(d3.mouse(this)[0]),
                i = bisectDate(data, x0, 1),
                d0 = data[i - 1],
                d1 = data[i];
            if(!!!d1) {
              return true;
            }
            var d = x0 - d0.date > d1.date - x0 ? d1 : d0;
            var ds = new Date(d.date);

            var per = i/data.length;
            var xoff = per*-110;
            ds = ds.getMonth() + "/" + ds.getDate() + "/" + ds.getFullYear();
            focus.attr("transform", "translate(" + x(d.date) + "," + y(d.close) + ")");
            focus.select("text").text(ds + ": " + formatCurrency(d.close));
            focus.select("text").attr("x", xoff);
        };
}

function drawChartOld(data, num) {
    svg.selectAll("*").remove();
    data.forEach(function(d) {
        d.date = historyDate(d.dateStock);
        d.close = +d.closePrice;
    });

    data.sort(function(a, b) {
        return a.date - b.date;
    });

    var thisLength = data.length;

    if(num == 0) {
        num = thisLength;
    }

    var titleText = "";

    if (num == 20) {
        titleText = "One Month Stock Performance";
    } else if (num == 250) {
        titleText = "One Year Stock Performance";
    } else if (num == 1200) {
        titleText = "Five Year Stock Performance";
    }

    data = data.slice(thisLength-num, thisLength-1);

    x.domain([data[0].date, data[data.length - 1].date]);
    y.domain([
        d3.min(data, function(d) { return d.close; }),
        d3.max(data, function(d) { return d.close; })
    ]);

    xAxis.tickFormat(function(d) {
        var d = new Date(""+d+"");
        return (d.getMonth()+1) + "/" + d.getDate() + "/" + d.getFullYear().toString().substring(2,4);
    });

    svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
      .selectAll("text")
      .attr("y", 0)
      .attr("x", 9)
      .attr("dy", ".35em")
      .attr("transform", "rotate(45)")
      .style("text-anchor", "start");

    svg.append("g")
      .attr("class", "y axis")
      .attr("transform", "translate(" + width + " ,0)")
      .call(yAxis)
    .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 40)
      .attr("x", -50)
      .attr("dy", ".71em")
      .style("text-anchor", "end")
      .text("Price (USD)");

    svg.append("path")
      .datum(data)
      .attr("class", "line")
      .attr("d", line);

    var focus = svg.append("g")
      .attr("class", "focus")
      .style("display", "none");

    focus.append("circle")
      .attr("r", 4.5);

    focus.append("text")
      .attr("x", 0)
      .attr("dy", "-2.35em");

    svg.append("text")
      .attr("x", (width / 2))
      .attr("y", 0 - (margin.top / 2))
      .classed('title', true)
      .attr("text-anchor", "middle")
      .style("font-size", "12px")
      .text(titleText);

    svg.append("rect")
      .attr("class", "overlay")
      .attr("width", width)
      .attr("height", height)
      .on("mouseover", function() { focus.style("display", null); })
      .on("mouseout", function() { focus.style("display", "none"); })
      .on("mousemove", mousemove);

    function mousemove() {
        var x0 = x.invert(d3.mouse(this)[0]),
            i = bisectDate(data, x0, 1),
            d0 = data[i - 1],
            d1 = data[i];
        if(!!!d1) {
          return true;
        }
        var d = x0 - d0.date > d1.date - x0 ? d1 : d0;
        var ds = new Date(d.date);
        ds = ds.getMonth() + "/" + ds.getDate() + "/" + ds.getFullYear();

        var per = i/data.length;
        var xoff = per*-110;
        focus.attr("transform", "translate(" + x(d.date) + "," + y(d.close) + ")");
        focus.select("text").text(ds + ": " + formatCurrency(d.close));
        focus.select("text").attr("x", xoff);
    };
}