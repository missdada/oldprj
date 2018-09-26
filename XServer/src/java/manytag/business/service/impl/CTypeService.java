package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.CTypeEntity;
import manytag.business.dao.entity.CTypeSearchEntity;
import manytag.business.service.ICTypeService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cTypeService")
@Transactional
public class CTypeService extends BaseBusinessService implements ICTypeService {
	@Resource
	private BaseDAO baseDAO;

	public List<CTypeEntity> search(CTypeSearchEntity cTypeSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeMapper.search", cTypeSearchEntity);
	}

	public CTypeEntity searchPK(CTypeSearchEntity cTypeSearchEntity) throws Exception {
		List<CTypeEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeMapper.searchPK", cTypeSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<CTypeEntity> searchAll(CTypeSearchEntity cTypeSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeMapper.searchAll", cTypeSearchEntity);
	}

	public long searchCount(CTypeSearchEntity cTypeSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICTypeMapper.searchCount", cTypeSearchEntity);
		return count;
	}

	public int insert(CTypeEntity cTypeEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICTypeMapper.insert", cTypeEntity);
	}

	public void batchInsert(List<CTypeEntity> cTypeEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICTypeMapper.insert", cTypeEntity);
	}

	public int update(CTypeEntity cTypeEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICTypeMapper.update", cTypeEntity);
	}

	public void batchUpdate(List<CTypeEntity> cTypeEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICTypeMapper.update", cTypeEntity);
	}

	public int delete(CTypeEntity cTypeEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeMapper.delete", cTypeEntity);
	}

	public int delete(List<CTypeEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeMapper.deleteList", list);
	}

	public int deleteAll(CTypeEntity cTypeEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeMapper.deleteAll", cTypeEntity);
	}

	public void batchDelete(List<CTypeEntity> cTypeEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICTypeMapper.delete", cTypeEntity);
	}
}