package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.CTypeEntity;
import manytag.business.dao.entity.CTypeSearchEntity;
import manytag.business.service.ICTypeService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cType")
@Scope("prototype")
public class CTypeAction extends BaseBusinessAction {
	@SystemLog(module = "类型管理", methods = "检索")
	public void doSearch() throws Exception {
		CTypeSearchEntity entity = this.paserJsonToEntity(CTypeSearchEntity.class);
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		List<CTypeEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "类型管理", methods = "新建")
	public void insert() throws Exception {
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		CTypeEntity entity = this.paserJsonToEntity(CTypeEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "类型管理", methods = "修改")
	public void update() throws Exception {
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		CTypeEntity entity = this.paserJsonToEntity(CTypeEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "类型管理", methods = "删除")
	public void delete() throws Exception {
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		CTypeEntity entity = this.paserJsonToEntity(CTypeEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "类型管理", methods = "删除")
	public void deleteList() throws Exception {
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		List<CTypeEntity> entity = this.paserJsonToArray(CTypeEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
	
	@SystemLog(module = "类型管理", methods = "查看")
	public void view() throws Exception {
		CTypeSearchEntity entity = this.paserJsonToEntity(CTypeSearchEntity.class);
		ICTypeService service = ApplicationContext.getBean("cTypeService", ICTypeService.class);
		CTypeEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}