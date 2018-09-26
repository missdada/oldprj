package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.CTypeInfoEntity;
import manytag.business.dao.entity.CTypeInfoSearchEntity;
import manytag.business.service.ICTypeInfoService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("cTypeInfoService")
@Transactional
public class CTypeInfoService extends BaseBusinessService implements ICTypeInfoService {
	@Resource
	private BaseDAO baseDAO;

	public List<CTypeInfoEntity> search(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeInfoMapper.search", cTypeInfoSearchEntity);
	}

	public CTypeInfoEntity searchPK(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception {
		List<CTypeInfoEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeInfoMapper.searchPK", cTypeInfoSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<CTypeInfoEntity> searchAll(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ICTypeInfoMapper.searchAll", cTypeInfoSearchEntity);
	}

	public long searchCount(CTypeInfoSearchEntity cTypeInfoSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ICTypeInfoMapper.searchCount", cTypeInfoSearchEntity);
		return count;
	}

	public int insert(CTypeInfoEntity cTypeInfoEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ICTypeInfoMapper.insert", cTypeInfoEntity);
	}

	public void batchInsert(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ICTypeInfoMapper.insert", cTypeInfoEntity);
	}

	public int update(CTypeInfoEntity cTypeInfoEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ICTypeInfoMapper.update", cTypeInfoEntity);
	}

	public void batchUpdate(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ICTypeInfoMapper.update", cTypeInfoEntity);
	}

	public int delete(CTypeInfoEntity cTypeInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeInfoMapper.delete", cTypeInfoEntity);
	}

	public int delete(List<CTypeInfoEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeInfoMapper.deleteList", list);
	}

	public int deleteAll(CTypeInfoEntity cTypeInfoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeInfoMapper.deleteAll", cTypeInfoEntity);
	}

	public void batchDelete(List<CTypeInfoEntity> cTypeInfoEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ICTypeInfoMapper.delete", cTypeInfoEntity);
	}

	public void saveTree(List<CTypeInfoEntity> list) throws Exception {
		String sessionid = baseDAO.createSession(ExecutorType.BATCH, false);
		try {
			baseDAO.delete("manytag.business.dao.entity.mapper.ICTypeInfoMapper.deleteAll", sessionid);

			if (null != list) {
				for (int i = 0; i < list.size(); i++) {
					baseDAO.insert("manytag.business.dao.entity.mapper.ICTypeInfoMapper.insert", list.get(i), sessionid);
				}
			}
			baseDAO.sessionCommit(sessionid);
		} catch (Exception e) {
			baseDAO.sessionRollback(sessionid);
			throw e;
		} finally {
			baseDAO.sessionClose(sessionid);
		}
	}
}