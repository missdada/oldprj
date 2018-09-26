<?xml version="1.0" encoding="UTF-8"?>

<editDetail id="${oDBTableInfo.tableName}_edit">
	<edit>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<editField id="${oDBColumnInfo.columnName}" name="${oDBColumnInfo.columnName}" caption="<#if oDBColumnInfo.remarks??>${oDBColumnInfo.remarks}<#else>${oDBColumnInfo.columnName}</#if>" control="input" style="easyui-textbox" type="textbox" required="${(!oDBColumnInfo.isNullAbled())?c}" value="" width="120px" primaryKey="${(oDBColumnInfo.isPrimaryKey())?c}"<#if !oDBColumnInfo.isNullAbled()> prompt="${oDBColumnInfo.remarks}是必填项"><#else>></#if></editField>
	</#list>
	</edit>
	<functionButtons>
		<functionButton id="saveBtn" name="saveBtn" caption="保存" style="easyui-linkbutton" scope="all" iconCls="icon-save" para="${oDBTableInfo.tableName}"/>
		<functionButton id="cancelBtn" name="cancelBtn" caption="取消" style="easyui-linkbutton" scope="all" iconCls="icon-cancel" para="${oDBTableInfo.tableName}"/>
	</functionButtons>
</editDetail>
