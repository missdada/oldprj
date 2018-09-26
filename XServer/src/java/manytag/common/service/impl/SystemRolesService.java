package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemRolesEntity;
import manytag.common.dao.entity.SystemRolesSearchEntity;
import manytag.common.service.ISystemRolesService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemRolesService")
public class SystemRolesService implements ISystemRolesService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemRolesEntity> search(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRolesMapper.search", systemRolesSearchEntity);
	}

	public SystemRolesEntity searchPK(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception {
		List<SystemRolesEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRolesMapper.searchPK", systemRolesSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemRolesEntity> searchAll(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRolesMapper.searchAll", systemRolesSearchEntity);
	}

	public long searchCount(SystemRolesSearchEntity systemRolesSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemRolesMapper.searchCount", systemRolesSearchEntity);
		return count;
	}

	public int insert(SystemRolesEntity systemRolesEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemRolesMapper.insert", systemRolesEntity);
	}

	public int update(SystemRolesEntity systemRolesEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemRolesMapper.update", systemRolesEntity);
	}

	public int delete(SystemRolesEntity systemRolesEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemRolesMapper.delete", systemRolesEntity);
	}

	public int delete(List<SystemRolesEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemRolesMapper.deleteList", list);
	}
}