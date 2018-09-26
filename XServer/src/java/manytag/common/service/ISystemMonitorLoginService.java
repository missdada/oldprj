package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemMonitorLoginEntity;
import manytag.common.dao.entity.SystemMonitorLoginSearchEntity;

public interface ISystemMonitorLoginService extends IBaseService {
	public List<SystemMonitorLoginEntity> search(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception;

	public SystemMonitorLoginEntity searchPK(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception;

	public List<SystemMonitorLoginEntity> searchAll(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception;

	public long searchCount(SystemMonitorLoginSearchEntity systemMonitorLoginSearchEntity) throws Exception;

	public int insert(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception;

	public int update(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception;

	public int delete(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception;

	public int delete(List<SystemMonitorLoginEntity> list) throws Exception;

	public int deleteAll(SystemMonitorLoginEntity systemMonitorLoginEntity) throws Exception;
}