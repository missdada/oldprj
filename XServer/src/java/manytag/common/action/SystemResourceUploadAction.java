package manytag.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.SystemResourceUploadEntity;
import manytag.common.dao.entity.SystemResourceUploadSearchEntity;
import manytag.common.service.ISystemResourceUploadService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("systemResourceUpload")
@Scope("prototype")
public class SystemResourceUploadAction extends BaseAction {
	public void doSearch() throws Exception {
		SystemResourceUploadSearchEntity entity = this.paserJsonToEntity(SystemResourceUploadSearchEntity.class);
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		List<SystemResourceUploadEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	public void insert() throws Exception {
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		SystemResourceUploadEntity entity = this.paserJsonToEntity(SystemResourceUploadEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	public void update() throws Exception {
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		SystemResourceUploadEntity entity = this.paserJsonToEntity(SystemResourceUploadEntity.class);
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
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		SystemResourceUploadEntity entity = this.paserJsonToEntity(SystemResourceUploadEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void deleteList() throws Exception {
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		List<SystemResourceUploadEntity> entity = this.paserJsonToArray(SystemResourceUploadEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	public void getUploadFile() throws Exception {
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		List<String> filepaths = new ArrayList<String>();
		Map<String, String> searchMap = this.paserJsonToEntity(HashMap.class);
		String imageUids = (String) searchMap.get("uid");
		String[] uids = imageUids.split(",");
		for (String uid : uids) {
			SystemResourceUploadSearchEntity systemResourceUploadSearchEntity = new SystemResourceUploadSearchEntity();
			systemResourceUploadSearchEntity.setUid(uid);
			List<SystemResourceUploadEntity> resourceList = service.search(systemResourceUploadSearchEntity);
			if (resourceList.size() > 0) {
				for (SystemResourceUploadEntity en : resourceList) {
					filepaths.add(en.getUid() + "," + en.getResouceUrl() + "," + en.getMemo());
				}
			}
		}
		JsonResponse result = new JsonResponse();
		result.put("rows", filepaths);
		this.setResult(result);
	}

	public void view() throws Exception {
		SystemResourceUploadSearchEntity entity = this.paserJsonToEntity(SystemResourceUploadSearchEntity.class);
		ISystemResourceUploadService service = ApplicationContext.getBean("systemResourceUploadService", ISystemResourceUploadService.class);
		SystemResourceUploadEntity entity2 = service.searchPK(entity);
		JsonResponse result = new JsonResponse();
		result.put("row", entity2);
		this.setResult(result);
	}
}