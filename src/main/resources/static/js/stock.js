var margin = {top: 20, right: 30, bottom: 30, left: 30},
    width = 320 - margin.left - margin.right,
    height = 200 - margin.top - margin.bottom;

var parseDate = d3.time.format("%I-%M-%S-%m-%d-%Y").parse,
    bisectDate = d3.bisector(function(d) { return d.date; }).left,
    formatValue = d3.format(",.2f"),
    formatCurrency = function(d) { return "$" + formatValue(d); };

var x = d3.time.scale()
    .range([0, width]);

var y = d3.scale.linear()
    .range([height, 0]);

var xAxis = d3.svg.axis()
    .scale(x)
    .orient("bottom");

var yAxis = d3.svg.axis()
    .scale(y)
    .orient("left");

var line = d3.svg.line()
    .x(function(d) { return x(d.date); })
    .y(function(d) { return y(d.close); });

var svg = d3.select("#stockChart").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom + 50)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

var global;
$( document ).ready(function() {
    $.get( "/stocks", function( data ) {
      global = data;

      var start = new Date();
      start.setHours(9,0,0,0);

      var end = new Date();
      end.setHours(17,0,0,0);

      //Comment this data variable out during the day to see it on live data
      data = [{
                         symbol: "DB",
                         time: {
                             dayOfYear: 50,
                             dayOfWeek: "FRIDAY",
                             month: "FEBRUARY",
                             dayOfMonth: 19,
                             year: 2016,
                             monthValue: 2,
                             hour: 18,
                             minute: 26,
                             second: 1,
                             nano: 103000000,
                             chronology: {
                                 id: "ISO",
                                 calendarType: "iso8601"
                             }
                           },
                         value: 17.14
                      },{
                         symbol: "DB",
                         time: {
                             dayOfYear: 50,
                             dayOfWeek: "FRIDAY",
                             month: "FEBRUARY",
                             dayOfMonth: 19,
                             year: 2016,
                             monthValue: 2,
                             hour: 9,
                             minute: 1,
                             second: 1,
                             nano: 103000000,
                             chronology: {
                                 id: "ISO",
                                 calendarType: "iso8601"
                             }
                           },
                         value: 14.24
                      },{
                         symbol: "DB",
                         time: {
                           dayOfYear: 50,
                           dayOfWeek: "FRIDAY",
                           month: "FEBRUARY",
                           dayOfMonth: 19,
                           year: 2016,
                           monthValue: 2,
                           hour: 11,
                           minute: 26,
                           second: 1,
                           nano: 103000000,
                           chronology: {
                               id: "ISO",
                               calendarType: "iso8601"
                           }
                         },
                         value: 9.24
                      },{
                         symbol: "DB",
                         time: {
                             dayOfYear: 50,
                             dayOfWeek: "FRIDAY",
                             month: "FEBRUARY",
                             dayOfMonth: 19,
                             year: 2016,
                             monthValue: 2,
                             hour: 12,
                             minute: 26,
                             second: 1,
                             nano: 103000000,
                             chronology: {
                                 id: "ISO",
                                 calendarType: "iso8601"
                             }
                         },
                         value: 12.24
                      }
         ];
      drawChart(data, start, end);
    });

});

$( "#oneDay" ).click(function() {
    var start = new Date();
    start.setHours(9,0,0,0);

    var end = new Date();
    end.setHours(17,0,0,0);

    data = [{
                             symbol: "DB",
                             time: {
                                 dayOfYear: 50,
                                 dayOfWeek: "FRIDAY",
                                 month: "FEBRUARY",
                                 dayOfMonth: 19,
                                 year: 2016,
                                 monthValue: 2,
                                 hour: 18,
                                 minute: 26,
                                 second: 1,
                                 nano: 103000000,
                                 chronology: {
                                     id: "ISO",
                                     calendarType: "iso8601"
                                 }
                               },
                             value: 17.14
                          },{
                             symbol: "DB",
                             time: {
                                 dayOfYear: 50,
                                 dayOfWeek: "FRIDAY",
                                 month: "FEBRUARY",
                                 dayOfMonth: 19,
                                 year: 2016,
                                 monthValue: 2,
                                 hour: 9,
                                 minute: 1,
                                 second: 1,
                                 nano: 103000000,
                                 chronology: {
                                     id: "ISO",
                                     calendarType: "iso8601"
                                 }
                               },
                             value: 14.24
                          },{
                             symbol: "DB",
                             time: {
                               dayOfYear: 50,
                               dayOfWeek: "FRIDAY",
                               month: "FEBRUARY",
                               dayOfMonth: 19,
                               year: 2016,
                               monthValue: 2,
                               hour: 11,
                               minute: 26,
                               second: 1,
                               nano: 103000000,
                               chronology: {
                                   id: "ISO",
                                   calendarType: "iso8601"
                               }
                             },
                             value: 9.24
                          },{
                             symbol: "DB",
                             time: {
                                 dayOfYear: 50,
                                 dayOfWeek: "FRIDAY",
                                 month: "FEBRUARY",
                                 dayOfMonth: 19,
                                 year: 2016,
                                 monthValue: 2,
                                 hour: 12,
                                 minute: 26,
                                 second: 1,
                                 nano: 103000000,
                                 chronology: {
                                     id: "ISO",
                                     calendarType: "iso8601"
                                 }
                             },
                             value: 12.24
                          }
             ];
    drawChart(data, start, end);
});

