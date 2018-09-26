<?xml version="1.0" encoding="UTF-8"?>
<#import "/Common.ftl" as myFun>
<searchDetail id="${oDBTableInfo.tableName}_search">
	<search display="true" labelWidth="100px" controlWidth="120px">
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#assign columnType>
		<@compress single_line=true>
		<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/>
		</@compress>
		</#assign>
		
		<#if columnType == 'java.util.Date' || (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case == 'date')>
			<searchField id="${oDBColumnInfo.columnName}__from" name="${oDBColumnInfo.columnName}__from" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>(&gt;=):" control="input" style="easyui-datebox" type="textbox" required="false" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}" operator="&gt;=" operatorOpen="" operatorClose=""></searchField>
			<searchField id="${oDBColumnInfo.columnName}__to" name="${oDBColumnInfo.columnName}__to" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>(&lt;=):" control="input" style="easyui-datebox" type="textbox" required="false" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}" operator="&lt;=" operatorOpen="" operatorClose=""></searchField>
		<#elseif columnType == 'Integer' || columnType == 'Long' || columnType == 'Double' || columnType == 'Float'>
			<searchField id="${oDBColumnInfo.columnName}__from" name="${oDBColumnInfo.columnName}__from" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>(&gt;=):" control="input" style="easyui-textbox" type="textbox" required="false" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}" operator="&gt;=" operatorOpen="" operatorClose=""></searchField>
			<searchField id="${oDBColumnInfo.columnName}__to" name="${oDBColumnInfo.columnName}__to" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>(&lt;=):" control="input" style="easyui-textbox" type="textbox" required="false" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}" operator="&lt;=" operatorOpen="" operatorClose=""></searchField>
		<#else>
			<searchField id="${oDBColumnInfo.columnName}" name="${oDBColumnInfo.columnName}" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>:" control="input" style="easyui-textbox" type="textbox" required="false" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}" operator="like" operatorOpen="%" operatorClose="%"></searchField>
		</#if>
		</#list>
	</search>
	
	<toolBar display="true">
		<toolButton id="addBtn" name="addBtn" caption="新建" style="easyui-linkbutton" scope="all" iconCls="icon-add" para="${oDBTableInfo.tableName}" onclick="doNew();"></toolButton>
		<toolButton id="editBtn" name="editBtn" caption="修改" style="easyui-linkbutton" scope="single" iconCls="icon-edit" para="${oDBTableInfo.tableName}" onclick="doEdit();"></toolButton>
		<toolButton id="deleteBtn" name="deleteBtn" caption="删除" style="easyui-linkbutton" scope="single" iconCls="icon-remove" para="${oDBTableInfo.tableName}" onclick="doDelete();"></toolButton>
		<toolButton id="viewBtn" name="viewBtn" caption="查看" style="easyui-linkbutton" scope="single" iconCls="icon-tip" para="${oDBTableInfo.tableName}" onclick="doView();"></toolButton>
		<toolButton id="confirmBtn" name="confirmBtn" caption="确定" style="easyui-linkbutton" scope="single" iconCls="icon-ok" para="${oDBTableInfo.tableName}" onclick="doConfirm();" display="false"></toolButton>
		<toolButton id="exportExcelBtn" name="exportExcelBtn" caption="导出" style="easyui-linkbutton" scope="all" iconCls="icon-reload" para="${oDBTableInfo.tableName}" onclick="doExportExcel();" display="false"></toolButton>
	</toolBar>
	
	<listView id="${oDBTableInfo.tableName}_listView" tableid="${oDBTableInfo.tableName}" width="100%" height="245px" checkbox="true" singleSelect="false" fitColumns="true" pageSize="20" pagination="true" showFooter="false" rownumbers="true">
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<column id="${oDBColumnInfo.columnName}" name="${oDBColumnInfo.columnName}" dbColumnName="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>" width="120px" align="left" valign="center" sortable="true" display="true" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}"></column>
		</#list>
	</listView>
</searchDetail>
