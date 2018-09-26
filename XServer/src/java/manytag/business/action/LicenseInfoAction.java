package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.LicenseInfoEntity;
import manytag.business.dao.entity.LicenseInfoSearchEntity;
import manytag.business.service.ILicenseInfoService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("licenseInfo")
@Scope("prototype")
public class LicenseInfoAction extends BaseBusinessAction {
	@SystemLog(module = "产品授权信息", methods = "检索")
	public void doSearch() throws Exception {
		LicenseInfoSearchEntity entity = this.paserJsonToEntity(LicenseInfoSearchEntity.class);
		ILicenseInfoService service = ApplicationContext.getBean("licenseInfoService", ILicenseInfoService.class);
		List<LicenseInfoEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		for (LicenseInfoEntity oneEntity : searchList) {
			oneEntity.setContractImagesUidStr(getImageUrls(oneEntity.getContractImagesUid()));
			oneEntity.setRequestSheetFileUidStr(getImageUrls(oneEntity.getRequestSheetFileUid()));
		}
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "产品授权信息", methods = "新建")
	public void insert() throws Exception {
		ILicenseInfoService service = ApplicationContext.getBean("licenseInfoService", ILicenseInfoService.class);
		LicenseInfoEntity entity = this.paserJsonToEntity(LicenseInfoEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "产品授权信息", methods = "修改")
	public void update() throws Exception {
		ILicenseInfoService service = ApplicationContext.getBean("licenseInfoService", ILicenseInfoService.class);
		LicenseInfoEntity entity = this.paserJsonToEntity(LicenseInfoEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "产品授权信息", methods = "删除")
	public void delete() throws Exception {
		ILicenseInfoService service = ApplicationContext.getBean("licenseInfoService", ILicenseInfoService.class);
		LicenseInfoEntity entity = this.paserJsonToEntity(LicenseInfoEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "产品授权信息", methods = "删除")
	public void deleteList() throws Exception {
		ILicenseInfoService service = ApplicationContext.getBean("licenseInfoService", ILicenseInfoService.class);
		List<LicenseInfoEntity> entity = this.paserJsonToArray(LicenseInfoEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
}