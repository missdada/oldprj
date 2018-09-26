package manytag.business.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.CTypeInfoEntity;
import manytag.business.dao.entity.CTypeInfoSearchEntity;
import manytag.business.service.ICTypeInfoService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cTypeInfo")
@Scope("prototype")
public class CTypeInfoAction extends BaseBusinessAction {
	@SystemLog(module = "分类信息", methods = "检索")
	public void doSearch() throws Exception {
		CTypeInfoSearchEntity entity = this.paserJsonToEntity(CTypeInfoSearchEntity.class);
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		List<CTypeInfoEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "分类信息", methods = "新建")
	public void insert() throws Exception {
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		CTypeInfoEntity entity = this.paserJsonToEntity(CTypeInfoEntity.class);
		this.setUidDateTime(entity, false, true, true);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "分类信息", methods = "修改")
	public void update() throws Exception {
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		CTypeInfoEntity entity = this.paserJsonToEntity(CTypeInfoEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "分类信息", methods = "删除")
	public void delete() throws Exception {
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		CTypeInfoEntity entity = this.paserJsonToEntity(CTypeInfoEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "分类信息", methods = "删除")
	public void deleteList() throws Exception {
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		List<CTypeInfoEntity> entity = this.paserJsonToArray(CTypeInfoEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SuppressWarnings("unchecked")
	@SystemLog(module = "分类信息", methods = "保存树")
	public void saveTree() throws Exception {
		JsonResponse result = new JsonResponse();
		ICTypeInfoService service = ApplicationContext.getBean("cTypeInfoService", ICTypeInfoService.class);
		HashMap<String, List<CTypeInfoEntity>> params = this.paserJsonToEntity(HashMap.class);
		List<CTypeInfoEntity> entity = params.get("treedata");
		if (entity != null) {
			try {
				service.saveTree(entity);
				result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0003", new String[] {}));
			} catch (Exception e) {
				result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
				e.printStackTrace();
			}
		}
		this.setResult(result);
	}
}