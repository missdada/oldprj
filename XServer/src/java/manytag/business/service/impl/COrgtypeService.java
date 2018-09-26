package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.COrgtypeEntity;
import manytag.business.dao.entity.COrgtypeSearchEntity;
import manytag.business.service.ICOrgtypeService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cOrgtypeService")
@Transactional
public class COrgtypeService extends BaseBusinessService implements ICOrgtypeService {
	@Resource
	private BaseDAO baseDAO;

	public List<COrgtypeEntity> search(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICOrgtypeMapper.search", cOrgtypeSearchEntity);
	}

	public COrgtypeEntity searchPK(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception {
		List<COrgtypeEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICOrgtypeMapper.searchPK", cOrgtypeSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<COrgtypeEntity> searchAll(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICOrgtypeMapper.searchAll", cOrgtypeSearchEntity);
	}

	public long searchCount(COrgtypeSearchEntity cOrgtypeSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICOrgtypeMapper.searchCount", cOrgtypeSearchEntity);
		return count;
	}

	public int insert(COrgtypeEntity cOrgtypeEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICOrgtypeMapper.insert", cOrgtypeEntity);
	}

	public void batchInsert(List<COrgtypeEntity> cOrgtypeEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICOrgtypeMapper.insert", cOrgtypeEntity);
	}

	public int update(COrgtypeEntity cOrgtypeEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICOrgtypeMapper.update", cOrgtypeEntity);
	}

	public void batchUpdate(List<COrgtypeEntity> cOrgtypeEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICOrgtypeMapper.update", cOrgtypeEntity);
	}

	public int delete(COrgtypeEntity cOrgtypeEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICOrgtypeMapper.delete", cOrgtypeEntity);
	}

	public int delete(List<COrgtypeEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICOrgtypeMapper.deleteList", list);
	}

	public int deleteAll(COrgtypeEntity cOrgtypeEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICOrgtypeMapper.deleteAll", cOrgtypeEntity);
	}

	public void batchDelete(List<COrgtypeEntity> cOrgtypeEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICOrgtypeMapper.delete", cOrgtypeEntity);
	}
}