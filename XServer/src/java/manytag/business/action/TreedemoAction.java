package manytag.business.action;

import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.TreedemoEntity;
import manytag.business.dao.entity.TreedemoSearchEntity;
import manytag.business.service.ITreedemoService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("treedemo")
@Scope("prototype")
public class TreedemoAction extends BaseBusinessAction {
	@SystemLog(module = "树test", methods = "检索")
	public void doSearch() throws Exception {
		TreedemoSearchEntity entity = this.paserJsonToEntity(TreedemoSearchEntity.class);
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		List<TreedemoEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "树test", methods = "新建")
	public void insert() throws Exception {
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		TreedemoEntity entity = this.paserJsonToEntity(TreedemoEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "树test", methods = "修改")
	public void update() throws Exception {
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		TreedemoEntity entity = this.paserJsonToEntity(TreedemoEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "树test", methods = "删除")
	public void delete() throws Exception {
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		TreedemoEntity entity = this.paserJsonToEntity(TreedemoEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "树test", methods = "删除")
	public void deleteList() throws Exception {
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		List<TreedemoEntity> entity = this.paserJsonToArray(TreedemoEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SuppressWarnings("unchecked")
	@SystemLog(module = "树test", methods = "保存树")
	public void saveTree() throws Exception {
		JsonResponse result = new JsonResponse();
		ITreedemoService service = ApplicationContext.getBean("treedemoService", ITreedemoService.class);
		HashMap<String, List<TreedemoEntity>> params = this.paserJsonToEntity(HashMap.class);
		List<TreedemoEntity> entity = params.get("treedata");
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