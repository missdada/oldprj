package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.CGoodsInfoEntity;
import manytag.business.dao.entity.CGoodsInfoSearchEntity;

public interface ICGoodsInfoService extends IBaseBusinessService {
	public List<CGoodsInfoEntity> search(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception;

	public CGoodsInfoEntity searchPK(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception;

	public List<CGoodsInfoEntity> searchAll(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception;

	public long searchCount(CGoodsInfoSearchEntity cGoodsInfoSearchEntity) throws Exception;

	public int insert(CGoodsInfoEntity cGoodsInfoEntity) throws Exception;

	public void batchInsert(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception;

	public int update(CGoodsInfoEntity cGoodsInfoEntity) throws Exception;

	public void batchUpdate(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception;

	public int delete(CGoodsInfoEntity cGoodsInfoEntity) throws Exception;

	public int delete(List<CGoodsInfoEntity> list) throws Exception;

	public int deleteAll(CGoodsInfoEntity cGoodsInfoEntity) throws Exception;

	public void batchDelete(List<CGoodsInfoEntity> cGoodsInfoEntity) throws Exception;
}