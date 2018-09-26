var local = window.location;
var projectName = local.pathname.split("/")[1];
var netPath = local.protocol + "//" + local.host + "/";
var basePath = netPath + projectName + "/";

//可配置项
var serverNetPath = netPath;
//可配置项
var serverProjectName = "XServer";
if (!serverProjectName) {
	serverProjectName = projectName;
}
//可配置项
var serverSrc = "";
if (!serverSrc) {
	serverSrc = serverNetPath + serverProjectName
}

function goUrl(url) {
	window.location.href = getUrl(url);
}

function getUrl(url) {
	return basePath + url;
}

function getServerUrl(url) {
	return serverSrc + url;
}

function iframeUrl(url) {
	window.parent.document.getElementById("frameWin").src = getUrl("pages/business/" + url);
	window.parent.location.hash = "pages/business/" + url;
}

function systemUrl(url) {
	window.parent.document.getElementById("frameWin").src = getUrl("pages/common/" + url);
	window.parent.location.hash = "pages/common/" + url;
}

var onerror = function (response) {
	if (response) {
		var status_code = response.status;
		if (status_code == '403') {
			// 登录超时，弹出登录页面
			var dlg = "<div id='logindlg'></div>";
			$(dlg).dialog({
				title: '系统登录',
				width: 400,
				height: 200,
				closed: false,
				cache: false,
				href: '../../pub/login.html',
				modal: true
			});
		}
	}
};

var Base = {};
Base.ajax = function (opt) {
	var onsuccess = function (response) {
		if (response && response.message) {
			var msgInfo = response.message;
			if (msgInfo.messageId == 'E0002' || msgInfo.messageId == 'E0000') {
				// 权限检查未通过
				window.parent.location.href = "/" + projectName + "/pages/pub/403.html"
				return;
			}
			/*
			 * $.messager.popup({ title:msgInfo.messagetype,
			 * msg:msgInfo.messagestr, showType:'show', style:{ left:'',
			 * right:0,
			 * top:document.body.scrollTop+document.documentElement.scrollTop,
			 * bottom:'' } });
			 */
			// $.messager.popup(msgInfo.messagestr);
		}

		if (typeof (opt.mysuccess) == 'function') {
			opt.mysuccess(response);
		}
	}

	opt.success = onsuccess;
	opt.error = onerror;
	return $.ajax(opt);
};

function getSelectOptionsFromTable(selectedValue, controlName, tableName, key, name, firstBlank, where, orderBy) {
	var oControl = $("#" + controlName);
	oControl.empty();
	var searchurl = "";
	if (where) {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name
			+ "&where=" + where;
	} else {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name;
	}

	if (orderBy) {
		searchurl += "&orderBy=" + orderBy;
	}

	$.ajax({
		url: searchurl,
		type: "post",
		dataType: "json",
		success: function (res) {
			if (firstBlank) {
				var option = $("<option>").val("").text("-");
				oControl.append(option);
			}
			if (res.droplist.length > 0) {
				for (var i = 0; i < res.droplist.length; i++) {
					var option = $("<option>").val(res.droplist[i].value).text(res.droplist[i].name);
					oControl.append(option);
				}
				if (selectedValue && selectedValue != '') {
					oControl.val(selectedValue);
				} else {
					if (!firstBlank) {
						oControl.val(res.droplist[0].value);
					}
				}
			}
		}
	});
}

function getSelectOptionsFromDic(appUid, selectedValue, controlName, dicCode, firstBlank) {
	var oControl = $("#" + controlName);
	oControl.empty();
	$.ajax({
		url: serverSrc + "/getDropDown.do?_method=fromDic&app_uid=" + appUid + "&code=" + dicCode,
		type: "post",
		dataType: "json",
		success: function (res) {
			if (firstBlank) {
				var option = $("<option>").val("").text("-");
				oControl.append(option);
			}
			if (res.droplist.length > 0) {
				for (var i = 0; i < res.droplist.length; i++) {
					var option = $("<option>").val(res.droplist[i].value).text(res.droplist[i].name);
					oControl.append(option);
				}
				if (selectedValue != '') {
					oControl.val(selectedValue);
				}
			}
		}
	});
}

function getRadioFromTable(selectedValue, controlName, tableName, key, name, where, orderBy) {
	var oControl = $("#" + controlName);
	oControl.html();
	var searchurl = "";
	if (where) {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name
			+ "&where=" + where;
	} else {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name;
	}

	if (orderBy) {
		searchurl += "&orderBy=" + orderBy;
	}

	$.ajax({
		url: searchurl,
		type: "post",
		dataType: "json",
		success: function (res) {
			setRadioInnerHtml(res, selectedValue, controlName);
		}
	});
}

function getRadioFromDic(appUid, selectedValue, controlName, dicCode) {
	var oControl = $("#" + controlName);
	oControl.html();
	$.ajax({
		url: serverSrc + "/getDropDown.do?_method=fromDic&app_uid=" + appUid + "&code=" + dicCode,
		type: "post",
		dataType: "json",
		success: function (res) {
			setRadioInnerHtml(res, selectedValue, controlName);
		}
	});
}

