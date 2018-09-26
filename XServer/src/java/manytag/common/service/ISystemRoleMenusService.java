package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemRoleMenusEntity;
import manytag.common.dao.entity.SystemRoleMenusSearchEntity;

public interface ISystemRoleMenusService extends IBaseService {
	public List<SystemRoleMenusEntity> search(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception;

	public SystemRoleMenusEntity searchPK(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception;

	public List<SystemRoleMenusEntity> searchAll(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception;

	public long searchCount(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception;

	public int insert(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception;

	public int update(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception;

	public int delete(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception;

	public int delete(List<SystemRoleMenusEntity> list) throws Exception;
}