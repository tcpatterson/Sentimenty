$( document ).ready(function() {
    $.get( "/showPress", function( data ) {
      global = data;
      data.forEach(function(d) {
        $("#mentions").append('<div class="mention"><a target="_blank" href="' + d.url + '">'+ d.title +'</a></div>')
      });
    });
});
