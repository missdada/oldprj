package manytag.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manytag.business.base.BaseBusinessService;
import manytag.business.dao.entity.TreedemoEntity;
import manytag.business.dao.entity.TreedemoSearchEntity;
import manytag.business.service.ITreedemoService;
import manytag.framework.dao.mybatis.BaseDAO;

@Service("treedemoService")
@Transactional
public class TreedemoService extends BaseBusinessService implements ITreedemoService {
	@Resource
	private BaseDAO baseDAO;

	public List<TreedemoEntity> search(TreedemoSearchEntity treedemoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ITreedemoMapper.search", treedemoSearchEntity);
	}

	public TreedemoEntity searchPK(TreedemoSearchEntity treedemoSearchEntity) throws Exception {
		List<TreedemoEntity> result = baseDAO.selectList("manytag.business.dao.entity.mapper.ITreedemoMapper.searchPK", treedemoSearchEntity);
		if (result.size() > 0) {
			return result.get(0);
		}

		return null;
	}

	public List<TreedemoEntity> searchAll(TreedemoSearchEntity treedemoSearchEntity) throws Exception {
		return baseDAO.selectList("manytag.business.dao.entity.mapper.ITreedemoMapper.searchAll", treedemoSearchEntity);
	}

	public long searchCount(TreedemoSearchEntity treedemoSearchEntity) throws Exception {
		int count = baseDAO.selectOne("manytag.business.dao.entity.mapper.ITreedemoMapper.searchCount", treedemoSearchEntity);
		return count;
	}

	public int insert(TreedemoEntity treedemoEntity) throws Exception {
		return baseDAO.insert("manytag.business.dao.entity.mapper.ITreedemoMapper.insert", treedemoEntity);
	}

	public void batchInsert(List<TreedemoEntity> treedemoEntity) throws Exception {
		baseDAO.batchInsert("manytag.business.dao.entity.mapper.ITreedemoMapper.insert", treedemoEntity);
	}

	public int update(TreedemoEntity treedemoEntity) throws Exception {
		return baseDAO.update("manytag.business.dao.entity.mapper.ITreedemoMapper.update", treedemoEntity);
	}

	public void batchUpdate(List<TreedemoEntity> treedemoEntity) throws Exception {
		baseDAO.batchUpdate("manytag.business.dao.entity.mapper.ITreedemoMapper.update", treedemoEntity);
	}

	public int delete(TreedemoEntity treedemoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ITreedemoMapper.delete", treedemoEntity);
	}

	public int delete(List<TreedemoEntity> list) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ITreedemoMapper.deleteList", list);
	}

	public int deleteAll(TreedemoEntity treedemoEntity) throws Exception {
		return baseDAO.delete("manytag.business.dao.entity.mapper.ITreedemoMapper.deleteAll", treedemoEntity);
	}

	public void batchDelete(List<TreedemoEntity> treedemoEntity) throws Exception {
		baseDAO.batchDelete("manytag.business.dao.entity.mapper.ITreedemoMapper.delete", treedemoEntity);
	}

	public void saveTree(List<TreedemoEntity> list) throws Exception {
		String sessionid = baseDAO.createSession(ExecutorType.BATCH, false);
		try {
			baseDAO.delete("manytag.business.dao.entity.mapper.ITreedemoMapper.deleteAll", sessionid);

			if (null != list) {
				for (int i = 0; i < list.size(); i++) {
					baseDAO.insert("manytag.business.dao.entity.mapper.ITreedemoMapper.insert", list.get(i), sessionid);
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