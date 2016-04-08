$(document).ready(function(){
    $.get("/twitter", function (twitterList){
        for(var i=0; i < twitterList.length; i++){
            $("#twitterTemplate").tmpl(twitterList[i]).appendTo(".tweetedAbout");
        }
    })
})