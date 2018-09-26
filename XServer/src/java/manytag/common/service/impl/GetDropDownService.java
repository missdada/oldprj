package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.DicSearchEntity;
import manytag.common.dao.entity.DropDownResultEntity;
import manytag.common.dao.entity.TableSearchEntity;
import manytag.common.service.IGetDropDownService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("getDropDownService")
public class GetDropDownService implements IGetDropDownService {
	@Resource
	private BaseDAO baseDAO;

	public List<DropDownResultEntity> tableSearch(TableSearchEntity tableSearchEntity) {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.IGetDropDownMapper.tablesearch", tableSearchEntity);
	}

	public List<DropDownResultEntity> dicSearch(DicSearchEntity dicSearchEntity) {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.IGetDropDownMapper.dicsearch", dicSearchEntity);
	}
}