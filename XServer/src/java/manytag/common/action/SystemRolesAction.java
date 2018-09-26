package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemRolesEntity;
import manytag.common.dao.entity.SystemRolesSearchEntity;
import manytag.common.service.ISystemRolesService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemRoles")
@Scope("prototype")
public class SystemRolesAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemRolesSearchEntity entity = this.paserJsonToEntity(SystemRolesSearchEntity.class);
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		List<SystemRolesEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		SystemRolesEntity entity = this.paserJsonToEntity(SystemRolesEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		SystemRolesEntity entity = this.paserJsonToEntity(SystemRolesEntity.class);
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
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		SystemRolesEntity entity = this.paserJsonToEntity(SystemRolesEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		List<SystemRolesEntity> entity = this.paserJsonToArray(SystemRolesEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		ISystemRolesService service = ApplicationContext.getBean("systemRolesService", ISystemRolesService.class);
		SystemRolesSearchEntity entity = this.paserJsonToEntity(SystemRolesSearchEntity.class);
		SystemRolesEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}