package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemResourceUploadEntity;
import manytag.common.dao.entity.SystemResourceUploadSearchEntity;
import manytag.common.service.ISystemResourceUploadService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemResourceUploadService")
public class SystemResourceUploadService implements ISystemResourceUploadService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemResourceUploadEntity> search(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.search", systemResourceUploadSearchEntity);
	}

	public SystemResourceUploadEntity searchPK(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception {
		List<SystemResourceUploadEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.searchPK",
				systemResourceUploadSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemResourceUploadEntity> searchAll(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.searchAll", systemResourceUploadSearchEntity);
	}

	public long searchCount(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.searchCount", systemResourceUploadSearchEntity);
		return count;
	}

	public int insert(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.insert", systemResourceUploadEntity);
	}

	public int update(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.update", systemResourceUploadEntity);
	}

	public int delete(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.delete", systemResourceUploadEntity);
	}

	public int delete(List<SystemResourceUploadEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.deleteList", list);
	}

	public int deleteAll(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemResourceUploadMapper.deleteAll", systemResourceUploadEntity);
	}
}