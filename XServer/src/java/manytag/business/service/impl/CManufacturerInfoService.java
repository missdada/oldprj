package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.CManufacturerInfoEntity;
import manytag.business.dao.entity.CManufacturerInfoSearchEntity;
import manytag.business.service.ICManufacturerInfoService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cManufacturerInfoService")
@Transactional
public class CManufacturerInfoService extends BaseBusinessService implements ICManufacturerInfoService {
	@Resource
	private BaseDAO baseDAO;

	public List<CManufacturerInfoEntity> search(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.search", cManufacturerInfoSearchEntity);
	}

	public CManufacturerInfoEntity searchPK(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception {
		List<CManufacturerInfoEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.searchPK", cManufacturerInfoSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<CManufacturerInfoEntity> searchAll(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.searchAll", cManufacturerInfoSearchEntity);
	}

	public long searchCount(CManufacturerInfoSearchEntity cManufacturerInfoSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.searchCount", cManufacturerInfoSearchEntity);
		return count;
	}

	public int insert(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.insert", cManufacturerInfoEntity);
	}

	public void batchInsert(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.insert", cManufacturerInfoEntity);
	}

	public int update(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.update", cManufacturerInfoEntity);
	}

	public void batchUpdate(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.update", cManufacturerInfoEntity);
	}

	public int delete(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.delete", cManufacturerInfoEntity);
	}

	public int delete(List<CManufacturerInfoEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.deleteList", list);
	}

	public int deleteAll(CManufacturerInfoEntity cManufacturerInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.deleteAll", cManufacturerInfoEntity);
	}

	public void batchDelete(List<CManufacturerInfoEntity> cManufacturerInfoEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICManufacturerInfoMapper.delete", cManufacturerInfoEntity);
	}
}