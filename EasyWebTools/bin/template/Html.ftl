<!DOCTYPE html>
<html>
<head lang="en">
	<meta charset="UTF-8">
	<title>myTitle</title>
	<link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../../bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" href="../../bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="../../dist/css/AdminLTE.css">
	<link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="../../dist/css/manytag.css">
	<style type="text/css">
		td.alignRight {
			text-align: right;
		}
		td.alignCenter {
			text-align: center;
		}
		tr th {
			text-align: center;
		}
	</style>
<#import "/Common.ftl" as myFun>
<#assign tableName>
<@compress single_line=true>
<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>
</@compress>
</#assign>
<#assign tableNameCh>
<@compress single_line=true>
	<#assign result = oDBTableInfo.tableName>
	<#if oDBTableInfo.remarks??>
		<#assign result = oDBTableInfo.remarks />
	</#if>
	${result}
</@compress>
</#assign>
<#assign firstPrimaryKey>
<@compress single_line=true>
	<#assign result = ''>
	<#list oDBTableInfo.primaryKey?keys as key>
		<#if key_index == 0>
			<#assign result = oDBTableInfo.primaryKey[key].columnName />
		</#if>
	</#list>
	${result}
</@compress>
</#assign>
<#assign existFileUpload>
<@compress single_line=true>
	<#assign result = false>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
			<#assign result = true />
		</#if>
	</#list>
	${result?c}
</@compress>
</#assign>
</head>
<body>
	<div class="wrapper">
		<section class="content-header">
			<h1>详细数据	<small>${tableNameCh}</small></h1>
			<ol class="breadcrumb">
				<li><a href="../../dashboard.html"><i class="fa fa-home"></i>首页</a></li>
				<li><a href="#">详细数据</a></li>
				<li class="active">${tableNameCh}</li>
			</ol>
		</section>
		<section class="content">
			<div class="row">
				<div class="box box-danger">
					<div class="box-header with-border">
						<h3 class="box-title">检索条件</h3>
						<div class="box-tools pull-right">
							<button class="btn btn-box-tool" data-widget="collapse">
								<i class="fa fa-minus fa-lg"></i>
							</button>
						</div>
					</div>
					<form class="form-horizontal">
						<div class="box-body">
							<div class="form-group">
						<#list oDBTableInfo.columns as oDBColumnInfo>
							<#if oDBColumnInfo.extendInfo.searchFlag?? && oDBColumnInfo.extendInfo.searchFlag?lower_case=='true'>
								<div class="col-sm-12 col-md-4 col-lg-4">
									<div class="col-sm-3 col-md-5 col-lg-5 control-label" style="border:0;"><i class="fa" style="font-weight:bold;"><#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>：</i></div>
									<div class="col-sm-7 col-md-7 col-lg-7">
									<#if (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date')|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
										<input type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" data-toggle="popover2t">
									<#elseif oDBColumnInfo.typeName == "DOUBLE" || oDBColumnInfo.typeName == "INT">
										<input type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" data-toggle="popover2">
									<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select'>
										<input type="hidden" class="form-control" id="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>">
										<select class="form-control select2" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" style="width: 100%;"></select>
									<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio'>
										<div class="radio" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>"></div>
									<#else>
										<input type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>">
									</#if>
									</div>
								</div>
							</#if>
						</#list>
							</div>
						</div>
						<div class="box-footer">
							<a class="btn btn-info but-permit" onclick="doSearch();" data-user-permis="btn_search">检索</a>
						</div>
					</form>
				</div>
				<div class="box">
					<div class="box-body">
						<div id="transform-buttons" class="btn-group btn-default" style="position: absolute; top: 10px; left: 10px;">
							<button class="btn btn-default but-permit" id="btn_add" data-user-permis="btn_add">
								<i class="fa fa-file-o"></i><span data-zh="新建" data-es="Transformar">新建</span>
							</button>
							<button class="btn btn-default but-permit" id="btn_modify" data-user-permis="btn_modify">
								<i class="fa fa-edit"></i><span data-zh="修改" data-es="Transformar">修改</span>
							</button>
							<button class="btn btn-default but-permit" id="btn_view" data-user-permis="btn_view">
								<i class="fa fa-eye"></i><span data-zh="查看" data-es="Transformar">查看</span>
							</button>
							<button class="btn btn-default but-permit" id="btn_delete" data-user-permis="btn_delete">
								<i class="fa fa-trash"></i><span data-zh="删除" data-es="Destruir">删除</span>
							</button>
						</div>
						<table id="table"></table>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>

