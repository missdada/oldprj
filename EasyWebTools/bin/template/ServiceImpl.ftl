package ${serviceImplPackageInfo?string};

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
<#assign mapperNamespace = daoPackageInfo?string + "." + tableName + "Dao">
<#assign tableDao>
	<@compress single_line=true>
		<@myFun.name2HumpFormat name=oDBTableInfo.tableName + "Dao" isFirstUpper=false/>
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
import java.util.List;

import javax.annotation.Resource;

<#if existTree == 'true'>
import org.apache.ibatis.session.ExecutorType;
</#if>
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import ${entityPackageInfo?string}.${entityName};
import ${entityPackageInfo?string}.${searchEntityName};
import ${servicePackageInfo?string}.I${tableName}Service;
import ${daoPackageInfo}.${tableName}Dao;

@Service("${tableName?uncap_first}Service")
@Transactional
public class ${tableName}Service extends BaseBusinessService implements I${tableName}Service {
	@Resource
	private ${tableName}Dao ${tableDao};

	public ${entityName} searchPK(${searchEntityName} ${searchEntityNameLower}) throws Exception {
		return ${tableDao}.selectPK(${searchEntityNameLower});
	}

	public List<${entityName}> search(${searchEntityName} ${searchEntityNameLower}) throws Exception {
		return ${tableDao}.select(${searchEntityNameLower});
	}

	public long searchCount(${searchEntityName} ${searchEntityNameLower}) throws Exception {
		return ${tableDao}.selectCount(${searchEntityNameLower});
	}

	public int insert(${entityName} ${entityNameLower}) throws Exception {
		return ${tableDao}.insert(${entityNameLower});
	}

	public void batchInsert(List<${entityName}> ${entityNameLower}) throws Exception {
		${tableDao}.batchInsert(${entityNameLower});
	}

	public int update(${entityName} ${entityNameLower}) throws Exception {
		return ${tableDao}.update(${entityNameLower});
	}

	public void batchUpdate(List<${entityName}> ${entityNameLower}) throws Exception {
		${tableDao}.batchUpdate(${entityNameLower});
	}

	public int delete(${entityName} ${entityNameLower}) throws Exception {
		return ${tableDao}.delete(${entityNameLower});
	}

	public int deleteList(List<${entityName}> list) throws Exception {
		return ${tableDao}.deleteList(list);
	}
	<#if existTree == 'true'>

	public void saveTree(List<${entityName}> list) throws Exception {
		try {
			${tableDao}.deleteAll();

			if (null != list) {
				for (int i = 0; i < list.size(); i++) {
					${tableDao}.insert(list.get(i));
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	</#if>
}