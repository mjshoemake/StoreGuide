
jQuery(document).ready(function() { 
	var offset = 250; 
	var duration = 300;

	jQuery(window).scroll(function() { 
		if (jQuery(this).scrollTop() > offset) {
			$(".top-btn").fadeIn(duration);
		} else {
			$(".top-btn").fadeOut(duration);
		}
	});
 
	jQuery(".top-btn").click(function(event) {	 
		event.preventDefault(); 
		$("html, body").animate({scrollTop: 0}, duration);
		return false;
	})
});
 

 