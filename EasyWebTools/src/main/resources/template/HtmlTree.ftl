<!DOCTYPE html>
<html>
<head lang="zh-CN">
	<meta charset="UTF-8">
	<title>树形控件</title>
	<link rel="stylesheet" href="../../bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="../../bootstrap/css/bootstrap-theme.min.css" />
	<link rel="stylesheet" href="../../bootstrap-table/bootstrap-table.css" />
	<link rel="stylesheet" href="../../font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="../../dist/css/AdminLTE.css">
	<link rel="stylesheet" href="../../dist/css/skins/_all-skins.min.css">
	<link rel="stylesheet" href="../../plugins/ztree/zTreeStyle.css">
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
</head>
<body>
	<div class="wrapper">
		<div>
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
					<div class="col-xs-3">
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title" id="crrentTreeName">${tableNameCh}</h3>
							</div>
							<div class="box-body">
								<ul class="ztree" id="zTree"></ul>
							</div>
						</div>
					</div>
					<div class="col-xs-9">
						<div class="box box-danger">
							<div class="box-header with-border">
								<h3 class="box-title">设置</h3>
							</div>
							<form class="form-horizontal">
								<div class="box-body">
									<#list oDBTableInfo.columns as oDBColumnInfo>
									<#if oDBColumnInfo.extendInfo.hide?? && oDBColumnInfo.extendInfo.hide?lower_case=='true'>
									<#else>
									<div class="form-group <#if oDBColumnInfo.isPrimaryKey()></#if><#if oDBTableInfo.extendInfo.s == oDBColumnInfo.columnName>hide</#if>">
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
												<div id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>_preview0" onclick="xian(this)" class="yi">
													<img id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>_imghead0" border="0" src=""/>
													<input type="file" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" onchange="previewImage(this,0);addimgfile(this,0);"/>
												</div>
												<div id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" class="add"></div>
											</div>
										<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='reference'>
											<div class="form-group " disabled="disabled">
												<div class="col-sm-10">
													<input type="text" class="form-control hide" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>">
													<input disabled="disabled" type="text" class="form-control" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str">
												</div>
												<div class="col-sm-2">
													<a href="#popup_content" data-toggle="modal" class="btn btn-primary btn-large" onclick='popupShow("<@myFun.name2HumpFormat name=oDBColumnInfo.extendInfo.referenceTable isFirstUpper=true/>","<@myFun.name2HumpFormat name=oDBColumnInfo.extendInfo.referenceTable isFirstUpper=true/>.html", <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>PopupCallback);'>参照</a>
												</div>
											</div>
										<#else>
											<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select'>
											<input type="hidden" class="form-control" id="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="data_<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>">
											<select class="form-control select2" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>"
												style="width: 100%;" disabled="disabled">
											</select>
											<#else>
											<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
											<textarea class="form-control" rows="10" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>" style="width:100%;height:500px"></textarea>
											<#else>
												<#if oDBColumnInfo.extendInfo.multi?? && oDBColumnInfo.extendInfo.multi?lower_case=='true'>
											<textarea class="form-control" rows="3" id="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" name="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>" disabled="disabled" placeholder="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>"></textarea>
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
									<input type="hidden" class="form-control" id="node_tid" placeholder="列tid" disabled>
								</div>
								<div class="box-footer">
									<div class="box-tools col-sm-offset-2">
										<a class="btn btn-default" href="javascript:void(0)" onclick="editTreeNode()"><i class="fa fa-edit"></i>编辑</a>
										<a class="btn btn-default" href="javascript:void(0)" onclick="addNode()"><i class="fa fa-file-o"></i>添加</a>
										<a class="btn btn-danger" href="javascript:void(0)" onclick="removeNode()"><i class="fa fa-trash"></i>删除</a>
											&nbsp;|&nbsp;
										<a class="btn btn-default" href="javascript:void(0)" onclick="save()"><i class="fa fa-save"></i>保存</a>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</section>
		</div>
	</div>
</body>

