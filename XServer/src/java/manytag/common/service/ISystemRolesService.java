package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemRolesEntity;
import manytag.common.dao.entity.SystemRolesSearchEntity;

public interface ISystemRolesService extends IBaseService {
	public List<SystemRolesEntity> search(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception;

	public SystemRolesEntity searchPK(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception;

	public List<SystemRolesEntity> searchAll(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception;

	public long searchCount(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception;

	public int insert(SystemRolesEntity systemRolesEntity) throws Exception;

	public int update(SystemRolesEntity systemRolesEntity) throws Exception;

	public int delete(SystemRolesEntity systemRolesEntity) throws Exception;

	public int delete(List<SystemRolesEntity> list) throws Exception;
}