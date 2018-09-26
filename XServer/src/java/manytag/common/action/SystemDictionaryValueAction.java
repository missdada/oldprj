package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemDictionaryValueEntity;
import manytag.common.dao.entity.SystemDictionaryValueSearchEntity;
import manytag.common.service.ISystemDictionaryValueService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemDictionaryValue")
@Scope("prototype")
public class SystemDictionaryValueAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemDictionaryValueSearchEntity entity = this.paserJsonToEntity(SystemDictionaryValueSearchEntity.class);
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		List<SystemDictionaryValueEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		SystemDictionaryValueEntity entity = this.paserJsonToEntity(SystemDictionaryValueEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		SystemDictionaryValueEntity entity = this.paserJsonToEntity(SystemDictionaryValueEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	public void delete() throws Exception {
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		SystemDictionaryValueEntity entity = this.paserJsonToEntity(SystemDictionaryValueEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		List<SystemDictionaryValueEntity> entity = this.paserJsonToArray(SystemDictionaryValueEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		SystemDictionaryValueSearchEntity entity = this.paserJsonToEntity(SystemDictionaryValueSearchEntity.class);
		ISystemDictionaryValueService service = ApplicationContext.getBean("systemDictionaryValueService", ISystemDictionaryValueService.class);
		SystemDictionaryValueEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}