<script type="text/javascript" src="../../plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="../../bootstrap/js/bootstrap.min.js"></script>
<script src="../../bootstrap-table/bootstrap-table.js"></script>
<script src="../../bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="../../plugins/jQuery/jquery.bootstrap.min.js"></script>
<script src="../../plugins/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="../../dist/js/command.js"></script>
<script src="../../dist/js/message.js"></script>
<script src="../../dist/js/app.min.js"></script>
<script>
	$(function() {
		init();
	});
	
	function init() {
		initData();
		initUI();
	}
	
	function initData() {
		getTree();
	}
	
	function initUI() {
	}
	
	window.actionEvents = {
		'click .resend': function(e, value, row, index) {
			alert('重新发送该, row: ' + JSON.stringify(row));
			//console.log(value, row, index);
		}
	};
	
	function getTree() {
		var setting = {
			edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.uidColumn/>",
					pIdKey: "<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.p/>"
				},
				key: {
					checked: "checked",
					name: "<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.n/>"
				}
			},
			callback: {
				onClick: zTreeOnClick,
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				onDrop: zTreeOnDrop
			}
		};
		
		Base.ajax({
			url: serverSrc + "/${tableName?uncap_first}.do?_method=doSearch",
			type: 'post',
			dataType: 'json',
			mysuccess: function(data) {
//				console.log(data);
				var zNodes = data.rows;
				var treeObject = $.fn.zTree.init($("#zTree"), setting, zNodes); //生成树形结构
				treeObject.expandAll(true);
			},
			error: function(msg) {
				alert("加载异常");
			}
		});
	}
	
	function editTreeNode() {
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBTableInfo.extendInfo.p == oDBColumnInfo.columnName>
		<#else>
		$('#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>').removeAttr('disabled');
		</#if>
	</#list>
	}
	
	function disableTreeNode() {
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBTableInfo.extendInfo.p == oDBColumnInfo.columnName>
		<#else>
		$('#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>').attr({disabled:'disabled'});
		</#if>
	</#list>
	}
	
	function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
		doSave();
	}
	
	function beforeDrag(treeId, treeNodes) {
		return true;
	}
	
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		return true;
	}
	
	function zTreeOnClick(event, treeId, treeNode) {
		disableTreeNode();
//		console.log(treeNode);
		var treeObject = $.fn.zTree.getZTreeObj("zTree");
		var tId = treeNode.parentTId;
		var node_parent = "";
		if (treeObject.getNodeByTId(tId)) {
			node_parent = treeObject.getNodeByTId(tId).name;
		}
		
		$('#node_tid').val(treeNode.tId);
		
		var data = treeNode;
	<#list oDBTableInfo.columns as oDBColumnInfo>
	<#if oDBTableInfo.extendInfo.p == oDBColumnInfo.columnName>
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val(node_parent);
	<#else>
	<#if oDBColumnInfo.isPrimaryKey()>
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
	<#else>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
		$("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
		<#else>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='select' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table' >
		getSelectOptionsFromTable(selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getSelectOptionsFromDic('', selectedValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table' >
		getRadioFromTable(radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getRadioFromDic('', radioValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox' && oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>
		var checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			<#if oDBColumnInfo.extendInfo.referenceType=='table' >
		getCheckFromTable(checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.referenceTable}', '${oDBColumnInfo.extendInfo.referenceKey}', '${oDBColumnInfo.extendInfo.referenceName}');
			<#else>
		getCheckFromDic('', checkValue<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>', '${oDBColumnInfo.extendInfo.condition}');
			</#if>
		<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
		getUploadFileByUid(data.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>, '<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>');
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
	</#if>
	</#list>
	};
	
	function setNode() {
		var node_tid = $('#node_tid').val();
		var treeObject = $.fn.zTree.getZTreeObj("zTree");
		var node = treeObject.getNodeByTId(node_tid);
		if (node) {
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBTableInfo.extendInfo.p == oDBColumnInfo.columnName>
			<#else>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
			node.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = $("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str").val();
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='radio'>
			node.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = $("input:radio[name='<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>']:checked").val();
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox'>
			node.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = getCheckValue("<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>");
			<#else>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='richclienttext'>
			node.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = umeditor.getContent();
			<#else>
			node.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = $("#<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>").val();
			</#if>
			</#if>
			</#if>
		</#list>
		}
		treeObject.updateNode(node);
	}
	
	function addNode() {
		var treeObject = $.fn.zTree.getZTreeObj("zTree");
		var parentNode = treeObject.getSelectedNodes();
//		if (parentNode.length == 0) {
//			alert('请选择一个节点');
//			return;
//		}
		var pnode = parentNode[0];
		if (pnode) {
			var cnode = {<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.uidColumn/>:pnode.<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.uidColumn/>+"_1", <@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.p/>:pnode.<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.uidColumn/>, <@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.n/>:"new node"}
			treeObject.addNodes(pnode, cnode);
		} else {
			var cnode = {<@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.uidColumn/>:"root_1", <@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.p/>:'*', <@myFun.name2HumpFormat name=oDBTableInfo.extendInfo.n/>:"new node"}
			treeObject.addNodes(pnode, cnode);
		}
	}
	
	function removeNode() {
		var treeObject = $.fn.zTree.getZTreeObj("zTree");
		var parentNode = treeObject.getSelectedNodes();
		if (parentNode.length == 0) {
			alert('请选择一个节点');
			return;
		}
		var pnode = parentNode[0];
		if (pnode) {
			if (confirm("确定要删除此项吗？")) {
				treeObject.removeNode(pnode);
				doSave();
			}
		}
	}
	
	function save() {
		setNode();
		doSave();
	}
	
	var savedNode = [];
	var nodesort = 0;
	function doSave() {
		var treeObject = $.fn.zTree.getZTreeObj("zTree");
		var nodes = treeObject.getNodes();
		if (nodes) {
			savedNode = [];
			nodesort = 0;
			for (var i = 0; i < nodes.length; i++) {
				var node = nodes[i];
				saveThisNode(node);
			}
			//console.log(savedNode);
			
			var param = {};
			param.treedata = savedNode;
			Base.ajax({
				url: serverSrc + "/${tableName?uncap_first}.do?_method=saveTree",
				type: 'post',
				dataType: 'json',
				data: {
					_json: JSON.stringify(param)
				},
				mysuccess: function(resp) {
					if (resp && resp.message) {
						var msgInfo = resp.message;
						//console.log(msgInfo);
						if (msgInfo.messageId == 'I0003') {
//							$.messager.alert("提示", msgInfo.message);
							window.location.href = "${tableName}.html";
							getTree();
						} else {
							$.messager.alert("提示", msgInfo.message);
						}
					}
				}
			});
		}
	}
	
	function saveThisNode(tnode) {
		if (tnode) {
			var sm = {};
			
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBTableInfo.extendInfo.s == oDBColumnInfo.columnName>
			sm.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = nodesort;
			<#else>
			sm.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = tnode.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
			</#if>
		</#list>
			
			nodesort += 1;
			savedNode.push(sm);
			if (tnode.children) {
				for (var y = 0; y < tnode.children.length; y++) {
					saveThisNode(tnode.children[y]);
				}
			}
		}
	}
</script>
</html>