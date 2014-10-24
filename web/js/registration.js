$(function() {
  $('input.person').autocomplete({
    autoFocus: true,
    source: '/person/search'
    //change: function( event, ui ) {
    //  console.log($(event.currentTarget));
    //}
  });
});