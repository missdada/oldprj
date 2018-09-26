package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.CGoodsInfoEntity;
import manytag.business.dao.entity.CGoodsInfoSearchEntity;
import manytag.business.service.ICGoodsInfoService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cGoodsInfoService")
@Transactional
public class CGoodsInfoService extends BaseBusinessService implements ICGoodsInfoService {
	@Resource
	private BaseDAO baseDAO;

	public List<CGoodsInfoEntity> search(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.search", cGoodsInfoSearchEntity);
	}

	public CGoodsInfoEntity searchPK(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception {
		List<CGoodsInfoEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.searchPK", cGoodsInfoSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<CGoodsInfoEntity> searchAll(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.searchAll", cGoodsInfoSearchEntity);
	}

	public long searchCount(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.searchCount", cGoodsInfoSearchEntity);
		return count;
	}

	public int insert(CGoodsInfoEntity cGoodsInfoEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.insert", cGoodsInfoEntity);
	}

	public void batchInsert(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.insert", cGoodsInfoEntity);
	}

	public int update(CGoodsInfoEntity cGoodsInfoEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.update", cGoodsInfoEntity);
	}

	public void batchUpdate(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.update", cGoodsInfoEntity);
	}

	public int delete(CGoodsInfoEntity cGoodsInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.delete", cGoodsInfoEntity);
	}

	public int delete(List<CGoodsInfoEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.deleteList", list);
	}

	public int deleteAll(CGoodsInfoEntity cGoodsInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.deleteAll", cGoodsInfoEntity);
	}

	public void batchDelete(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICGoodsInfoMapper.delete", cGoodsInfoEntity);
	}
}