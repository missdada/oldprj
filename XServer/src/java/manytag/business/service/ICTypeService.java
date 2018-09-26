package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.CTypeEntity;
import manytag.business.dao.entity.CTypeSearchEntity;

public interface ICTypeService extends IBaseBusinessService {
	public List<CTypeEntity> search(CTypeSearchEntity cTypeSearchEntity) throws Exception;

	public CTypeEntity searchPK(CTypeSearchEntity cTypeSearchEntity) throws Exception;

	public List<CTypeEntity> searchAll(CTypeSearchEntity cTypeSearchEntity) throws Exception;

	public long searchCount(CTypeSearchEntity cTypeSearchEntity) throws Exception;

	public int insert(CTypeEntity cTypeEntity) throws Exception;

	public void batchInsert(List<CTypeEntity> cTypeEntity) throws Exception;

	public int update(CTypeEntity cTypeEntity) throws Exception;

	public void batchUpdate(List<CTypeEntity> cTypeEntity) throws Exception;

	public int delete(CTypeEntity cTypeEntity) throws Exception;

	public int delete(List<CTypeEntity> list) throws Exception;

	public int deleteAll(CTypeEntity cTypeEntity) throws Exception;

	public void batchDelete(List<CTypeEntity> cTypeEntity) throws Exception;
}