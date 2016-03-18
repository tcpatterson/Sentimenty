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
    $.get( "/trends", function( data ) {
        data.forEach(function(d){
            //Get the whole list 
        })
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