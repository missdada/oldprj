package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.COrgtypeEntity;
import manytag.business.dao.entity.COrgtypeSearchEntity;

public interface ICOrgtypeService extends IBaseBusinessService {
	public List<COrgtypeEntity> search(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception;

	public COrgtypeEntity searchPK(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception;

	public List<COrgtypeEntity> searchAll(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception;

	public long searchCount(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception;

	public int insert(COrgtypeEntity cOrgtypeEntity) throws Exception;

	public void batchInsert(List<COrgtypeEntity> cOrgtypeEntity) throws Exception;

	public int update(COrgtypeEntity cOrgtypeEntity) throws Exception;

	public void batchUpdate(List<COrgtypeEntity> cOrgtypeEntity) throws Exception;

	public int delete(COrgtypeEntity cOrgtypeEntity) throws Exception;

	public int delete(List<COrgtypeEntity> list) throws Exception;

	public int deleteAll(COrgtypeEntity cOrgtypeEntity) throws Exception;

	public void batchDelete(List<COrgtypeEntity> cOrgtypeEntity) throws Exception;
}