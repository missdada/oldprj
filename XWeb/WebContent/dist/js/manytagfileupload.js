var uploadfiles = new Array();
var filestart = {};
$(document).ready(function() {
	var start = 0;
	$('.add').click(function() {
		var id = this.id;
		if (filestart[id]) {
			start = filestart[id].start
		} else {
			filestart[id] = new Object();
			filestart[id].start = 0;
			start = filestart[id].start
		}
		$('#' + id + '_preview' + start).find('input').trigger('click');
		//$('#preview'+start).addClass('on');
		start += 1;
		filestart[id].start = start;
		var pianduan = '';
		pianduan = '<div id="' + id + '_preview' + start + '" onclick="xian(this)" class="yi"><img id="' + id + '_imghead' + start + '" border="0" src=""><input type="file" name="' + id + '" onchange="previewImage(this,' + start + ');addimgfile(this,' + start + ');"/></div>';
		$(this).before(pianduan);
	});
});

function xian(e) {
	var opacity = $(e).find('.shan').css('opacity');
	if (opacity == 1) {
		$(e).find('.shan').css('opacity', 0);
	} else {
		$(e).find('.shan').css('opacity', 1);
	}
}

function shan(e) {
	deletefile($(e).next("img")[0]);
	$(e).parent().remove();
}

function addimgfile(afile, start) {
	var id = afile.name;
	var imgfile = {};
	imgfile.id = id;
	imgfile.imgid = id + "_imghead" + start;
	imgfile.file = afile.files[0];
	uploadfiles.push(imgfile);
}

function addimgfileUrl(fileurl, start, id) {
	var imgfile = {};
	imgfile.id = id;
	imgfile.imgid = id + "_imghead" + start;
	//imgfile.file = afile.files[0];
	imgfile.url = fileurl;
	uploadfiles.push(imgfile);
}

function deletefile(imgobj) {
	for (var i = 0; i < uploadfiles.length; i++) {
		if (uploadfiles[i].imgid == imgobj.id) {
			uploadfiles.splice(i, 1);
			return;
		}
	}
}

function setUploadFileToFormData(fd) {
	var uploadfileurls = "";
	for (var i = 0; i < uploadfiles.length; i++) {
		if (uploadfiles[i].file) {
			fd.append(uploadfiles[i].imgid, uploadfiles[i].file);
		}
		if (uploadfiles[i].url) {
			if (uploadfileurls == "") {
				uploadfileurls = uploadfiles[i].id + "_" + uploadfiles[i].url;
			} else {
				uploadfileurls += "," + uploadfiles[i].id + "_" + uploadfiles[i].url;
			}
		}
	}
	if (uploadfileurls != "") {
		fd.append("_uploadfile_uids", uploadfileurls);
	}
}

/*
function compress(source, target) {
	var image = new Image();
	var ndata;
	image.onload = function() {
		var canvas = document.getElementById("myCanvas");
		console.log(canvas);
		// 获取 canvas的 2d 环境对象,
		// 可以理解Context是管理员，canvas是房子
		var ctx = canvas.getContext("2d");
		// canvas清屏
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		// 重置canvas宽高
		can_width = 1024;
		can_height = image.height * (can_width / image.width);
		canvas.width = can_width;
		canvas.height = can_height;
		// 将图像绘制到canvas上
		ctx.drawImage(image, 0, 0, can_width, can_height);
		ndata = canvas.toDataURL("image/jpeg", 0.1);
		target.src = ndata;
		console.log("canvas压缩后：" + ndata.length);
	};
	target.onload = function(){
		console.log("预览压缩后：" + target.src.length);
	};
	image.src = source;
}

function render(src, xu) {
	var initsize = src.length;
	var img = document.getElementById('imghead' + xu);
	compress(src, img);
	console.log("压缩前：" + initsize);
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
	var param = {
		top: 0,
		left: 0,
		width: width,
		height: height
	};
	if (width > maxWidth || height > maxHeight) {
		rateWidth = width / maxWidth;
		rateHeight = height / maxHeight;
		
		if (rateWidth > rateHeight) {
			param.width = maxWidth;
			param.height = Math.round(height / rateWidth);
		} else {
			param.width = Math.round(width / rateHeight);
			param.height = maxHeight;
		}
	}
	
	param.left = Math.round((maxWidth - param.width) / 2);
	param.top = Math.round((maxHeight - param.height) / 2);
	return param;
}
*/

