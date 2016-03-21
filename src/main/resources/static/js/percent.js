$( document ).ready(function() {
    $.get( "/percentSentiment", function( data ) {
      var today = data[0];
      var yesterday = data[1];
      $("#good").text(String(today).substring(0,7) + '%');
      $("#bad").text(String(yesterday).substring(0,7) + '%');
    });
});