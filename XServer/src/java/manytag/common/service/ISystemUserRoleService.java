package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemUserRoleEntity;
import manytag.common.dao.entity.SystemUserRoleSearchEntity;

public interface ISystemUserRoleService extends IBaseService {
	public List<SystemUserRoleEntity> search(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception;

	public SystemUserRoleEntity searchPK(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception;

	public List<SystemUserRoleEntity> searchAll(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception;

	public List<SystemUserRoleEntity> selectAccurate(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception;

	public long searchCount(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception;

	public int insert(SystemUserRoleEntity systemUserRoleEntity) throws Exception;

	public int update(SystemUserRoleEntity systemUserRoleEntity) throws Exception;

	public int delete(SystemUserRoleEntity systemUserRoleEntity) throws Exception;

	public int delete(List<SystemUserRoleEntity> list) throws Exception;
}