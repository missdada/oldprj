package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.COrgtypeEntity;
import manytag.business.dao.entity.COrgtypeSearchEntity;
import manytag.business.service.ICOrgtypeService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cOrgtype")
@Scope("prototype")
public class COrgtypeAction extends BaseBusinessAction {
	@SystemLog(module = "", methods = "检索")
	public void doSearch() throws Exception {
		COrgtypeSearchEntity entity = this.paserJsonToEntity(COrgtypeSearchEntity.class);
		ICOrgtypeService service = ApplicationContext.getBean("cOrgtypeService", ICOrgtypeService.class);
		List<COrgtypeEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "", methods = "新建")
	public void insert() throws Exception {
		ICOrgtypeService service = ApplicationContext.getBean("cOrgtypeService", ICOrgtypeService.class);
		COrgtypeEntity entity = this.paserJsonToEntity(COrgtypeEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "", methods = "修改")
	public void update() throws Exception {
		ICOrgtypeService service = ApplicationContext.getBean("cOrgtypeService", ICOrgtypeService.class);
		COrgtypeEntity entity = this.paserJsonToEntity(COrgtypeEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "", methods = "删除")
	public void delete() throws Exception {
		ICOrgtypeService service = ApplicationContext.getBean("cOrgtypeService", ICOrgtypeService.class);
		COrgtypeEntity entity = this.paserJsonToEntity(COrgtypeEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "", methods = "删除")
	public void deleteList() throws Exception {
		ICOrgtypeService service = ApplicationContext.getBean("cOrgtypeService", ICOrgtypeService.class);
		List<COrgtypeEntity> entity = this.paserJsonToArray(COrgtypeEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
}