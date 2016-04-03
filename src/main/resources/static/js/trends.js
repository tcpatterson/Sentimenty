$( document ).ready(function() {

  var NUM_OF_TRENDS_TO_DISPLAY = 4

  $.get("/trends", function ( trendMentionList ) {
    for (var i = 0; i < NUM_OF_TRENDS_TO_DISPLAY; i++){
        $("<button class='btn'>"+trendMentionList[0][i]+"</button>").addClass("trendButton").addClass("trend"+i).appendTo(".trend")
    }

    var showList = trendMentionList[1];
    for (var i = 0; i < trendMentionList[1].length; i++ ){//List of List of Press
        for (var j = 0; j < trendMentionList[1][i].length; j++){//List of Press
            var apiArticle = trendMentionList[1][i][j]
            frontendArticle = apiArticle
            var parsedSentiment = JSON.parse(frontendArticle.sentiment)
            frontendArticle.sentiment = parsedSentiment
            if ( frontendArticle.sentiment && frontendArticle.sentiment.score ){
                var sentScore = frontendArticle.sentiment.score
                sentScore = 50*sentScore+50
                frontendArticle.sentiment.score = String(sentScore).substring(0,5);
            } else {
                frontendArticle.sentiment = {
                    score: 1
                };
                frontendArticle.sentiment.score = "0.00"
            }
            if (frontendArticle.sentiment.score < 50) {
                frontendArticle.arrow = "bottom"
                frontendArticle.color = "red"
            } else {
                frontendArticle.arrow = "top"
                frontendArticle.color = "green"
            }
            var date = new Date(frontendArticle.time)
            frontendArticle.time = "" + (date.getMonth()+1) + "/" + date.getDate() + "/" + date.getFullYear()
            frontendArticle.title = frontendArticle.title.substring(0,60);
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
    var counter = 0;
    $(".onoffswitch-checkbox").change(function() {
        if(counter%2==0){
            $(".trend").hide(600);
            $(".mention").removeClass("hide");
        } else {
            $(".trend").show(600);
            $(".mention").addClass("hide");
            $(".article0").removeClass("hide")
        }
        counter++;
    });
  })
});
