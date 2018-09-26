package ${daoPackageInfo?string};

<#import "/Common.ftl" as myFun>
<#assign tableName>
<@compress single_line=true>
<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>
</@compress>
</#assign>
<#assign entityName=tableName + "Entity">
<#assign searchEntityName = tableName + "SearchEntity">
<#assign entityNameLower = entityName?uncap_first>
<#assign searchEntityNameLower = searchEntityName?uncap_first>
<#assign existTree>
	<@compress single_line=true>
		<#assign result = false>
		<#if oDBTableInfo.extendInfo.t?? && oDBTableInfo.extendInfo.t?lower_case=='tree'>
			<#assign result = true />
		</#if>
		${result?c}
	</@compress>
</#assign>
import java.util.List;

import cn.manytag.manytagUtil.annotation.MybatisDao;
import ${entityPackageInfo?string}.${entityName};
import ${entityPackageInfo?string}.${searchEntityName};

@MybatisDao
public interface ${tableName}Dao {
	public ${entityName} selectPK(${searchEntityName} ${searchEntityNameLower});

	public List<${entityName}> select(${searchEntityName} ${searchEntityNameLower});

	public long selectCount(${searchEntityName} ${searchEntityNameLower});

	public int insert(${entityName} ${entityNameLower});

	public void batchInsert(List<${entityName}> ${entityNameLower});

	public int update(${entityName} ${entityNameLower});

	public void batchUpdate(List<${entityName}> ${entityNameLower});

	public int delete(${entityName} ${entityNameLower});

	public int deleteList(List<${entityName}> list);
}