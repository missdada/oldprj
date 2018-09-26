package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.CManufacturerInfoEntity;
import manytag.business.dao.entity.CManufacturerInfoSearchEntity;
import manytag.business.service.ICManufacturerInfoService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cManufacturerInfo")
@Scope("prototype")
public class CManufacturerInfoAction extends BaseBusinessAction {
	@SystemLog(module = "厂商管理", methods = "检索")
	public void doSearch() throws Exception {
		CManufacturerInfoSearchEntity entity = this.paserJsonToEntity(CManufacturerInfoSearchEntity.class);
		ICManufacturerInfoService service = ApplicationContext.getBean("cManufacturerInfoService", ICManufacturerInfoService.class);
		List<CManufacturerInfoEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "厂商管理", methods = "新建")
	public void insert() throws Exception {
		ICManufacturerInfoService service = ApplicationContext.getBean("cManufacturerInfoService", ICManufacturerInfoService.class);
		CManufacturerInfoEntity entity = this.paserJsonToEntity(CManufacturerInfoEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "厂商管理", methods = "修改")
	public void update() throws Exception {
		ICManufacturerInfoService service = ApplicationContext.getBean("cManufacturerInfoService", ICManufacturerInfoService.class);
		CManufacturerInfoEntity entity = this.paserJsonToEntity(CManufacturerInfoEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "厂商管理", methods = "删除")
	public void delete() throws Exception {
		ICManufacturerInfoService service = ApplicationContext.getBean("cManufacturerInfoService", ICManufacturerInfoService.class);
		CManufacturerInfoEntity entity = this.paserJsonToEntity(CManufacturerInfoEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "厂商管理", methods = "删除")
	public void deleteList() throws Exception {
		ICManufacturerInfoService service = ApplicationContext.getBean("cManufacturerInfoService", ICManufacturerInfoService.class);
		List<CManufacturerInfoEntity> entity = this.paserJsonToArray(CManufacturerInfoEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
}