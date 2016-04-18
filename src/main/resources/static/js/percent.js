$( document ).ready(function() {
    $.get( "/percentSentiment", function( data ) {
      var array = data.split('\n');
      var today = array[1].split(',')[1] ;
      var yesterday = array[2].split(',')[1];
      //var offset = array[3].split(',')[1];
      //var mentionsCount = array[4].split(',')[1];
      console.log(mentionsCount);
      //$("#mentionsToday").text(String(mentionsCount));
      $("#good").text(String(today).substring(0,4) + '%');
      $("#bad").text(String(yesterday).substring(0,4) + '%');
      /*if (offset == 0){
        $("#offset").text('today');
      } else if (offset == 1){
        $("#offset").text('yesterday');
      } else{
        $("#offset").text(String(offset) + ' days ago');
      }*/
    });
});