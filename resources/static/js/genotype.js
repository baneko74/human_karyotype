jQuery.noConflict();
(function($){
	//cookie note
	let lang = $("span:first").attr("id");
	$.cookieBar({
		style: "bottom",
		language: lang,
		infoLink: "/cookie",
		infoTarget: "_blank"
	});
	//refresh page
	setTimeout(() => {
		location.reload()
	}, 900000);
	//csrf token 
	let $token = $("meta[name='_csrf']").attr('content');
	//enabling tootips
	$("[data-toggle='tooltip']").tooltip();
	//fade in and out last navbar link (for lang) 
	$("li.dropdown").hover(function(){
		$(this).find("ul.dropdown-menu").stop(true, true).delay(100).fadeIn(120);
	}, function(){
			$(this).find("ul.dropdown-menu").stop(true, true).delay(100).fadeOut(120);
	});
	//reference links in square brackets [x]
	$("a[href^='#science']").on('click', () => {
		$("a#national").css("text-decoration", "none");
		return $("a#science").css("text-decoration", "underline");
	});
	$("a[href='#wiki']").on('click', () => {
		$("a#wikidna").css("text-decoration", "none");
		return $("a#wiki").css("text-decoration", "underline");
	});
	$("a[href='#wikidna']").on('click', () => {
		$("a#wiki").css("text-decoration", "none");
		return $("a#wikidna").css("text-decoration", "underline");
	});
	$("a[href='#technics']").on('click', () => {
		return $("a#technics").css("text-decoration", "underline");
	});
	
	$("a[href^='#national']").on('click', () => {
		$("a#science").css("text-decoration", "none");
		return $("a#national").css("text-decoration", "underline");
	});
	$("a[href^='#extraction']").on('click', () => {
		$("a#sd").css("text-decoration", "none");
		return $("a#extraction").css("text-decoration", "underline");
	});
	$("a[href^='#banding']").on('click', () => {
		return $("a#banding").css("text-decoration", "underline");
	});
	$("a[href^='#sd']").on('click', () => {
		$("a#extraction").css("text-decoration", "none");
		return $("a#sd").css("text-decoration", "underline");
	});
	$("a[href^='#fish']").on('click', () => {
		$("a#nick").css("text-decoration", "none");
		$("a#technics").css("text-decoration", "none");
		return $("a#fish").css("text-decoration", "underline");
	});
	$("a[href^='#nick']").on('click', () => {
		$("a#fish").css("text-decoration", "none");
		$("a#technics").css("text-decoration", "none");
		return $("a#nick").css("text-decoration", "underline");
	});
	$("a[href^='#pcr']").on('click', () => {
		return $("a#pcr").css("text-decoration", "underline");
	});
	$("a[href^='#karyotype']").on('click', () => {
		$("a#chromosome18").css("text-decoration", "none");
		$("a#basic").css("text-decoration", "none");
		return $("a#karyotype").css("text-decoration", "underline");
	});
	$("a[href^='#chromosome18']").on('click', () => {
		$("a#basic").css("text-decoration", "none");
		$("a#karyotype").css("text-decoration", "none");
		return $("a#chromosome18").css("text-decoration", "underline");
	});
	$("a[href^='#basic']").on('click', () => {
		$("a#chromosome18").css("text-decoration", "none");
		$("a#karyotype").css("text-decoration", "none");
		return $("a#basic").css("text-decoration", "underline");
	})
	//url for ajax call
	let url = window.location.origin  + "/subscribeEmail";
	//ajax request
	$("#lower_form").on('submit', function(e){
		e.preventDefault();
		$("#success.success").text("");
		let $this = $("input#email");
		let json = {
				"email" : $this.val()
		};
		$.ajax({
			method: "POST",
			url: url,
			headers: {
				'Accept': "application/json",
				'Content-Type': "application/json",
				'X-CSRF-TOKEN' : $token
			},
			data: JSON.stringify(json),
			dataType: "json",			
			success: function(data){
						if(data.status === 'success'){
							$("#success.success", "#lower_form").text(data.message);
							$("input#email").on('focus', function(){
								$(this).next("#error.error").text("");
								$(this).val("");
							});
							$this.val("");
						}
					},
			error: function(data){
				if(data.status === 400){
					$this.next("#error.error").text(data.responseJSON.message);
				}
			}
		});
	});
	//search input box handling
	let $input = $("input[type='search']");
	$input.on('focus', function(){
		$(this).removeAttr('placeholder');
	});
	$("#upper_form").on('submit', function(e){	
		if($input.val() === '' || $input.val().match("^\\s+$") || $input.val().match("[^a-zA-Z0-9'\"\\sžćčđš]+")){
			e.preventDefault();	
			$input.addClass('upperborder').focusout(function(){
				$(this).removeClass('upperborder');
			});
		}else {
			$(this).unbind('submit').submit();
		}
	});
	//newsletter input box handling
	$("input#email").on('focus', function(){
		$(this).next("#error.error").text("");
		$(this).removeAttr('placeholder');
		$(this).next().next("#success.success").text("");
	});
	//new password request
	$("input#new-email").on('focus', function(){
		$(this).removeAttr('placeholder');
	});
	//login
	$("input#username, input#password").on('focus', function(){
		$(this).removeAttr('placeholder');
	});
	//scroll to top animation
	$("a[href='#top']").on('click', function(){
		$("html, body").animate({ scrollTop : 0 }, 'slow');
	});
	// fading h1 and an image
	$("#image, h1").hide().fadeIn();
})(jQuery);
