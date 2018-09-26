<?xml version="1.0" encoding="UTF-8"?>
<data id="${oDBTableInfo.tableName}" name="${oDBTableInfo.tableName}" table="${oDBTableInfo.tableName}">
<#list oDBTableInfo.columns as oDBColumnInfo>
	<field columnName="${oDBColumnInfo.columnName}" dataType="${oDBColumnInfo.dataType}" typeName="${oDBColumnInfo.typeName}" columnSize="${oDBColumnInfo.columnSize}" isNullAbled="${oDBColumnInfo.isNullAbled()}" defaultValue="${oDBColumnInfo.defaultValue}" isAutoIncrement="${oDBColumnInfo.isAutoIncrement()}" isPrimaryKey="${oDBColumnInfo.isPrimaryKey()}" remarks="${oDBColumnInfo.remarks}"/>
</#list>
</data>