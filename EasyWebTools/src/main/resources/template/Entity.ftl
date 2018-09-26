package ${entityPackageInfo?string};

import manytag.business.base.BaseBusicessEntity;
import manytag.framework.util.DateUtil;
<#import "/Common.ftl" as myFun>

public class <@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>Entity extends BaseBusicessEntity {
	<#list oDBTableInfo.columns as oDBColumnInfo>
	private <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	<#if (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date')
	|| (oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true')
	|| oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP"
	|| (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file')>
	private String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	</#if>
	</#list>
	<#list oDBTableInfo.columns as oDBColumnInfo>
	<#assign newLineCnt = 0>
	<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'>
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null) {
			this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
		}
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str != null && !"".equals(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str)) {
			this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
		}
	}
	<#elseif oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null) {
			this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>.getTime());
		}
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		if (<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str != null && !"".equals(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str)) {
			this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = new java.util.Date(DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str));
		}
	}
	<#elseif (oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true')
		|| (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file')>
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}
	<#else>
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}
	</#if>
	</#list>
}