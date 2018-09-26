package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.DicSearchEntity;
import manytag.common.dao.entity.DropDownResultEntity;
import manytag.common.dao.entity.TableSearchEntity;

public interface IGetDropDownService {
	List<DropDownResultEntity> tableSearch(TableSearchEntity tableSearchEntity);

	List<DropDownResultEntity> dicSearch(DicSearchEntity dicSearchEntity);
}