<script type="text/javascript" src="../../plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<script src="../../bootstrap-table/bootstrap-table.js"></script>
<script src="../../bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="../../plugins/jQuery/jquery.bootstrap.min.js"></script>
<script src="../../dist/js/command.js"></script>
<script src="../../dist/js/message.js"></script>
<script src="../../dist/js/app.min.js"></script>
<script src="../../dist/js/popover2.js"></script>
<script src="../../dist/js/popover2t.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.regex.extensions.js"></script>
<script src="../../plugins/input-mask/jquery.inputmask.numeric.extensions.js"></script>
<#if existFileUpload == 'true'>
<script src="../../dist/js/manytagfileupload.js"></script>
<script src="../../dist/js/popup.js"></script>
</#if>
<script>
	var ismodal = 0;
	$(function () {
		init();
	});
	
	function init() {
		initData();
		initUI();
	}
	
	function initData() {
		ismodal = getAndDelLS('${tableName}_modal');
		if (ismodal) {
			ismodal = '1';
		}
	}
	
	function initUI() {
		$('#btn_delete').prop('disabled', true);
		$('#btn_modify').prop('disabled', true);
		$('#btn_view').prop('disabled', true);
		
		$('#table').on('check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table load-success.bs.table', function () {
			$('#btn_delete').prop('disabled', !$('#table').bootstrapTable('getSelections').length);
			$('#btn_modify').prop('disabled', $('#table').bootstrapTable('getSelections').length != 1);
			$('#btn_view').prop('disabled', $('#table').bootstrapTable('getSelections').length != 1);
			
			if (ismodal == '1') {
				setLS('popreturnval', getPopupResult());
			}
		});
		
		if (ismodal != '1') {
			$('#btn_delete').click(function (){ doDelete(getSelectedItems()); });
			$('#btn_modify').click(function (){ doModify(getSelectedItems()); });
			$('#btn_add').click(function (){ doAdd(); });
			$('#btn_view').click(function (){ doView(getSelectedItems()); });
			
			getUserButton();
		}
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table'>
		getSelectOptionsFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}', true);
				<#else>
		getSelectOptionsFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}', true);
				</#if>
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table'>
		getRadioFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
				<#else>
		getRadioFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
				</#if>
			</#if>
		</#list>
		initPopover2();
		initPopover2t();
		tableInit();
	}
	
	function tableInit() {
		var herf = serverSrc + "/${tableName?uncap_first}.do?_method=doSearch";
		$("#table").bootstrapTable({
			url: herf,
			toggle: "table",
			clickToSelect: true,//是否启用点击选中行
			sidePagination: "server",//分页方式：client客户端分页，server服务端分页（*）
			pagination: true,//是否显示分页（*）
			pageList: [10, 25, 50, 100], //可供选择的每页的行数
			showRefresh: true,
			responseHandler: "table_responseHandler",
			queryParams: queryParams,
			columns: [
				{
					field: 'state',
					checkbox: true
				},
				{
					title: 'NO',
					formatter: function (value, row, index) {
						return index + 1
					}
				<#list oDBTableInfo.columns as oDBColumnInfo>
				},
				{
					<#if oDBColumnInfo.isPrimaryKey()>
					field: "<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>",
					title: "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>",
					visible: false
					<#else>
					<#if (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date')|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
					field: "<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str",
					title: "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>"<#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>,
					visible: false</#if>
					<#elseif oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
					field: "<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str",
					title: "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>"<#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>,
					visible: false</#if>
					<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
					field: "<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str",
					title: "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>",
					formatter: "showimages"<#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>,
					visible: false</#if>
					<#else>
					field: "<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>",
					title: "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>"<#if oDBColumnInfo.extendInfo.subContent?? && oDBColumnInfo.extendInfo.subContent?lower_case=='true'>,
					formatter: "subcontent"</#if><#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>,
					visible: false</#if>
					</#if>
					</#if>
				</#list>
				}
			]
		});
	}
	
	function getSelectedItems() {
		return $('#table').bootstrapTable('getSelections');
	}
	
	function actionFormatter(value, row, index) {
		return [
			'<button class="btn btn-primary resend" style="margin:-10px 6px;">详细</button>'
		].join('');
	}
	
	window.actionEvents = {
		'click .resend': function (e, value, row, index) {
			alert('重新发送, row: ' + JSON.stringify(row));
			console.log(value, row, index);
		}
	};
	
	function doDelete(oJsonSelects) {
		$.messager.confirm('提示', getMessage('MSG_COMMON_I0002'), function(r) {
			ok: {
				var data = [];
				for (var i = 0, l = oJsonSelects.length; i < l; i++) {
					var ids = {};
					<#list oDBTableInfo.primaryKey?keys as key>ids.${oDBTableInfo.primaryKey[key].columnName} = oJsonSelects[i].${oDBTableInfo.primaryKey[key].columnName};</#list>
					data.push(ids);
				}
				
				Base.ajax({
					url: serverSrc + "/${tableName?uncap_first}.do?_method=deleteList",
					type: "post",
					dataType: "json",
					data: {
						_json: JSON.stringify(data)
					},
					mysuccess: function(data) {
						var count = data.count;
						if (count == 0) {
							$.messager.alert("提示", data.errorMessage);
						}
						
						$('#btn_delete').prop('disabled', true);
						doSearch();
					}
				});
			}
		});
	}
	
	function doModify(oJsonSelects) {
		var selects = oJsonSelects;
		var idKey = <#if oDBTableInfo.primaryKey?size==0>selects.key[0]<#else>"${firstPrimaryKey}"</#if>;
		var idValue = escape(<#if oDBTableInfo.primaryKey?size==0>selects[0][0]<#else>selects[0].${firstPrimaryKey}</#if>);
		var doModifyHerf = "${tableName}_Detail.html?id=" + idValue + "&key=" + idKey;
		window.location.href = doModifyHerf;
		iframeUrl(doModifyHerf);
	}
	
	function doView(oJsonSelects) {
		var selects = oJsonSelects;
		var idKey = <#if oDBTableInfo.primaryKey?size==0>selects.key[0]<#else>"${firstPrimaryKey}"</#if>;
		var idValue = escape(<#if oDBTableInfo.primaryKey?size==0>selects[0][0]<#else>selects[0].${firstPrimaryKey}</#if>);
		var doViewHerf = "${tableName}_View.html?id=" + idValue + "&key=" + idKey;
		window.location.href = doViewHerf;
		iframeUrl(doViewHerf);
	}
	
	function doAdd() {
		var doAddHerf = "${tableName}_Detail.html";
		window.location.href = doAddHerf;
		iframeUrl(doAddHerf);
	}
	
	function doSearch() {
		$('#table').bootstrapTable('destroy');
		tableInit();
		return;
	}
	
	function queryParams(params) {
		var paras = {};
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.searchFlag?? && oDBColumnInfo.extendInfo.searchFlag?lower_case=='true'>
			<#if (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date')|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
		var <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = $("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val();
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str != '') {
			var splits = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str.split("~");
			if (splits.length > 1) {
				if (splits[0] != "") {
					paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = splits[0];
				}
				if (splits[1] != "") {
					paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = splits[1];
				}
			} else {
				paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
			}
		}
			<#elseif oDBColumnInfo.typeName == "DOUBLE" || oDBColumnInfo.typeName == "INT">
		var <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = $("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val();
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str != '') {
			var splits = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str.split("~");
			if (splits.length > 1) {
				if (splits[0] != "") {
					paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = splits[0];
				}
				if (splits[1] != "") {
					paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = splits[1];
				}
			} else {
				paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
			}
		}
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio'>
		var <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = $("input:radio[name='<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>']:checked").val();
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> && <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != '') {
			paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = escape(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		}
			<#else>
		var <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = $("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val();
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> && <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != '') {
			paras.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = escape(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		}
			</#if>
		</#if>
		</#list>
		
		var queryPara = JSON.stringify(paras);
		params._json = queryPara;
		
		return params;
	}
	
	function getPopupResult() {
		return JSON.stringify($('#table').bootstrapTable('getSelections'));
	}
</script>
</html>