$( document ).ready(function() {
    var allArticles;
    $.get( "/showPress", function( data ) {
      data.forEach(function(d) {
        var jsonn = JSON.parse(d.sentiment);
        d.sentiment = jsonn;
        if(d.sentiment && d.sentiment.score){
            d.sentiment.score = d.sentiment.score.substring(0,6);
        } else {
            d.sentiment = {
                score: 1
            };
            d.sentiment.score = "0.00";
        }
        if(d.sentiment.score < 0 ) {
            d.arrow = "bottom";
            d.color = "red";
        } else {
            d.arrow = "top";
            d.color = "green";
        }
        var date = new Date(d.time);
        d.time = date;
      });
      $("#clientTemplate").tmpl(data).appendTo( "#mentions" );

      $.get("/trend", function ( trendMentionList ) {
                  trendMentionList[0]//This is the list of trend titles
                  trendMentionList[1]//This is the list of article mentions

      })
    });

});

$("#reuters").click(function() {
    $(".mention").addClass("hide");
    $(".Reuters").removeClass("hide");
});

$("#bloomberg").click(function() {
    $(".mention").addClass("hide");
    $(".Bloomberg").removeClass("hide");
});