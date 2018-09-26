package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.CManufacturerInfoEntity;
import manytag.business.dao.entity.CManufacturerInfoSearchEntity;

public interface ICManufacturerInfoService extends IBaseBusinessService {
	public List<CManufacturerInfoEntity> search(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception;

	public CManufacturerInfoEntity searchPK(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception;

	public List<CManufacturerInfoEntity> searchAll(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception;

	public long searchCount(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception;

	public int insert(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception;

	public void batchInsert(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception;

	public int update(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception;

	public void batchUpdate(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception;

	public int delete(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception;

	public int delete(List<CManufacturerInfoEntity> list) throws Exception;

	public int deleteAll(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception;

	public void batchDelete(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception;
}