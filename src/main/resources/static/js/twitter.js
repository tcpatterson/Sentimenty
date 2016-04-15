$(document).ready(function(){
    $.get("/twitter", function (twitterList){
        for(var i=0; i < twitterList.length; i++){
            var newDate = new Date(parseInt(twitterList[i]));
            console.log(newDate);
            twitterList[i].created = newDate;
            $("#twitterTemplate").tmpl(twitterList[i]).appendTo(".tweetedAbout");
        }
    })
})