function previewImage(file, xu) {
	var id = file.name;
	var div = document.getElementById(id + '_preview' + xu);
	$(div).addClass('on');
	if (file.files && file.files[0]) {
		var upfile = file.files[0];
		div.innerHTML = '<span class="shan" onclick="shan(this)"></span><img id="' + id + '_imghead' + xu + '">';
		
		var reader = new FileReader();
		reader.onload = function(evt) {
		//	render(evt.target.result, xu);
			var img = document.getElementById(id + '_imghead' + xu);
			if (upfile.type.indexOf("image/") > -1) {
				img.src = evt.target.result;
			} else {
				img.src = "";
				img.alt = upfile.name;
			}
		}
		reader.readAsDataURL(upfile);
	} else {
		var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
		file.select();
		var src = document.selection.createRange().text;
		div.innerHTML = '<span class="shan" onclick="shan(this)"></span><img id="imghead' + xu + '">';
		var img = document.getElementById('imghead' + xu);
		img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
		var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
		status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
		div.innerHTML = "<div id=divhead style='width:" + rect.width
				+ "px;height:" + rect.height + "px;margin-top:" + rect.top
				+ "px;margin-left:" + rect.left + "px;" + sFilter + src
				+ "\"'></div>";
	}
}

function getUploadFileByUid(uploadfile_uids, id, flag) {
	if (uploadfile_uids) {
		var query = {};
		query.uid = uploadfile_uids;
		var url = serverSrc + "/systemResourceUpload.do?_method=getUploadFile" ;
		Base.ajax({
			url: url,
			type: "post",
			dataType: "json",
			data: {
				_json: JSON.stringify(query)
			},
			mysuccess: function(r) {
				if (JSON.stringify(r) == "{}") {
					return;
				}
				
				if (r.rows) {
					for (var i = 0; i < r.rows.length; i++) {
						var rr = r.rows[i];
						var rrs = rr.split(',');
						previewExistedImage(serverSrc + rrs[1], rrs[2], id + "_imghead" + i, id + "_preview" + i);
						addimgfileUrl(rrs[0], i, id);
						addNextImageDiv(i + 1, id, flag);
					}
				}
			}
		});
	}
}

function addNextImageDiv(index, id, flag) {
	var pianduan = flag == 0 ?
			'<div id=\"' + id + '_preview' + index + '\" class="yi"><img id=\"' + id + '_imghead' + index + '\" border="0" src=""><input type="file" name="' + id + '" hidden onchange="previewImage(this,' + index + ');addimgfile(this,' + index + ');" style="display:none"/></div>'
			:
			'<div id=\"' + id + '_preview' + index + '\" onclick="xian(this)" class="yi"><img id=\"' + id + '_imghead' + index + '\" border="0" src=""><input type="file" name="' + id + '" hidden onchange="previewImage(this,' + index + ');addimgfile(this,' + index + ');" style="display:none"/></div>';
	$('.add[id="' + id + '"]').before(pianduan);
	if (filestart[id]) {
		filestart[id].start = index;
	} else {
		filestart[id] = new Object();
		filestart[id].start = index;
	}
}

function previewExistedImage(url, filename, imageCtrlId, divId, hideDivClass) {
	var div = document.getElementById(divId);
	$(div).addClass('on');
	if (null != hideDivClass) {
		$("." + hideDivClass).hide();
	}
	var filen = url.substring(url.lastIndexOf('/') + 1);
	var filetype = filen.substring(filen.indexOf('.') + 1);
	if ("png,jpg,gif".indexOf(filetype) > -1) {
		//是图片
		div.innerHTML = '<span class="shan" onclick="shan(this, true);"></span><img id="' + imageCtrlId + '" src="' + url + '">';
	} else {
		div.innerHTML = '<span class="shan" onclick="shan(this, true);"></span><img id="' + imageCtrlId + '" alt="' + filename + '">';
	}
}

function showimages(value, row, index) {
	//alert(value);
	var urls = JSON.parse(value);
	
	var html = '';
	if (urls && urls != '') {
		/*
		var utlsTemp = urls.split(",");
		for (var i = 0; i < utlsTemp.length; i++) {
			var url = utlsTemp[i];
			html += ('<span class="imageicon"><img src="/XServer' + url + '"/></span>&nbsp;&nbsp;');
		}
		*/
		for (var i = 0; i < urls.length; i ++) {
			var item = urls[i];
			var url = item.url;
			var fileName = item.file_name;
			var temp = "";
			if (fileName && fileName != '') {
				temp = fileName.toLowerCase();
			}
			if (temp.endWith('.png') || temp.endWith('.jpg') || temp.endWith('.jpeg') || temp.endWith('.gif') || temp.endWith('.bmp') || temp.endWith('.tif')) {
				html += ('<span class="imageicon"><img src="' + serverSrc + url + '" alt="' + fileName + '"/></span>&nbsp;&nbsp;');
			} else {
				html += ('<span><a href="' + serverSrc + url + '" alt="' + fileName + '">' + fileName + '</a></span>&nbsp;&nbsp;');
			}
		}
		html += '<div style="clear:both;height:0;width:0"/>';
	} else {
	}
	return [
		html
	].join('');
}