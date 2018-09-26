package manytag.framework.dao.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;

public class DaoFactory {
	private static DaoFactory instance = new DaoFactory();
	protected SqlSessionFactory sqlSessionFactory;

	public static DaoFactory getInstance() {
		return instance;
	}

	public void init(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return this.sqlSessionFactory;
	}
}