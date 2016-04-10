$(function() {
    $( ".column" ).sortable({
      connectWith: ".column",
      stop: function( event, ui ) {
        var viewArray = [];
        $(".column").each(function(index){
            console.log(index);
            $(this).children().each(function(index) {
                var x = $(this).find('.portlet-header').text();
                console.log(x);
            });
        })
      },
      handle: ".portlet-header",
      cancel: ".portlet-toggle",
      placeholder: "portlet-placeholder ui-corner-all"
    });

    $( ".portlet" )
      .addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
      .find( ".portlet-header" )
        .addClass( "ui-widget-header ui-corner-all" )
        .prepend( "<span class='ui-icon ui-icon-minusthick portlet-toggle'></span>");

    $( ".portlet-toggle" ).click(function() {
      var icon = $( this );
      icon.toggleClass( "ui-icon-minusthick ui-icon-plusthick" );
      icon.closest( ".portlet" ).find( ".portlet-content" ).toggle();
    });
  });