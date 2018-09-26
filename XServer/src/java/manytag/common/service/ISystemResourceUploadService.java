package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemResourceUploadEntity;
import manytag.common.dao.entity.SystemResourceUploadSearchEntity;

public interface ISystemResourceUploadService extends IBaseService {
	public List<SystemResourceUploadEntity> search(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception;

	public SystemResourceUploadEntity searchPK(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception;

	public List<SystemResourceUploadEntity> searchAll(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception;

	public long searchCount(SystemResourceUploadSearchEntity systemResourceUploadSearchEntity) throws Exception;

	public int insert(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception;

	public int update(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception;

	public int delete(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception;

	public int delete(List<SystemResourceUploadEntity> list) throws Exception;

	public int deleteAll(SystemResourceUploadEntity systemResourceUploadEntity) throws Exception;
}