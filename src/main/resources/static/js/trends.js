$( document ).ready(function() {
    $.get( "/showPress", function( data ) {
      global = data;
      data.forEach(function(d) {
        $("#mentions").append('<div>'+ d.title +'</div>')
      });
    });
});
