package manytag.business.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.business.base.BaseBusinessAction;
import manytag.business.dao.entity.CGoodsInfoEntity;
import manytag.business.dao.entity.CGoodsInfoSearchEntity;
import manytag.business.service.ICGoodsInfoService;
import manytag.common.annotation.SystemLog;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.Message;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("cGoodsInfo")
@Scope("prototype")
public class CGoodsInfoAction extends BaseBusinessAction {
	@SystemLog(module = "商品信息", methods = "检索")
	public void doSearch() throws Exception {
		CGoodsInfoSearchEntity entity = this.paserJsonToEntity(CGoodsInfoSearchEntity.class);
		ICGoodsInfoService service = ApplicationContext.getBean("cGoodsInfoService", ICGoodsInfoService.class);
		List<CGoodsInfoEntity> searchList = service.search(entity);
		long listCount = service.searchCount(entity);
		for (CGoodsInfoEntity oneEntity : searchList) {
			oneEntity.setImagesUidStr(getImageUrls(oneEntity.getImagesUid()));
			oneEntity.setLicenseFileUidStr(getImageUrls(oneEntity.getLicenseFileUid()));
		}
		JsonResponse result = new JsonResponse();
		result.put("total", listCount);
		result.put("rows", searchList);
		this.setResult(result);
	}

	@SystemLog(module = "商品信息", methods = "新建")
	public void insert() throws Exception {
		ICGoodsInfoService service = ApplicationContext.getBean("cGoodsInfoService", ICGoodsInfoService.class);
		CGoodsInfoEntity entity = this.paserJsonToEntity(CGoodsInfoEntity.class);
		this.setUidDateTime(entity);
		int count = service.insert(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0001", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "商品信息", methods = "修改")
	public void update() throws Exception {
		ICGoodsInfoService service = ApplicationContext.getBean("cGoodsInfoService", ICGoodsInfoService.class);
		CGoodsInfoEntity entity = this.paserJsonToEntity(CGoodsInfoEntity.class);
		this.setUidDateTime(entity, false, false, true);
		int count = service.update(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		if (count == 0) {
			result.setMessage(new Message(Message.MESSAGE_TYPE_ERROR, "E0003", new String[] {}));
		}
		this.setResult(result);
	}

	@SystemLog(module = "商品信息", methods = "删除")
	public void delete() throws Exception {
		ICGoodsInfoService service = ApplicationContext.getBean("cGoodsInfoService", ICGoodsInfoService.class);
		CGoodsInfoEntity entity = this.paserJsonToEntity(CGoodsInfoEntity.class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}

	@SystemLog(module = "商品信息", methods = "删除")
	public void deleteList() throws Exception {
		ICGoodsInfoService service = ApplicationContext.getBean("cGoodsInfoService", ICGoodsInfoService.class);
		List<CGoodsInfoEntity> entity = this.paserJsonToArray(CGoodsInfoEntity[].class);
		int count = service.delete(entity);
		JsonResponse result = new JsonResponse();
		result.put("count", count);
		result.setMessage(new Message(Message.MESSAGE_TYPE_INFO, "I0002", new String[] {}));
		this.setResult(result);
	}
}