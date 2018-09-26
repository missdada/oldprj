function initPopover2() {
	$('[data-toggle="popover2"]').each(function () {
		var element = $(this);
		var id = element.attr('id');
		var txt = element.html();
		element.popover({
			trigger: 'manual',
			placement: 'bottom', //top, bottom, left or right
			title: txt,
			html: 'true',
			content: htmlContentPopover2(id),
		}).on("click", function () {
			var _this = this;
			$(this).popover("show");
			initPopoverFormat2();
			initPopoverValue2(this);
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

function htmlContentPopover2(id) {
	var html = '<div class="form-group">'
		+ '<div style="margin:3px;"><span style="color:#f00;margin-left:5px;">检索范围：</span></div>'
//		+ '<div class="row" style="margin:0 0 3px 0"><label for="minvalue" class="col-sm-6 control-label"">最小值:</label><div class="col-sm-6"><input type="text" class="form-control" id="minvalue"/></div></div>'
//		+ '<div class="row" style="margin:0"><label for="maxvalue" class="col-sm-6 control-label"">最大值:</label><div class="col-sm-6"><input type="text" class="form-control" id="maxvalue"/></div></div>'
		+ '<div class="input-group"><div class="input-group-addon" style="border:0;"><i class="fa">最小值</i></div><input type="text" class="form-control" id="minvalue2" style="padding:0 0 0 2px"/></div>'
		+ '<div class="input-group"><div class="input-group-addon" style="border:0;"><i class="fa">最大值</i></div><input type="text" class="form-control" id="maxvalue2" style="padding:0 0 0 2px"/></div>'
		+ '<div style="margin:5px 5px 0 0; float:right;"><input type="button" class="btn btn-info" style="margin-right:3px;" value="确定" onclick="doSetSearchValue2(' + id + ');"><input type="button" class="btn btn-info" value="取消" onclick="doCancelSearchValue2(' + id + ');"/></div>'
		+ '</div>';
	return html;
}

function doSetSearchValue2(id) {
	var min = $("#minvalue2").val();
	var max = $("#maxvalue2").val();
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

function doCancelSearchValue2(id) {
	$(id).popover("hide");
}

function initPopoverValue2(obj) {
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
	$("#minvalue2").val(min);
	$("#maxvalue2").val(max);
}

function initPopoverFormat2() {
	$("#minvalue2").inputmask('Regex', { regex: "-?[0-9]*[\.]?[0-9]*" });
	$("#maxvalue2").inputmask('Regex', { regex: "-?[0-9]*[\.]?[0-9]*" });
}