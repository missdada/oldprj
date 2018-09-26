<#import "submenu.ftl" as subm>
<#if menulist??>
<#list menulist as menu>
	<@subm.createmenu menu/>
</#list>
</#if>