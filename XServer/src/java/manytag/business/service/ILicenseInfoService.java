package manytag.business.service;

import java.util.List;

import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.LicenseInfoEntity;
import manytag.business.dao.entity.LicenseInfoSearchEntity;

public interface ILicenseInfoService extends IBaseBusinessService {
	public List<LicenseInfoEntity> search(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception;

	public LicenseInfoEntity searchPK(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception;

	public List<LicenseInfoEntity> searchAll(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception;

	public long searchCount(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception;

	public int insert(LicenseInfoEntity licenseInfoEntity) throws Exception;

	public int update(LicenseInfoEntity licenseInfoEntity) throws Exception;

	public int delete(LicenseInfoEntity licenseInfoEntity) throws Exception;

	public int delete(List<LicenseInfoEntity> list) throws Exception;

	public int deleteAll(LicenseInfoEntity licenseInfoEntity) throws Exception;
}