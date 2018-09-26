package manytag.framework.dao.mybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import manytag.framework.util.StringUtils;
import manytag.framework.util.UUIDBuilder;

@Repository
public class BaseDAO extends SqlSessionDaoSupport {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private SqlSessionTemplate sqlSessionTemplate;

	private java.util.concurrent.ConcurrentMap<String, SqlSession> sqlSessionMap = new java.util.concurrent.ConcurrentHashMap<String, SqlSession>();

	/**
	 * 清除查询缓存.
	 */
	public void clearCache() {
		sqlSessionTemplate.clearCache();
	}

	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sqlid, Object paramObj) {
		return (List<T>) this.getSqlSession().selectList(sqlid, paramObj);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sqlid) {
		return (List<T>) this.getSqlSession().selectList(sqlid);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> selectList(String sqlid, Object paramObj, RowBounds arg3) {
		return (List<T>) this.getSqlSession().selectList(sqlid, paramObj, arg3);
	}

	@SuppressWarnings("unchecked")
	public <T> T selectOne(String sqlid) {
		return (T) this.getSqlSession().selectOne(sqlid);
	}

	@SuppressWarnings("unchecked")
	public <T> T selectOne(String sqlid, Object paramObj) {
		return (T) this.getSqlSession().selectOne(sqlid, paramObj);
	}

	@SuppressWarnings("rawtypes")
	public Map selectMap(String arg0, String arg1) {
		return this.getSqlSession().selectMap(arg0, arg1);
	}

	@SuppressWarnings("rawtypes")
	public Map selectMap(String arg0, Object arg1, String arg2) {
		return this.getSqlSession().selectMap(arg0, arg1, arg2);
	}

	@SuppressWarnings("rawtypes")
	public Map selectMap(String arg0, Object arg1, String arg2, RowBounds arg3) {
		return this.getSqlSession().selectMap(arg0, arg1, arg2, arg3);
	}

	public int delete(String sqlid) {
		return this.getSqlSession().delete(sqlid);
	}

	public int delete(String sqlid, Object paramObj) {
		return this.getSqlSession().delete(sqlid, paramObj);
	}

	public int insert(String sqlid, Object paramObj) {
		return this.getSqlSession().insert(sqlid, paramObj);
	}

	public int insert(String sqlid) {
		return this.getSqlSession().insert(sqlid);
	}

	public void select(String sqlid, ResultHandler<?> arg1) {
		this.getSqlSession().select(sqlid, arg1);
	}

	public void select(String sqlid, Object paramObj, ResultHandler<?> arg1) {
		this.getSqlSession().select(sqlid, paramObj, arg1);
	}

	public void select(String sqlid, Object paramObj, RowBounds arg3, ResultHandler<?> arg1) {
		this.getSqlSession().select(sqlid, paramObj, arg3, arg1);
	}

	public int update(String sqlid, Object paramObj) {
		return this.getSqlSession().update(sqlid, paramObj);
	}

	public int update(String sqlid) {
		return this.getSqlSession().update(sqlid);
	}

	@SuppressWarnings("rawtypes")
	public void batchUpdate(final String statementName, final List list) throws DataAccessException {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		try {
			if (null != list && list.size() > 0) {
				int size = 10000;

				for (int i = 0, n = list.size(); i < n; i++) {
					session.update(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						session.commit();
						session.clearCache();
					}
				}
			}
		} catch (Exception e) {
			session.rollback();
			if (log.isDebugEnabled()) {
				e.printStackTrace();
				log.debug("batchUpdate error: id [" + statementName + "], parameterObject [" + list + "].  Cause: " + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public void batchInsert(final String statementName, final List list) throws DataAccessException {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

		int size = 10000;
		try {
			if (null != list && list.size() > 0) {
				for (int i = 0, n = list.size(); i < n; i++) {
					session.insert(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						session.commit();
						session.clearCache();
					}
				}
				session.commit();
				session.clearCache();
			}
		} catch (Exception e) {
			session.rollback();
			if (log.isDebugEnabled()) {
				e.printStackTrace();
				log.debug("batchInsert error: id [" + statementName + "], parameterObject [" + list + "].  Cause: " + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("rawtypes")
	public void batchDelete(final String statementName, final List list) throws DataAccessException {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

		int size = 10000;
		try {
			if (null != list && list.size() > 0) {
				for (int i = 0, n = list.size(); i < n; i++) {
					session.delete(statementName, list.get(i));
					if (i % 1000 == 0 || i == size - 1) {
						session.commit();
						session.clearCache();
					}
				}
			}
		} catch (Exception e) {
			session.rollback();
			if (log.isDebugEnabled()) {
				e.printStackTrace();
				log.debug("batchDelete error: id [" + statementName + "], parameterObject [" + list + "].  Cause: " + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	public String createSession(ExecutorType execType, boolean autoCommit) {
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(execType, autoCommit);
		String UUID = UUIDBuilder.getUUID();
		sqlSessionMap.put(UUID, session);
		return UUID;
	}

	private SqlSession getSessionById(String sessionid) {
		if (StringUtils.checkBlank(sessionid)) {
			return null;
		}
		return sqlSessionMap.get(sessionid);
	}

	private void removeSession(String sessionid) {
		if (StringUtils.checkBlank(sessionid)) {
			return;
		}
		sqlSessionMap.remove(sessionid);
	}

	public void sessionCommit(String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			session.commit();
			session.clearCache();
		}
	}

	public void sessionRollback(String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			session.rollback();
		}
	}

	public void sessionClose(String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			session.close();
			removeSession(sessionid);
		}
	}

	public int delete(String sqlid, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.delete(sqlid);
		}
		return delete(sqlid);
	}

	public int delete(String sqlid, Object paramObj, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.delete(sqlid, paramObj);
		}
		return delete(sqlid, paramObj);
	}

	public int insert(String sqlid, Object paramObj, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.insert(sqlid, paramObj);
		}
		return insert(sqlid, paramObj);
	}

	public int insert(String sqlid, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.insert(sqlid);
		}
		return insert(sqlid);
	}

	public int update(String sqlid, Object paramObj, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.update(sqlid, paramObj);
		}
		return update(sqlid, paramObj);
	}

	public int update(String sqlid, String sessionid) {
		SqlSession session = getSessionById(sessionid);
		if (session != null) {
			return session.update(sqlid);
		}
		return update(sqlid);
	}
}