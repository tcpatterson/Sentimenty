$( document ).ready(function() {
//    $.get( "/showPress", function( data ) {
//      data.forEach(function(d) {
//        var jsonn = JSON.parse(d.sentiment);
//        d.sentiment = jsonn;
//        if(d.sentiment && d.sentiment.score){
//            d.sentiment.score = d.sentiment.score.substring(0,6);
//        } else {
//            d.sentiment = {
//                score: 1
//            };
//            d.sentiment.score = "0.00";
//        }
//        if(d.sentiment.score < 0 ) {
//            d.arrow = "bottom";
//            d.color = "red";
//        } else {
//            d.arrow = "top";
//            d.color = "green";
//        }
//        var date = new Date(d.time);
//        d.time = date;
//      });
//      $("#clientTemplate").tmpl(data).appendTo( "#mentions" );
  var NUM_OF_TRENDS_TO_DISPLAY = 4

  $.get("/trends", function ( trendMentionList ) {
    for (var i = 0; i < NUM_OF_TRENDS_TO_DISPLAY; i++){
        $("<button>"+trendMentionList[0][i]+"</button>").addClass("trendButton").addClass("trend"+i).appendTo(".trend")
    }

    var showList = trendMentionList[1];
    for (var i = 0; i < trendMentionList[1].length; i++ ){//List of List of Press
        for (var j = 0; j < trendMentionList[1][i].length; j++){//List of Press
            var apiArticle = trendMentionList[1][i][j]
            frontendArticle = apiArticle
            var parsedSentiment = JSON.parse(frontendArticle.sentiment)
            frontendArticle.sentiment = parsedSentiment
            if ( frontendArticle.sentiment && frontendArticle.sentiment.score ){
                var sentScore = parseInt(frontendArticle.sentiment.score)
                sentScore = 50*sentScore+50
                frontendArticle.sentiment.score = "" + sentScore
            } else {
                frontendArticle.sentiment = {
                    score: 1
                };
                frontendArticle.sentiment.score = "0.00"
            }
            if (frontendArticle.sentiment.score < 0) {
                frontendArticle.arrow = "bottom"
                frontendArticle.color = "red"
            } else {
                frontendArticle.arrow = "top"
                frontendArticle.color = "green"
            }
            var date = new Date(frontendArticle.time)
            frontendArticle.time = date
            showList[i][j] = frontendArticle
            $(showList[i][j]).attr("trendNo", ""+i)
        }
    }
    for ( var i = 0; i < showList.length; i++){
        $("#clientTemplate").tmpl(showList[i]).appendTo("#mentions")
    }
    $(".article0").removeClass("hide")//Start off showing trend 1

    $(".trend0").click(function(){
        $(".mention").addClass("hide");
        $(".article0").removeClass("hide")
    })
    $(".trend1").click(function(){
        $(".mention").addClass("hide");
        $(".article1").removeClass("hide")
    })
    $(".trend2").click(function(){
        $(".mention").addClass("hide");
        $(".article2").removeClass("hide")
    })
    $(".trend3").click(function(){
        $(".mention").addClass("hide");
        $(".article3").removeClass("hide")
    })
  })
});
