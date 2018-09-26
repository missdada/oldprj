package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemMenusEntity;
import manytag.common.dao.entity.SystemMenusSearchEntity;

public interface ISystemMenusService extends IBaseService {
	public List<SystemMenusEntity> search(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception;

	public SystemMenusEntity searchPK(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception;

	public List<SystemMenusEntity> searchAll(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception;

	public long searchCount(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception;

	public int insert(SystemMenusEntity systemMenusEntity) throws Exception;

	public int update(SystemMenusEntity systemMenusEntity) throws Exception;

	public int delete(SystemMenusEntity systemMenusEntity) throws Exception;

	public int delete(List<SystemMenusEntity> list) throws Exception;
}