package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemDictionaryEntity;
import manytag.common.dao.entity.SystemDictionarySearchEntity;

public interface ISystemDictionaryService extends IBaseService {
	public List<SystemDictionaryEntity> search(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception;
	
	public List<SystemDictionaryEntity> searchAccurate(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception;

	public SystemDictionaryEntity searchPK(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception;

	public List<SystemDictionaryEntity> searchAll(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception;

	public long searchCount(SystemDictionarySearchEntity systemDictionarySearchEntity) throws Exception;

	public int insert(SystemDictionaryEntity systemDictionaryEntity) throws Exception;

	public int update(SystemDictionaryEntity systemDictionaryEntity) throws Exception;

	public int delete(SystemDictionaryEntity systemDictionaryEntity) throws Exception;

	public int delete(List<SystemDictionaryEntity> list) throws Exception;
}