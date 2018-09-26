package manytag.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.common.dao.entity.SystemMonitorLoginEntity;
import manytag.common.dao.entity.SystemMonitorLoginSearchEntity;
import manytag.common.service.ISystemMonitorLoginService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("systemMonitorLoginService")
public class SystemMonitorLoginService implements ISystemMonitorLoginService {
	@Resource
	private BaseDAO baseDAO;

	public List<SystemMonitorLoginEntity> search(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.search", systemMonitorLoginSearchEntity);
	}

	public SystemMonitorLoginEntity searchPK(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception {
		List<SystemMonitorLoginEntity> result = baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.searchPK",
				systemMonitorLoginSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<SystemMonitorLoginEntity> searchAll(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.searchAll", systemMonitorLoginSearchEntity);
	}

	public long searchCount(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.searchCount", systemMonitorLoginSearchEntity);
		return count;
	}

	public int insert(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception {
		return baseDAO.insert("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.insert", systemMonitorLoginEntity);
	}

	public int update(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception {
		return baseDAO.update("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.update", systemMonitorLoginEntity);
	}

	public int delete(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.delete", systemMonitorLoginEntity);
	}

	public int delete(List<SystemMonitorLoginEntity> list) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.deleteList", list);
	}

	public int deleteAll(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception {
		return baseDAO.delete("manytag.common.dao.entity.mapper.ISystemMonitorLoginMapper.deleteAll", systemMonitorLoginEntity);
	}
}