$(document).ready( function() {
    $('.dropdown-toggle').click(function() {
	$('.dropdown-menu').toggle();
    });
    $('.more_btn').click( function() {
       	var section = $(this).parent();
        $(section).children('.more_section').slideDown();
        // $(more_section).slideDown();
        $(this).hide();
	return false;
    });
    $('.less_btn').click( function() {
        var section = $(this).parent();
        $(section).slideUp( function() {
            var listing = $(this).parent();
            $(listing).children('.more_btn').show();
	});
	return false;
    });
});
