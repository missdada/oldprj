var Popup = {};
Popup.layout = {
	init: function() {
		$(document.body).append(modalDiv);
	},
	show: function(id, url, onPopReturn) {
		window.sessionStorage.setItem(id + "_modal", "1");
		$('#framePopup').attr('src', url);
		$('.modal-dialog').css('width', $(window).width() - 20);
		$('.modal-content').css('height', $(window).height() - 20);
		$('#framePopup').height($(window).height() - 180);
		$('#popup_content').on('hide.bs.modal', function (obj) {
			////待解决问题：弹出窗口无论是按确定还是按X都返回选择的数据
			if (closemodal == 0) {
				window.sessionStorage.removeItem('popreturnval');
				return;
			}
			var selectObj = window.sessionStorage.getItem('popreturnval');
			if (typeof(selectObj) != 'undefined') {
				window.sessionStorage.removeItem('popreturnval');
				var selects = JSON.parse(selectObj);
				if (selects) {
					if (onPopReturn) {
						onPopReturn.apply(Popup, selects);
					}
				}
			}
			$('#framePopup').attr('src', '');
			closemodal = 0;
		});
	}
};

var closemodal=0;
var modalDiv = '<div class="modal fade" id="popup_content" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >'
	+ '<div class="modal-dialog">'
	+ '<div class="modal-content">'
	+ '<div class="modal-header">'
	+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
	+ '<h4 class="modal-title" id="myModalLabel">选择</h4>'
	+ '</div>'
	+ '<div class="modal-body" id="mb">'
	+ '<iframe src="" id="framePopup" name="framePopup" style="width:100%;heigth:100%;overflow-x: hidden; overflow-y: auto;" frameborder="0" marginheight="0" marginwidth="0"></iframe> '
	+ '</div>'
	+ '<div class="modal-footer">'
	+ '<button type="button" class="btn btn-primary pull-left" data-dismiss="modal" onclick="javascript:closemodal=1;">确定</button>'
	+ '</div></div></div></div>';

function popupShow(id, url, fun) {
	Popup.layout.show(id, url, fun);
}