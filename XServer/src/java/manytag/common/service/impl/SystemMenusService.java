package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemMenusEntity;
import manytag.common.dao.entity.SystemMenusSearchEntity;
import manytag.common.service.ISystemMenusService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemMenusService")
public class SystemMenusService implements ISystemMenusService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemMenusEntity> search(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMenusMapper.search", systemMenusSearchEntity);
	}

	public SystemMenusEntity searchPK(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception {
		List<SystemMenusEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMenusMapper.searchPK", systemMenusSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemMenusEntity> searchAll(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMenusMapper.searchAll", systemMenusSearchEntity);
	}

	public long searchCount(SystemMenusSearchEntity systemMenusSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemMenusMapper.searchCount", systemMenusSearchEntity);
		return count;
	}

	public int insert(SystemMenusEntity systemMenusEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemMenusMapper.insert", systemMenusEntity);
	}

	public int update(SystemMenusEntity systemMenusEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemMenusMapper.update", systemMenusEntity);
	}

	public int delete(SystemMenusEntity systemMenusEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMenusMapper.delete", systemMenusEntity);
	}

	public int delete(List<SystemMenusEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMenusMapper.deleteList", list);
	}
}