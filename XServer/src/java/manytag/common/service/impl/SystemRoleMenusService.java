package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemRoleMenusEntity;
import manytag.common.dao.entity.SystemRoleMenusSearchEntity;
import manytag.common.service.ISystemRoleMenusService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemRoleMenusService")
public class SystemRoleMenusService implements ISystemRoleMenusService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemRoleMenusEntity> search(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.search", systemRoleMenusSearchEntity);
	}

	public SystemRoleMenusEntity searchPK(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception {
		List<SystemRoleMenusEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.searchPK",
				systemRoleMenusSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemRoleMenusEntity> searchAll(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.searchAll", systemRoleMenusSearchEntity);
	}

	public long searchCount(SystemRoleMenusSearchEntity systemRoleMenusSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.searchCount", systemRoleMenusSearchEntity);
		return count;
	}

	public int insert(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.insert", systemRoleMenusEntity);
	}

	public int update(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.update", systemRoleMenusEntity);
	}

	public int delete(SystemRoleMenusEntity systemRoleMenusEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.delete", systemRoleMenusEntity);
	}

	public int delete(List<SystemRoleMenusEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemRoleMenusMapper.deleteList", list);
	}
}