package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemUserRoleEntity;
import manytag.common.dao.entity.SystemUserRoleSearchEntity;
import manytag.common.service.ISystemUserRoleService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemUserRoleService")
public class SystemUserRoleService implements ISystemUserRoleService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemUserRoleEntity> search(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.search", systemUserRoleSearchEntity);
	}

	public SystemUserRoleEntity searchPK(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception {
		List<SystemUserRoleEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.searchPK",
				systemUserRoleSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemUserRoleEntity> searchAll(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.searchAll", systemUserRoleSearchEntity);
	}

	public List<SystemUserRoleEntity> selectAccurate(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.selectAccurate", systemUserRoleSearchEntity);
	}

	public long searchCount(SystemUserRoleSearchEntity systemUserRoleSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.searchCount", systemUserRoleSearchEntity);
		return count;
	}

	public int insert(SystemUserRoleEntity systemUserRoleEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.insert", systemUserRoleEntity);
	}

	public int update(SystemUserRoleEntity systemUserRoleEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.update", systemUserRoleEntity);
	}

	public int delete(SystemUserRoleEntity systemUserRoleEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.delete", systemUserRoleEntity);
	}

	public int delete(List<SystemUserRoleEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemUserRoleMapper.deleteList", list);
	}
}