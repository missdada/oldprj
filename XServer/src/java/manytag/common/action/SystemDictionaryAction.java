package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemDictionaryEntity;
import manytag.common.dao.entity.SystemDictionarySearchEntity;
import manytag.common.dao.entity.SystemDictionaryValueEntity;
import manytag.common.service.ISystemDictionaryService;
import manytag.common.service.ISystemDictionaryValueService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemDictionary")
@Scope("prototype")
public class SystemDictionaryAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemDictionarySearchEntity entity = this.paserJsonToEntity(SystemDictionarySearchEntity.class);
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		List<SystemDictionaryEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void doSearch2() throws Exception {
		SystemDictionarySearchEntity entity = this.paserJsonToEntity(SystemDictionarySearchEntity.class);
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		List<SystemDictionaryEntity> searchList = service.searchAccurate(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		SystemDictionaryEntity entity = this.paserJsonToEntity(SystemDictionaryEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		SystemDictionaryEntity entity = this.paserJsonToEntity(SystemDictionaryEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);

		//first delete dictionary items
		ISystemDictionaryValueService oSystemDictionaryValueService = ApplicationContext.getBean("systemDictionaryValueService",
				ISystemDictionaryValueService.class);
		SystemDictionaryValueEntity oSystemDictionaryValueEntity = new SystemDictionaryValueEntity();
		oSystemDictionaryValueEntity.setDicCode(entity.getCode());
		oSystemDictionaryValueService.deleteAll(oSystemDictionaryValueEntity);
		for (SystemDictionaryValueEntity valueItem : entity.getDicItems()) {
			oSystemDictionaryValueService.insert(valueItem);
		}

		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	public void delete() throws Exception {
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		SystemDictionaryEntity entity = this.paserJsonToEntity(SystemDictionaryEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		List<SystemDictionaryEntity> entity = this.paserJsonToArray(SystemDictionaryEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		SystemDictionarySearchEntity entity = this.paserJsonToEntity(SystemDictionarySearchEntity.class);
		ISystemDictionaryService service = ApplicationContext.getBean("systemDictionaryService", ISystemDictionaryService.class);
		SystemDictionaryEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}