$( "#oneMonth" ).click(function() {
    var start = new Date();
    start.setHours(9,0,0,0);
    start.setMonth(start.getMonth()-1);

    var end = new Date();
    end.setHours(17,0,0,0);

    data = [{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 25,
                      year: 2016,
                      monthValue: 1,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 17.14
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 17,
                      year: 2016,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 14.24
               },{
                  symbol: "DB",
                  time: {
                    dayOfYear: 50,
                    dayOfWeek: "FRIDAY",
                    month: "FEBRUARY",
                    dayOfMonth: 18,
                    year: 2016,
                    monthValue: 2,
                    hour: 11,
                    minute: 26,
                    second: 1,
                    nano: 103000000,
                    chronology: {
                        id: "ISO",
                        calendarType: "iso8601"
                    }
                  },
                  value: 9.24
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 19,
                      year: 2016,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                  },
                  value: 12.24
               }
  ];
    drawChart(data, start, end);
});

$( "#oneYear" ).click(function() {
    var start = new Date();
    start.setHours(9,0,0,0);
    start.setFullYear(start.getFullYear()-1);

    var end = new Date();
    end.setHours(17,0,0,0);

    data = [{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 25,
                      year: 2015,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 17.14
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 18,
                      year: 2015,
                      monthValue: 6,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 14.24
               },{
                  symbol: "DB",
                  time: {
                    dayOfYear: 50,
                    dayOfWeek: "FRIDAY",
                    month: "FEBRUARY",
                    dayOfMonth: 18,
                    year: 2016,
                    monthValue: 2,
                    hour: 11,
                    minute: 26,
                    second: 1,
                    nano: 103000000,
                    chronology: {
                        id: "ISO",
                        calendarType: "iso8601"
                    }
                  },
                  value: 9.24
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 19,
                      year: 2016,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                  },
                  value: 12.24
               }
  ];
    drawChart(data, start, end);
});

$( "#fiveYears" ).click(function() {
    var start = new Date();
    start.setHours(9,0,0,0);
    start.setFullYear(start.getFullYear()-5);

    var end = new Date();
    end.setHours(17,0,0,0);

    data = [{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 25,
                      year: 2015,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 17.14
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 18,
                      year: 2011,
                      monthValue: 3,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                    },
                  value: 14.24
               },{
                  symbol: "DB",
                  time: {
                    dayOfYear: 50,
                    dayOfWeek: "FRIDAY",
                    month: "FEBRUARY",
                    dayOfMonth: 18,
                    year: 2016,
                    monthValue: 2,
                    hour: 11,
                    minute: 26,
                    second: 1,
                    nano: 103000000,
                    chronology: {
                        id: "ISO",
                        calendarType: "iso8601"
                    }
                  },
                  value: 9.24
               },{
                  symbol: "DB",
                  time: {
                      dayOfYear: 50,
                      dayOfWeek: "FRIDAY",
                      month: "FEBRUARY",
                      dayOfMonth: 19,
                      year: 2016,
                      monthValue: 2,
                      hour: 11,
                      minute: 26,
                      second: 1,
                      nano: 103000000,
                      chronology: {
                          id: "ISO",
                          calendarType: "iso8601"
                      }
                  },
                  value: 12.24
               }
  ];
    drawChart(data, start, end);
});

function drawChart(data, start, end) {
    svg.selectAll("*").remove();
        data.forEach(function(d) {
            d.date = parseDate(d.time.hour + "-" + d.time.minute + "-" + d.time.second + "-"+d.time.monthValue+"-"+d.time.dayOfMonth+"-"+d.time.year+"");
            d.close = +d.value;
        });

        data.sort(function(a, b) {
            return a.date - b.date;
        });

        var last = data[data.length-1];
        var lastClose = last.close;
        d3.select('#close').text(lastClose);

        x.domain([start, end]);
        y.domain([0,100]);

        svg.append("g")
          .attr("class", "x axis")
          .attr("transform", "translate(0," + height + ")")
          .call(xAxis)
          .selectAll("text")
          .attr("y", 0)
          .attr("x", 9)
          .attr("dy", ".35em")
          .attr("transform", "rotate(90)")
          .style("text-anchor", "start");

        svg.append("g")
          .attr("class", "y axis")
          .call(yAxis)
        .append("text")
          .attr("transform", "rotate(-90)")
          .attr("y", 6)
          .attr("dy", ".71em")
          .style("text-anchor", "end")
          .text("Price ($)");

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
            focus.attr("transform", "translate(" + x(d.date) + "," + y(d.close) + ")");
            focus.select("text").text(formatCurrency(d.close));
        };
}