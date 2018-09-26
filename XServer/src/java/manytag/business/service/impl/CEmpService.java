package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.CEmpEntity;
import manytag.business.dao.entity.CEmpSearchEntity;
import manytag.business.service.ICEmpService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cEmpService")
@Transactional
public class CEmpService extends BaseBusinessService implements ICEmpService {
	@Resource
	private BaseDAO baseDAO;

	public List<CEmpEntity> search(CEmpSearchEntity cEmpSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICEmpMapper.search", cEmpSearchEntity);
	}

	public CEmpEntity searchPK(CEmpSearchEntity cEmpSearchEntity) throws Exception {
		List<CEmpEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICEmpMapper.searchPK", cEmpSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<CEmpEntity> searchAll(CEmpSearchEntity cEmpSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICEmpMapper.searchAll", cEmpSearchEntity);
	}

	public long searchCount(CEmpSearchEntity cEmpSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICEmpMapper.searchCount", cEmpSearchEntity);
		return count;
	}

	public int insert(CEmpEntity cEmpEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICEmpMapper.insert", cEmpEntity);
	}

	public void batchInsert(List<CEmpEntity> cEmpEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICEmpMapper.insert", cEmpEntity);
	}

	public int update(CEmpEntity cEmpEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICEmpMapper.update", cEmpEntity);
	}

	public void batchUpdate(List<CEmpEntity> cEmpEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICEmpMapper.update", cEmpEntity);
	}

	public int delete(CEmpEntity cEmpEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICEmpMapper.delete", cEmpEntity);
	}

	public int delete(List<CEmpEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICEmpMapper.deleteList", list);
	}

	public int deleteAll(CEmpEntity cEmpEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICEmpMapper.deleteAll", cEmpEntity);
	}

	public void batchDelete(List<CEmpEntity> cEmpEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICEmpMapper.delete", cEmpEntity);
	}
}