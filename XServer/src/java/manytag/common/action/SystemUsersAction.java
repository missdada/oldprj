package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemUsersEntity;
import manytag.common.dao.entity.SystemUsersSearchEntity;
import manytag.common.service.ISystemUsersService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemUsers")
@Scope("prototype")
public class SystemUsersAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemUsersSearchEntity entity = this.paserJsonToEntity(SystemUsersSearchEntity.class);
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		List<SystemUsersEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		SystemUsersEntity entity = this.paserJsonToEntity(SystemUsersEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		SystemUsersEntity entity = this.paserJsonToEntity(SystemUsersEntity.class);
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
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		SystemUsersEntity entity = this.paserJsonToEntity(SystemUsersEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		List<SystemUsersEntity> entity = this.paserJsonToArray(SystemUsersEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void view() throws Exception {
		ISystemUsersService service = ApplicationContext.getBean("systemUsersService", ISystemUsersService.class);
		SystemUsersSearchEntity entity = this.paserJsonToEntity(SystemUsersSearchEntity.class);
		SystemUsersEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}