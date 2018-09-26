$(document).ready(function () {
	$("#frameWin").load(function () {
		var height = $(window).height() - $('.main-header').outerHeight(true)-$('.main-footer').outerHeight(true);
		$("#frameBox").height(height < 500 ? 500 : height);
		$(this).height(height < 500 ? 500 : height);
	});
});