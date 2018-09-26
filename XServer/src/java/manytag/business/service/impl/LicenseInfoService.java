package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.LicenseInfoEntity;
import manytag.business.dao.entity.LicenseInfoSearchEntity;
import manytag.business.service.ILicenseInfoService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("licenseInfoService")
public class LicenseInfoService extends BaseBusinessService implements ILicenseInfoService {
	@Resource
	private BaseDAO baseDAO;

	public List<LicenseInfoEntity> search(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ILicenseInfoMapper.search", licenseInfoSearchEntity);
	}

	public LicenseInfoEntity searchPK(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception {
		List<LicenseInfoEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ILicenseInfoMapper.searchPK", licenseInfoSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<LicenseInfoEntity> searchAll(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ILicenseInfoMapper.searchAll", licenseInfoSearchEntity);
	}

	public long searchCount(LicenseInfoSearchEntity licenseInfoSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ILicenseInfoMapper.searchCount", licenseInfoSearchEntity);
		return count;
	}

	public int insert(LicenseInfoEntity licenseInfoEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ILicenseInfoMapper.insert", licenseInfoEntity);
	}

	public int update(LicenseInfoEntity licenseInfoEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ILicenseInfoMapper.update", licenseInfoEntity);
	}

	public int delete(LicenseInfoEntity licenseInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ILicenseInfoMapper.delete", licenseInfoEntity);
	}

	public int delete(List<LicenseInfoEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ILicenseInfoMapper.deleteList", list);
	}

	public int deleteAll(LicenseInfoEntity licenseInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ILicenseInfoMapper.deleteAll", licenseInfoEntity);
	}
}