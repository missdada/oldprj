<#if SESSION_USER??>
	<#if SESSION_USER.menus??>
		<#if SESSION_USER.menus?contains("btn_add")>
			<button class="btn btn-default" id="btn_add">
				<i class="fa fa-file-o"></i><span data-zh="新建" data-es="Transformar">新建</span>
			</button>
		</#if>
		<#if SESSION_USER.menus?contains("btn_modify")>
			<button class="btn btn-default" id="btn_modify">
				<i class="fa fa-edit"></i><span data-zh="转换" data-es="Transformar">修改</span>
			</button>
		</#if>
		<#if SESSION_USER.menus?contains("btn_view")>
			<button class="btn btn-default" id="btn_view">
				<i class="fa fa-eye"></i><span data-zh="查看" data-es="Transformar">查看</span>
			</button>
		</#if>
		<#if SESSION_USER.menus?contains("btn_delete")>
			<button class="btn btn-default" id="btn_delete">
				<i class="fa fa-trash"></i><span data-zh="删除" data-es="Destruir">删除</span>
			</button>
		</#if>
	</#if>
</#if>
<script>
<#if SESSION_USER.menus?contains("btn_delete")>
$('#btn_delete').click(function (){ doDelete(getSelectedItems()); });
</#if>
<#if SESSION_USER.menus?contains("btn_modify")>
$('#btn_modify').click(function (){ doModify(getSelectedItems()); });
</#if>
<#if SESSION_USER.menus?contains("btn_add")>
$('#btn_add').click(function (){ doAdd(); });
</#if>
<#if SESSION_USER.menus?contains("btn_view")>
$('#btn_view').click(function (){ doView(getSelectedItems()); });
</#if>
</script>