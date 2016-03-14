$('#modal-content').on('shown.bs.modal', function () {
    var term = $( "#searchTerm" ).val();
    $.post( "/search", { query: term })
      .done(function( data ) {
        $( "#searchTerm" ).val("");
        $(".modal-body").text("");
        $(".resultsTerm").text(term);
        term = "";
        data.forEach(function(d) {
            $(".modal-body").append(d.title);
            $(".modal-body").append("<br/>");
        });
      });
});

$('#openBtn').click(function () {
    $('#modal-content').modal({
        show: true
    });
});

formSubmit = function() {
    $('#modal-content').modal({
        show: true
    });
}

$('#modal-content').on('hidden.bs.modal', function () {
    $(".modal-body").text("");
})