package manytag.common.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import manytag.common.dao.entity.DicSearchEntity;
import manytag.common.dao.entity.DropDownResultEntity;
import manytag.common.dao.entity.TableSearchEntity;
import manytag.common.service.IGetDropDownService;
import manytag.framework.dispatch.base.ApplicationContext;
import manytag.framework.dispatch.base.BaseAction;
import manytag.framework.dispatch.base.response.impl.JsonResponse;

@Controller("getDropDown")
@Scope("prototype")
public class GetDropDownAction extends BaseAction {
	public void fromTable() throws Exception {
		TableSearchEntity entity = paramToEntity(TableSearchEntity.class);
		IGetDropDownService service = ApplicationContext.getBean("getDropDownService", IGetDropDownService.class);
		List<DropDownResultEntity> searchList = service.tableSearch(entity);

		JsonResponse result = new JsonResponse();
		result.put("droplist", searchList);
		this.setResult(result);
	}

	public void fromDic() throws Exception {
		DicSearchEntity entity = paramToEntity(DicSearchEntity.class);
		IGetDropDownService service = ApplicationContext.getBean("getDropDownService", IGetDropDownService.class);
		List<DropDownResultEntity> searchList = service.dicSearch(entity);

		JsonResponse result = new JsonResponse();
		result.put("droplist", searchList);
		this.setResult(result);
	}
}