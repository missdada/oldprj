<#--替换名字中的下划线，把名字转换为驼峰格式。例如，传入参数为name=get_max_value，返回结果为getMaxValue；如果第二个参数isFirstUpper=true，则返回GetMaxValue-->
<#macro name2HumpFormat name isFirstUpper=false>
<@compress single_line=true>
<#if name??>
	<#assign result = ''>
	<#list name?split("_") as s>
		<#if s != ''>
			<#assign temp = s>
			<#if isFirstUpper>
				<#assign temp = s?cap_first>
			</#if>
			<#if (s_index > 0)>
				<#assign temp = s?cap_first>
			</#if>
			<#assign result = result + temp>
		</#if>
	</#list>
	${result}
</#if>
</@compress>
</#macro>

<#--将数据库的类型转换为java类型-->
<#macro dbType2Java type realType="">
<@compress single_line=true>
<#if type=='LONG' || type=="BIGINT">
	Long
<#elseif type=='INT' || type=='INTEGER'>
	Integer
<#elseif type=='DOUBLE' || type=='DECIMAL' || type=='REAL' || type=='NUMERIC'>
	<#if realType?? && realType?lower_case == 'date'>
		Long
	<#else>
		Double
	</#if>
<#elseif type=='FLOAT' || type=='DECIMAL'>
	Float
<#elseif type=='DATE' || type=='DATETIME' || type=='TIMESTAMP' || type=='TIME' || type=='YEAR'>
	java.util.Date
<#else>
	String
</#if>
</@compress>
</#macro>
