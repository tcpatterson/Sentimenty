$( document ).ready(function() {
    $.get( "/mentions", function( data ) {
      var today = data[0];
      $("#mentionsToday").text(today);

    });
});

//current sentiment