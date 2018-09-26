package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemMonitorOperationEntity;
import manytag.common.dao.entity.SystemMonitorOperationSearchEntity;
import manytag.common.service.ISystemMonitorOperationService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemMonitorOperationService")
public class SystemMonitorOperationService implements ISystemMonitorOperationService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemMonitorOperationEntity> search(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.search", systemMonitorOperationSearchEntity);
	}

	public SystemMonitorOperationEntity searchPK(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception {
		List<SystemMonitorOperationEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.searchPK",
				systemMonitorOperationSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemMonitorOperationEntity> searchAll(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.searchAll", systemMonitorOperationSearchEntity);
	}

	public long searchCount(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.searchCount",
				systemMonitorOperationSearchEntity);
		return count;
	}

	public int insert(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.insert", systemMonitorOperationEntity);
	}

	public int update(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.update", systemMonitorOperationEntity);
	}

	public int delete(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.delete", systemMonitorOperationEntity);
	}

	public int delete(List<SystemMonitorOperationEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.deleteList", list);
	}

	public int deleteAll(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorOperationMapper.deleteAll", systemMonitorOperationEntity);
	}
}