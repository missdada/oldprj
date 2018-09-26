<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>myTitle</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="../../other/css/font-awesome.min.css">
	<link rel="stylesheet" href="../../other/css/ionicons.min.css">
	<link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
	<link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
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
<#assign existAppUid>
<@compress single_line=true>
	<#assign result = false>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.columnName == 'app_uid'>
			<#assign result = true />
		</#if>
	</#list>
	${result?c}
</@compress>
</#assign>
<#assign existRichClientText>
<@compress single_line=true>
	<#assign result = false>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
			<#assign result = true />
		</#if>
	</#list>
	${result?c}
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
<#assign existReference>
<@compress single_line=true>
	<#assign result = false>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='reference'>
			<#assign result = true />
		</#if>
	</#list>
	${result?c}
</@compress>
</#assign>
<#if existRichClientText == 'true'>
	<link href="../../dist/js/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
</#if>
<#if existFileUpload == 'true'>
	<link rel="stylesheet" href="../../dist/css/yu.css">
</#if>
	<style>
		.tiant >div.add {
			display:none;
		}
	</style>
</head>
<body>
	<div class="wrapper">
		<div>
			<section class="content-header">
				<h1>${tableNameCh}</h1>
				<ol class="breadcrumb">
					<li><a href="../../dashboard.html"><i class="fa fa-dashboard"></i>首页</a></li>
					<li><a href="#">详细数据</a></li>
					<li><a href="#">${tableNameCh}</a></li>
				</ol>
			</section>
			<section class="content">
				<div class="row">
					<div class="col-md-9">
						<div class="nav-tabs-custom">
							<div class="tab-content">
								<div class="active tab-pane" id="settings">
									<form id="form" class="form-horizontal">
										<#list oDBTableInfo.columns as oDBColumnInfo>
										<#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>
										<#else>
										<div class="form-group<#if oDBColumnInfo.isPrimaryKey()> hide</#if>">
											<label class="col-sm-2 control-label"><#if !oDBColumnInfo.isNullAbled()><span class="text-red">*&nbsp;</span></#if><#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if></label>
											<div class="col-sm-8">
											<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
												<input type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" placeholder="yyyy-mm-dd" onClick="WdatePicker()" disabled="disabled">
											<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio'>
												<div class="radio" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" disabled="disabled"></div>
											<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox'>
												<div class="checkbox" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" disabled="disabled"></div>
											<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
												<div class="tiant" disabled="disabled">
													<div id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>_preview0" class="yi">
														<img id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>_imghead0" border="0" src=""/>
														<input type="file" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" onchange="previewImage(this,0);addimgfile(this,0);"/>
													</div>
													<div id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" class="add"></div>
												</div>
											<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='reference'>
												<div disabled="disabled">
													<input type="text" class="form-control hide" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>">
													<input disabled="disabled" type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str">
												</div>
											<#else>
												<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select'>
												<input type="hidden" class="form-control" id="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>">
												<select class="form-control select2" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>" style="width: 100%;" disabled="disabled"></select>
												<#else>
												<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
												<textarea class="form-control" rows="10" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>" style="width:100%;height:500px"></textarea>
												<#else>
												<#if oDBColumnInfo.extendInfo.multi?? && oDBColumnInfo.extendInfo.multi?lower_case=='true'>
												<textarea class="form-control" rows="3" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" disabled="disabled" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>" style="resize:none;"></textarea>
												<#else>
												<input type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" disabled="disabled" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>">
												</#if>
												</#if>
												</#if>
											</#if>
											</div>
										<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
											<label for="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" class="col-sm-2 text-red"></label>
										<#else>
											<label for="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" class="col-sm-2 text-red"></label>
										</#if>
										</div>
										</#if>
										</#list>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button id="btn_save" type="button" class="btn btn-default hide"><i class="fa fa-save"></i>保存</button>
												<button id="btn_return" class="btn btn-default"><i class="fa fa-undo"></i>返回</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>

