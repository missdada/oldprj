package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemDictionaryValueEntity;
import manytag.common.dao.entity.SystemDictionaryValueSearchEntity;
import manytag.common.service.ISystemDictionaryValueService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemDictionaryValueService")
public class SystemDictionaryValueService implements ISystemDictionaryValueService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemDictionaryValueEntity> search(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.search", systemDictionaryValueSearchEntity);
	}

	public SystemDictionaryValueEntity searchPK(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception {
		List<SystemDictionaryValueEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.searchPK",
				systemDictionaryValueSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemDictionaryValueEntity> searchAll(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.searchAll", systemDictionaryValueSearchEntity);
	}

	public long searchCount(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.searchCount", systemDictionaryValueSearchEntity);
		return count;
	}

	public int insert(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.insert", systemDictionaryValueEntity);
	}

	public int update(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.update", systemDictionaryValueEntity);
	}

	public int delete(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.delete", systemDictionaryValueEntity);
	}

	public int delete(List<SystemDictionaryValueEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.deleteList", list);
	}

	public int deleteAll(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemDictionaryValueMapper.deleteAll", systemDictionaryValueEntity);
	}
}