function initPopover2t() {
	$('[data-toggle="popover2t"]').each(function () {
		var element = $(this);
		var id = element.attr('id');
		var txt = element.html();
		element.popover({
			trigger: 'manual',
			placement: 'bottom', //top, bottom, left or right
			title: txt,
			html: 'true',
			content: htmlContentPopover2t(id),
		}).on("click", function () {
			var _this = this;
			$(this).popover("show");
			initPopoverFormat2t();
			initPopoverValue2t(this);
			$(this).siblings(".popover").on("mouseleave", function () {
				$(_this).popover('hide');
			});
		}).on("mouseleave", function () {
			var _this = this;
			setTimeout(function () {
				if (!$(".popover:hover").length) {
					$(_this).popover("hide")
				}
			}, 100);
		});
	});
}

function htmlContentPopover2t(id) {
	var html = '<div class="form-group">'
		+ '<div style="margin:3px;"><span style="color:#f00;margin-left:5px;">检索范围：</span></div>'
//		+ '<div class="row" style="margin:0 0 3px 0"><label for="minvalue" class="col-sm-6 control-label"">最小值:</label><div class="col-sm-6"><input type="text" class="form-control" id="minvalue" placeholder="yyyy-mm-dd"/></div></div>'
//		+ '<div class="row" style="margin:0"><label for="maxvalue" class="col-sm-6 control-label"">最大值:</label><div class="col-sm-6"><input type="text" class="form-control" id="maxvalue" placeholder="yyyy-mm-dd"/></div></div>'
		+ '<div class="input-group"><div class="input-group-addon" style="border:0;"><i class="fa">最小值</i></div><input type="text" class="form-control" id="minvalue" style="padding:0 0 0 2px"/></div>'
		+ '<div class="input-group"><div class="input-group-addon" style="border:0;"><i class="fa">最大值</i></div><input type="text" class="form-control" id="maxvalue" style="padding:0 0 0 2px"/></div>'
		+ '<div style="margin:5px 5px 0 0; float:right;"><input type="button" class="btn btn-info" style="margin-right:3px;" value="确定" onclick="doSetSearchValue2t(' + id + ');"><input type="button" class="btn btn-info" value="取消" onclick="doCancelSearchValue2t(' + id + ');"/></div>'
		+ '</div>';
	return html;
}

function doSetSearchValue2t(id) {
	var min = $("#minvalue").val();
	var max = $("#maxvalue").val();
	var value = "";
	if (min != "") {
		value = min;
	}
	if (max != "") {
		value = value + "~" + max;
	}
	$(id).val(value);
	$(id).popover("hide");
}

function doCancelSearchValue2t(id) {
	$(id).popover("hide");
}

function initPopoverValue2t(obj) {
	var min = "";
	var max = "";
	var txt = $(obj).val();
	if (txt) {
		var splits = txt.split("~");
		if (splits.length > 1) {
			min = splits[0];
			max = splits[1];
		} else {
			min = txt;
		}
	}
	$("#minvalue").val(min);
	$("#maxvalue").val(max);
}

function initPopoverFormat2t() {
	$("#minvalue").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
	$("#maxvalue").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
}