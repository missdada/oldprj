package manytag.business.service;

import java.util.List;
import manytag.business.base.IBaseBusinessService;
import manytag.business.dao.entity.CEmpEntity;
import manytag.business.dao.entity.CEmpSearchEntity;

public interface ICEmpService extends IBaseBusinessService {
	public List<CEmpEntity> search(CEmpSearchEntity cEmpSearchEntity) throws Exception;

	public CEmpEntity searchPK(CEmpSearchEntity cEmpSearchEntity) throws Exception;

	public List<CEmpEntity> searchAll(CEmpSearchEntity cEmpSearchEntity) throws Exception;

	public long searchCount(CEmpSearchEntity cEmpSearchEntity) throws Exception;

	public int insert(CEmpEntity cEmpEntity) throws Exception;

	public void batchInsert(List<CEmpEntity> cEmpEntity) throws Exception;

	public int update(CEmpEntity cEmpEntity) throws Exception;

	public void batchUpdate(List<CEmpEntity> cEmpEntity) throws Exception;

	public int delete(CEmpEntity cEmpEntity) throws Exception;

	public int delete(List<CEmpEntity> list) throws Exception;

	public int deleteAll(CEmpEntity cEmpEntity) throws Exception;

	public void batchDelete(List<CEmpEntity> cEmpEntity) throws Exception;
}