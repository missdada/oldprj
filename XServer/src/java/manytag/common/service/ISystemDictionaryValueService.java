package manytag.common.service;

import java.util.List;

import manytag.common.dao.entity.SystemDictionaryValueEntity;
import manytag.common.dao.entity.SystemDictionaryValueSearchEntity;

public interface ISystemDictionaryValueService extends IBaseService {
	public List<SystemDictionaryValueEntity> search(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception;

	public SystemDictionaryValueEntity searchPK(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception;

	public List<SystemDictionaryValueEntity> searchAll(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception;

	public long searchCount(SystemDictionaryValueSearchEntity systemDictionaryValueSearchEntity) throws Exception;

	public int insert(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception;

	public int update(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception;

	public int delete(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception;

	public int delete(List<SystemDictionaryValueEntity> list) throws Exception;

	public int deleteAll(SystemDictionaryValueEntity systemDictionaryValueEntity) throws Exception;
}