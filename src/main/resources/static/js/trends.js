$( document ).ready(function() {
//    $.get( "/showPress", function( data ) {
//      data.forEach(function(d) {
//        var jsonn = JSON.parse(d.sentiment);
//        d.sentiment = jsonn;
//        if(d.sentiment.score){
//            d.sentiment.score = d.sentiment.score.substring(0,6);
//        } else {
//            d.sentiment.score = "0.00";
//        }
//        if(d.sentiment.score < 0 ) {
//            d.arrow = "bottom";
//            d.color = "red";
//        } else {
//            d.arrow = "top";
//            d.color = "green";
//        }
//      });
//      $("#clientTemplate").tmpl(data).appendTo( "#mentions" );
//    });
    var numOfTrendsToDisplay = 4;
    $.get( "/trends", function( data ) {
        for (var i = 0; i < numOfTrendsToDisplay; i++){
            $(".trend").append("<button>" + data[0][i] + "</button>")
        }
    })
});

$("#reuters").click(function() {
    $(".mention").addClass("hide");
    $(".Reuters").removeClass("hide");
});

$("#bloomberg").click(function() {
    $(".mention").addClass("hide");
    $(".Bloomberg").removeClass("hide");
});