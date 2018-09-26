package manytag.business.service;

import java.util.List;

import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.TreedemoEntity;
import manytag.business.dao.entity.TreedemoSearchEntity;

public interface ITreedemoService extends IBaseBusinessService {
	public List<TreedemoEntity> search(TreedemoSearchEntity treedemoSearchEntity) throws Exception;

	public TreedemoEntity searchPK(TreedemoSearchEntity treedemoSearchEntity) throws Exception;

	public List<TreedemoEntity> searchAll(TreedemoSearchEntity treedemoSearchEntity) throws Exception;

	public long searchCount(TreedemoSearchEntity treedemoSearchEntity) throws Exception;

	public int insert(TreedemoEntity treedemoEntity) throws Exception;

	public void batchInsert(List<TreedemoEntity> treedemoEntity) throws Exception;

	public int update(TreedemoEntity treedemoEntity) throws Exception;

	public void batchUpdate(List<TreedemoEntity> treedemoEntity) throws Exception;

	public int delete(TreedemoEntity treedemoEntity) throws Exception;

	public int delete(List<TreedemoEntity> list) throws Exception;

	public int deleteAll(TreedemoEntity treedemoEntity) throws Exception;

	public void batchDelete(List<TreedemoEntity> treedemoEntity) throws Exception;

	public void saveTree(List<TreedemoEntity> treedemoEntity) throws Exception;
}