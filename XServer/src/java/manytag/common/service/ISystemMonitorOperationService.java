package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemMonitorOperationEntity;
import manytag.common.dao.entity.SystemMonitorOperationSearchEntity;

public interface ISystemMonitorOperationService extends IBaseService {
	public List<SystemMonitorOperationEntity> search(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception;

	public SystemMonitorOperationEntity searchPK(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception;

	public List<SystemMonitorOperationEntity> searchAll(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception;

	public long searchCount(SystemMonitorOperationSearchEntity systemMonitorOperationSearchEntity) throws Exception;

	public int insert(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception;

	public int update(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception;

	public int delete(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception;

	public int delete(List<SystemMonitorOperationEntity> list) throws Exception;

	public int deleteAll(SystemMonitorOperationEntity systemMonitorOperationEntity) throws Exception;
}