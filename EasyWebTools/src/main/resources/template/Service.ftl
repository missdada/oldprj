package ${servicePackageInfo?string};

<#import "/Common.ftl" as myFun>
<#assign tableName>
<@compress single_line=true>
<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>
</@compress>
</#assign>
<#assign existTree>
	<@compress single_line=true>
		<#assign result = false>
		<#if oDBTableInfo.extendInfo.t?? && oDBTableInfo.extendInfo.t?lower_case=='tree'>
			<#assign result = true />
		</#if>
		${result?c}
	</@compress>
</#assign>
<#assign entityName=tableName + "Entity">
<#assign searchEntityName = tableName + "SearchEntity">
<#assign entityNameLower = entityName?uncap_first>
<#assign searchEntityNameLower = searchEntityName?uncap_first>
import java.util.List;
import manytag.business.base.IBaseBusinessService;
import ${entityPackageInfo?string}.${entityName};
import ${entityPackageInfo?string}.${searchEntityName};

public interface I${tableName}Service extends IBaseBusinessService {
	public ${entityName} searchPK(${searchEntityName} ${searchEntityNameLower}) throws Exception;

	public List<${entityName}> search(${searchEntityName} ${searchEntityNameLower}) throws Exception;

	public long searchCount(${searchEntityName} ${searchEntityNameLower}) throws Exception;

	public int insert(${entityName} ${entityNameLower}) throws Exception;

	public void batchInsert(List<${entityName}> ${entityNameLower}) throws Exception;

	public int update(${entityName} ${entityNameLower}) throws Exception;

	public void batchUpdate(List<${entityName}> ${entityNameLower}) throws Exception;

	public int delete(${entityName} ${entityNameLower}) throws Exception;

	public int deleteList(List<${entityName}> list) throws Exception;
	<#if existTree == 'true'>

	public void saveTree(List<${entityName}> ${entityNameLower}) throws Exception;
	</#if>
}