<script src="../../plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="../../plugins/validation/dist/jquery.validate.js"></script>
<script src="../../plugins/validation/dist/locales/messages_zh.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<script src="../../plugins/fastclick/fastclick.min.js"></script>
<script src="../../dist/js/app.min.js"></script>
<!--[if lt IE 9]>
<script src="../../other/js/html5shiv.min.js"></script>
<script src="../../other/js/respond.min.js"></script>
<![endif]-->
<script src="../../plugins/jQuery/jquery.bootstrap.min.js"></script>
<script src="../../plugins/My97DatePicker/WdatePicker.js"></script>
<script src="../../dist/js/command.js"></script>
<script src="../../dist/js/message.js"></script>
<#if existRichClientText == 'true'>
<script type="text/javascript" charset="utf-8" src="../../dist/js/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../dist/js/umeditor.min.js"></script>
<script type="text/javascript" src="../../dist/js/lang/zh-cn/zh-cn.js"></script>
</#if>
<#if existFileUpload == 'true'>
<script src="../../dist/js/manytagfileupload.js"></script>
</#if>
<#if existReference == 'true'>
<script src="../../dist/js/popup.js"></script>
</#if>
<script>
	var pk = null;
	var pkColumnName = null;
	var values = null;
	<#if existRichClientText == 'true'>
	var umeditor = null;
	</#if>
	
	$(function () {
		init();
	});
	
	function init() {
		initUI();
		initData();
	}
	
	function initData() {
		var urltype = getQueryString('id');
		
		pk = unescape(urltype);
		pkColumnName = getQueryString('key');
		
		if (isModify()) {
			loadData(pkColumnName, pk);
		} else {
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table'>
					<#if oDBColumnInfo.isNullAbled()>
			getSelectOptionsFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}', true);
					<#else>
			getSelectOptionsFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
					</#if>
				<#else>
					<#if oDBColumnInfo.isNullAbled()>
			getSelectOptionsFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}', true);
					<#else>
			getSelectOptionsFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
					</#if>
				</#if>
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table'>
			getRadioFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
				<#else>
			getRadioFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
				</#if>
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table'>
			getCheckFromTable('', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
				<#else>
			getCheckFromDic('', '', '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
				</#if>
			<#else>
			</#if>
		</#list>
		}
	}
	
	function initUI() {
		$('#btn_return').click(function () {
			history.back();
		});
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
		
		umeditor = UM.getEditor('<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', {
			scaleEnabled: true,
			autoFloatEnabled: true,
			imageUrl: serverSrc + "/file.up",
			imagePath: "",
			imageCompressEnable: true,
			imageCompressSide: 1,
			imageCompressBorder: 100
		});
			<#break>
			</#if>
		</#list>
	}
	
	function isModify() {
		return (null != pk && "" != pk);
	}
	
	function loadData(pkColumnName, pk) {
		var query = {};
		<#if oDBTableInfo.primaryKey?size==0>query.id = pk<#else>query.${firstPrimaryKey} = pk</#if>;
		
		Base.ajax({
			url: serverSrc + "/${tableName?uncap_first}.do?_method=view",
			type: "post",
			dataType: "json",
			data: {
				_json: JSON.stringify(query)
			},
			mysuccess: function(val) {
				if (JSON.stringify(val) == "{}") {
					return;
				}
				setUIInfo(val);
			}
		});
	}
	
	function setUIInfo(val) {
		var data = val.row;
		
	<#list oDBTableInfo.columns as oDBColumnInfo>
	<#if oDBColumnInfo.isPrimaryKey()>
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>).attr("disabled",true);
	<#else>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
		<#else>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table'>
		getSelectOptionsFromTable(selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getSelectOptionsFromDic('', selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table'>
		getRadioFromTable(radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getRadioFromDic('', radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table'>
		getCheckFromTable(checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getCheckFromDic('', checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
		getUploadFileByUid(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', 0);
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='reference' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
		<#else>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
		umeditor.setContent(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		<#else>
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		</#if>
		</#if>
		</#if>
	</#if>
	</#list>
	}
</script>
</html>