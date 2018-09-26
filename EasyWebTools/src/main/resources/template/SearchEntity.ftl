package ${entityPackageInfo?string};

import manytag.business.base.BaseBusicessSearchEntity;
import manytag.framework.util.DateUtil;
<#import "/Common.ftl" as myFun>

public class <@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>SearchEntity extends BaseBusicessSearchEntity {
	<#list oDBTableInfo.columns as oDBColumnInfo>
	private <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	<#if (oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date') || oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP" || oDBColumnInfo.typeName == "DOUBLE" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
	private String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	private <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	private String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
	private <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	private String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
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
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>);
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin);
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd);
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr);
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
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>.getTime());
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = new java.util.Date(DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str));
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin.getTime());
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = new java.util.Date(DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr));
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = DateUtil.longToDateStr(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd.getTime());
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = new java.util.Date(DateUtil.dateStrToLong(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr));
	}
	<#elseif oDBColumnInfo.typeName == "DOUBLE">
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = Double.parseDouble(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = Double.parseDouble(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = Double.parseDouble(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr);
	}
	<#elseif oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
	<#if newLineCnt == 0>

	<#assign newLineCnt = newLineCnt + 1>
	</#if>
	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> = Integer.parseInt(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>Str);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBegin(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchBeginStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin = Integer.parseInt(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBeginStr);
	}

	public <@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEnd(<@myFun.dbType2Java type=oDBColumnInfo.typeName realType=oDBColumnInfo.extendInfo.realTypeName/> <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = "" + <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd;
	}

	public String get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr() {
		return <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
	}

	public void set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>SearchEndStr(String <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr) {
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr = <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr;
		this.<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd = Integer.parseInt(<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEndStr);
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