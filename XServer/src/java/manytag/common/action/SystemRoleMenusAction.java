package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemRoleMenusEntity;
import manytag.common.dao.entity.SystemRoleMenusSearchEntity;
import manytag.common.service.ISystemRoleMenusService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemRoleMenus")
@Scope("prototype")
public class SystemRoleMenusAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemRoleMenusSearchEntity entity = this.paserJsonToEntity(SystemRoleMenusSearchEntity.class);
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
		List<SystemRoleMenusEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
		SystemRoleMenusEntity entity = this.paserJsonToEntity(SystemRoleMenusEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
		SystemRoleMenusEntity entity = this.paserJsonToEntity(SystemRoleMenusEntity.class);
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
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
		SystemRoleMenusEntity entity = this.paserJsonToEntity(SystemRoleMenusEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRoleMenusService", ISystemRoleMenusService.class);
		List<SystemRoleMenusEntity> entity = this.paserJsonToArray(SystemRoleMenusEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		ISystemRoleMenusService service = ApplicationContext.getBean("systemRolemenusService", ISystemRoleMenusService.class);
		SystemRoleMenusSearchEntity entity = this.paserJsonToEntity(SystemRoleMenusSearchEntity.class);
		SystemRoleMenusEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}