package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemUsersEntity;
import manytag.common.dao.entity.SystemUsersSearchEntity;
import manytag.common.service.ISystemUsersService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemUsersService")
public class SystemUsersService implements ISystemUsersService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemUsersEntity> search(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUsersMapper.search", systemUsersSearchEntity);
	}

	public SystemUsersEntity searchPK(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception {
		List<SystemUsersEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUsersMapper.searchPK", systemUsersSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemUsersEntity> searchAll(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUsersMapper.searchAll", systemUsersSearchEntity);
	}

	public long searchCount(SystemUsersSearchEntity systemUsersSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemUsersMapper.searchCount", systemUsersSearchEntity);
		return count;
	}

	public int insert(SystemUsersEntity systemUsersEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemUsersMapper.insert", systemUsersEntity);
	}

	public int update(SystemUsersEntity systemUsersEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemUsersMapper.update", systemUsersEntity);
	}

	public int delete(SystemUsersEntity systemUsersEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemUsersMapper.delete", systemUsersEntity);
	}

	public int delete(List<SystemUsersEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemUsersMapper.deleteList", list);
	}
}