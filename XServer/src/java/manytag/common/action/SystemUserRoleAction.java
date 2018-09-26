package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemUserRoleEntity;
import manytag.common.dao.entity.SystemUserRoleSearchEntity;
import manytag.common.service.ISystemUserRoleService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemUserRole")
@Scope("prototype")
public class SystemUserRoleAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemUserRoleSearchEntity entity = this.paserJsonToEntity(SystemUserRoleSearchEntity.class);
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		List<SystemUserRoleEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		SystemUserRoleEntity entity = this.paserJsonToEntity(SystemUserRoleEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		SystemUserRoleEntity entity = this.paserJsonToEntity(SystemUserRoleEntity.class);
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
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		SystemUserRoleEntity entity = this.paserJsonToEntity(SystemUserRoleEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		List<SystemUserRoleEntity> entity = this.paserJsonToArray(SystemUserRoleEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void selectAccurate() throws Exception {
		ISystemUserRoleService service = ApplicationContext.getBean("systemUserRoleService", ISystemUserRoleService.class);
		SystemUserRoleSearchEntity entity = this.paserJsonToEntity(SystemUserRoleSearchEntity.class);
		List<SystemUserRoleEntity> entitys = service.selectAccurate(entity);
		JsonResponse result = new JsonResponse();
		result.put("rows", entitys);
		this.setResult(result);
	}
}