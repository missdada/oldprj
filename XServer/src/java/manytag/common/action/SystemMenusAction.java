package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemMenusEntity;
import manytag.common.dao.entity.SystemMenusSearchEntity;
import manytag.common.service.ISystemMenusService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemMenus")
@Scope("prototype")
public class SystemMenusAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemMenusSearchEntity entity = this.paserJsonToEntity(SystemMenusSearchEntity.class);
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		List<SystemMenusEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		SystemMenusEntity entity = this.paserJsonToEntity(SystemMenusEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		SystemMenusEntity entity = this.paserJsonToEntity(SystemMenusEntity.class);
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
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		SystemMenusEntity entity = this.paserJsonToEntity(SystemMenusEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		List<SystemMenusEntity> entity = this.paserJsonToArray(SystemMenusEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		SystemMenusSearchEntity entity = this.paserJsonToEntity(SystemMenusSearchEntity.class);
		ISystemMenusService service = ApplicationContext.getBean("systemMenusService", ISystemMenusService.class);
		SystemMenusEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}