package ${actionPackageInfo?string};

<#import "/Common.ftl" as myFun>
<#assign tableName>
<@compress single_line=true>
<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>
</@compress>
</#assign>
<#assign entityName=tableName + "Entity">
<#assign searchEntityName = tableName + "SearchEntity">
<#assign serviceName = tableName?uncap_first + "Service">
<#assign serviceInterface = "I" + tableName + "Service">
<#assign existUid>
<@compress single_line=true>
	<#assign result = false>
	<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.columnName == 'uid'>
			<#assign result = true />
		</#if>
	</#list>
	${result?c}
</@compress>
</#assign>
<#assign tableNameCh>
	<@compress single_line=true>
		<#assign result = oDBTableInfo.tableName>
		<#if oDBTableInfo.remarks??>
			<#assign result = oDBTableInfo.remarks />
		</#if>
		${result}
	</@compress>
</#assign>
<#assign existFileUpload>
	<@compress single_line=true>
		<#assign result = false>
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
				<#assign result = true />
			</#if>
		</#list>
		${result?c}
	</@compress>
</#assign>
<#assign existCheckbox>
	<@compress single_line=true>
		<#assign result = false>
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox'>
				<#assign result = true />
			</#if>
		</#list>
		${result?c}
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
<#if existTree == 'true'>
import java.util.HashMap;
</#if>
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import ${entityPackageInfo?string}.${entityName};
import ${entityPackageInfo?string}.${searchEntityName};
import ${servicePackageInfo?string}.${serviceInterface};
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("${tableName?uncap_first}")
@Scope("prototype")
public class ${tableName}Action extends BaseBusinessAction {
	@SystemLog(module = "${tableNameCh}", methods = "检索")
	public void doSearch() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		${searchEntityName} entity = this.paserJsonToEntity(${searchEntityName}.class);
		List<${entityName}> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		<#if existFileUpload == 'true' || existCheckbox == 'true'>
		for (${entityName} oneEntity : searchList) {
			<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='file'>
			oneEntity.set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(getImageUrls(oneEntity.get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>()));
			<#elseif oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='checkbox'>
				<#if oDBColumnInfo.extendInfo.referenceType=='table' >
			oneEntity.set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(getNameFromTable("${oDBColumnInfo.extendInfo.referenceTable}", "${oDBColumnInfo.extendInfo.referenceKey}", "${oDBColumnInfo.extendInfo.referenceName}", oneEntity.get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>()));
				<#else>
			oneEntity.set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>Str(getNameFromDic("${oDBColumnInfo.extendInfo.condition}", oneEntity.get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>()));
				</#if>
			<#else>
			</#if>
			</#list>
		}
		</#if>
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "${tableNameCh}", methods = "新建")
	public void insert() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		${entityName} entity = this.paserJsonToEntity(${entityName}.class);
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.isOnly?? && oDBColumnInfo.extendInfo.isOnly?lower_case=='true'>

		${searchEntityName} searchEntity = new ${searchEntityName}();
		searchEntity.set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(entity.get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>());
		List<CWarehouseInfoEntity> entitys = service.searchAccurate(searchEntity);
		if (entitys != null && !entitys.isEmpty()) {
			JsonResponse result = new JsonResponse();
			result.put("count", 0);
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0001", "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>重复"));
			this.setResult(result);
			return;
		}
		</#if>
		</#list>

		<#if existUid='true'>
		this.setUidDateTime(entity);
		<#else>
		this.setUidDateTime(entity, false, true, true);
		</#if>
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "${tableNameCh}", methods = "修改")
	public void update() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		${entityName} entity = this.paserJsonToEntity(${entityName}.class);
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.isOnly?? && oDBColumnInfo.extendInfo.isOnly?lower_case=='true'>

		${searchEntityName} searchEntity = new ${searchEntityName}();
		searchEntity.set<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>(entity.get<@myFun.name2HumpFormat name=oDBColumnInfo.columnName isFirstUpper=true/>());
		List<CWarehouseInfoEntity> entitys = service.searchAccurate(searchEntity);
		if (entitys != null && !entitys.isEmpty()) {
			if(entitys.size() > 1<#list oDBTableInfo.primaryKey?keys as key> || !entitys.get(0).get<@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName isFirstUpper=true/>().equals(entity.get<@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName isFirstUpper=true/>())</#list>) {
				JsonResponse result = new JsonResponse();
				result.put("count", 0);
				result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0001", "<#if oDBColumnInfo.remarks?? && oDBColumnInfo.remarks != ''>${oDBColumnInfo.remarks}<#else><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/></#if>重复"));
				this.setResult(result);
				return;
			}
		}
		</#if>
		</#list>

		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "${tableNameCh}", methods = "删除")
	public void delete() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		${entityName} entity = this.paserJsonToEntity(${entityName}.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "${tableNameCh}", methods = "删除")
	public void deleteList() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		List<${entityName}> entity = this.paserJsonToArray(${entityName}[].class);
		int count = service.deleteList(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
	<#if existTree == 'true'>

	@SuppressWarnings("unchecked")
	@SystemLog(module = "${tableNameCh}", methods = "保存树")
	public void saveTree() throws Exception {
		JsonResponse result = new JsonResponse();
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		HashMap<String, List<${entityName}>> params = this.paserJsonToEntity(HashMap.class);
		List<${entityName}> entity = params.get("treedata");
		if (entity != null) {
			try {
				service.saveTree(entity);
				result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0003", new String[] {}));
			} catch (Exception e) {
				result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
				e.printStackTrace();
			}
		}
		this.setResult(result);
	}
	</#if>

	@SystemLog(module = "${tableNameCh}", methods = "查看")
	public void view() throws Exception {
		${serviceInterface} service = ApplicationContext.getBean("${serviceName}", ${serviceInterface}.class);
		${searchEntityName} entity = this.paserJsonToEntity(${searchEntityName}.class);
		${entityName} entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}