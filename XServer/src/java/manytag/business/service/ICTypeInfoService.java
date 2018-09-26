package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.CTypeInfoEntity;
import manytag.business.dao.entity.CTypeInfoSearchEntity;

public interface ICTypeInfoService extends IBaseBusinessService {
	public List<CTypeInfoEntity> search(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception;

	public CTypeInfoEntity searchPK(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception;

	public List<CTypeInfoEntity> searchAll(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception;

	public long searchCount(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception;

	public int insert(CTypeInfoEntity cTypeInfoEntity) throws Exception;

	public void batchInsert(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception;

	public int update(CTypeInfoEntity cTypeInfoEntity) throws Exception;

	public void batchUpdate(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception;

	public int delete(CTypeInfoEntity cTypeInfoEntity) throws Exception;

	public int delete(List<CTypeInfoEntity> list) throws Exception;

	public int deleteAll(CTypeInfoEntity cTypeInfoEntity) throws Exception;

	public void batchDelete(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception;

	public void saveTree(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception;
}