function setRadioInnerHtml(res, selectedValue, controlName) {
	var oControl = $("#" + controlName);
	if (res.droplist.length > 0) {
		var html = '';
		for (var i = 0; i < res.droplist.length; i++) {
			html += '<label><input type="radio" class="radio" name="' + controlName + '" value="' + res.droplist[i].value + '">' + res.droplist[i].name + '</label>&nbsp;&nbsp;&nbsp;&nbsp;';
		}
		oControl.html(html);
		if (selectedValue && selectedValue != '') {
			$('input[name="' + controlName + '"][value="' + selectedValue + '"]').attr('checked', 'checked');
		} else {
			$('input[name="' + controlName + '"]').eq(0).attr('checked', 'checked');
		}
	}
}

function getCheckFromTable(selectedValue, controlName, tableName, key, name, where, orderBy) {
	var oControl = $("#" + controlName);
	oControl.html();
	var searchurl = "";
	if (where) {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name
			+ "&where=" + where;
	} else {
		searchurl = serverSrc + "/getDropDown.do?_method=fromTable&tableName="
			+ tableName + "&valueColumn=" + key + "&nameColumn=" + name;
	}

	if (orderBy) {
		searchurl += "&orderBy=" + orderBy;
	}

	$.ajax({
		url: searchurl,
		type: "post",
		dataType: "json",
		success: function (res) {
			setCheckInnerHtml(res, selectedValue, controlName);
		}
	});
}

function getCheckFromDic(appUid, selectedValue, controlName, dicCode) {
	var oControl = $("#" + controlName);
	oControl.html();
	$.ajax({
		url: serverSrc + "/getDropDown.do?_method=fromDic&app_uid=" + appUid + "&code=" + dicCode,
		type: "post",
		dataType: "json",
		success: function (res) {
			setCheckInnerHtml(res, selectedValue, controlName);
		}
	});
}

function setCheckInnerHtml(res, selectedValue, controlName) {
	var oControl = $("#" + controlName);
	if (res.droplist.length > 0) {
		var html = '';
		for (var i = 0; i < res.droplist.length; i++) {
			html += '<label><input type="checkbox" class="checkbox" value="' + res.droplist[i].value + '" name="' + controlName + '" >' + res.droplist[i].name + '</label> &nbsp;&nbsp;&nbsp;&nbsp;';
		}
		oControl.html(html);
		if (selectedValue) {
			setCheckValue(controlName, selectedValue);
		}
	}
}

function getCheckValue(name) {
	var returnval = '';
	$("#" + name + " input:checkbox:checked").each(function (index, e) {
		if (returnval == '') {
			returnval = e.value;
		} else {
			returnval += "," + e.value;
		}
	});
	return returnval;
}

function setCheckValue(name, value) {
	if (value) {
		var vs = value.split(",");
		for (var i = 0; i < vs.length; i++) {
			$("#" + name + " input:checkbox[value='" + vs[i] + "']").attr('checked', 'true');
		}
	}
}

function table_responseHandler(response) {
	if (response && response.message) {
		var msgInfo = response.message;
		if (msgInfo.messageId == 'E0002' || msgInfo.messageId == 'E0000') {
			// 权限检查未通过
			window.parent.location.href = "/" + projectName + "/pages/pub/403.html";
			return;
		}
	}
	return response;
}

function getindex(value, row, index) {
	var tableOpt = $('#table').bootstrapTable('getOptions');
	var pageNumber = tableOpt.pageNumber;
	var pageSize = tableOpt.pageSize;
	if (pageSize == "All") {
		pageSize = tableOpt.data.length;
	}
	return index + 1 + ((pageNumber - 1) * pageSize);
}

function getTableHeight() {
	var tableH = $(window).height() - $('.content-header').outerHeight(true) - $('#search-content').outerHeight(true);
	alert(tableH);
	return tableH;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

function subcontent(value, row, index) {
	if (value && value.length && value.length > 20) {
		value = '<a href="javascript:void(0);" data-toggle="tooltip" data-placement="left" title="'
			+ value + '">' + value.substring(0, 20) + '...</a>';
	}
	return value;
}

function getUserButton() {
	$.ajax({
		url: serverSrc + "/menu.do?_method=getUserButton",
		type: "post",
		dataType: "text",
		success: function (res) {
			//$('#transform-buttons').html(res);
			if (res) {
				if ('BTN_ALLPERMIS' == res) {
					$('*').removeClass('but-permit');
					return;
				}
				var mybut = res.split(',');
				for (var i = 0; i < mybut.length; i++) {
					//$('[user-permis="'+mybut[i]+'"]').removeClass('but-permit');
					$('[data-user-permis="' + mybut[i] + '"]').removeClass('but-permit');
				}
				$('.but-permit').remove();
			}
		}
	});
}

function setLS(key, value) {
	window.sessionStorage.setItem(key, value);
}

function getAndDelLS(key) {
	var lsvalue = null;
	lsvalue = window.sessionStorage.getItem(key);
	if (lsvalue) {
		window.sessionStorage.removeItem(key);
	}
	return lsvalue;
}

String.prototype.startWith = function (str) {
	var reg = new RegExp("^" + str);
	return reg.test(this);
}

String.prototype.endWith = function (str) {
	var reg = new RegExp(str + "$");
	return reg.test(this);
}