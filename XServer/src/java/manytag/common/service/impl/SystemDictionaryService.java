package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemDictionaryEntity;
import manytag.common.dao.entity.SystemDictionarySearchEntity;
import manytag.common.service.ISystemDictionaryService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemDictionaryService")
public class SystemDictionaryService implements ISystemDictionaryService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemDictionaryEntity> search(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.search", systemDictionarySearchEntity);
	}

	public List<SystemDictionaryEntity> searchAccurate(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.searchAccurate", systemDictionarySearchEntity);
	}

	public SystemDictionaryEntity searchPK(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception {
		List<SystemDictionaryEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.searchPK",
				systemDictionarySearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemDictionaryEntity> searchAll(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.searchAll", systemDictionarySearchEntity);
	}

	public long searchCount(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.searchCount", systemDictionarySearchEntity);
		return count;
	}

	public int insert(SystemDictionaryEntity systemDictionaryEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.insert", systemDictionaryEntity);
	}

	public int update(SystemDictionaryEntity systemDictionaryEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.update", systemDictionaryEntity);
	}

	public int delete(SystemDictionaryEntity systemDictionaryEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.delete", systemDictionaryEntity);
	}

	public int delete(List<SystemDictionaryEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemDictionaryMapper.deleteList", list);
	}
}