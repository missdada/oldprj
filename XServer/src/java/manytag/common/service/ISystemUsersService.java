package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemUsersEntity;
import manytag.common.dao.entity.SystemUsersSearchEntity;

public interface ISystemUsersService extends IBaseService {
	public List<SystemUsersEntity> search(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception;

	public SystemUsersEntity searchPK(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception;

	public List<SystemUsersEntity> searchAll(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception;

	public long searchCount(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception;

	public int insert(SystemUsersEntity systemUsersEntity) throws Exception;

	public int update(SystemUsersEntity systemUsersEntity) throws Exception;

	public int delete(SystemUsersEntity systemUsersEntity) throws Exception;

	public int delete(List<SystemUsersEntity> list) throws Exception;
}