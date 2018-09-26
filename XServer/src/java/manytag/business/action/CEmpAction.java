package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.CEmpEntity;
import manytag.business.dao.entity.CEmpSearchEntity;
import manytag.business.service.ICEmpService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cEmp")
@Scope("prototype")
public class CEmpAction extends BaseBusinessAction {
	@SystemLog(module = "雇员管理", methods = "检索")
	public void doSearch() throws Exception {
		CEmpSearchEntity entity = this.paserJsonToEntity(CEmpSearchEntity.class);
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		List<CEmpEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "雇员管理", methods = "新建")
	public void insert() throws Exception {
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		CEmpEntity entity = this.paserJsonToEntity(CEmpEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "雇员管理", methods = "修改")
	public void update() throws Exception {
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		CEmpEntity entity = this.paserJsonToEntity(CEmpEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "雇员管理", methods = "删除")
	public void delete() throws Exception {
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		CEmpEntity entity = this.paserJsonToEntity(CEmpEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "雇员管理", methods = "删除")
	public void deleteList() throws Exception {
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		List<CEmpEntity> entity = this.paserJsonToArray(CEmpEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
	
	@SystemLog(module = "雇员管理", methods = "查看")
	public void view() throws Exception {
		ICEmpService service = ApplicationContext.getBean("cEmpService", ICEmpService.class);
		CEmpSearchEntity entity = this.paserJsonToEntity(CEmpSearchEntity.class);
		CEmpEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}