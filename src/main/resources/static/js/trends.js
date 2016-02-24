$( document ).ready(function() {
    $.get( "/showPress", function( data ) {
      data.forEach(function(d) {
        var jsonn = JSON.parse(d.sentiment);
        var sent = jsonn.type;
        var score = jsonn.score;
        $("#mentions").append('<div class="mention ' + sent + ' ' + d.source + '"><a target="_blank" href="' + d.url + '">'+ d.title +'</a><br><p>' + score + '</p></div>')
      });
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