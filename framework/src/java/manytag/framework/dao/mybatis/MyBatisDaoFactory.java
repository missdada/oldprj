package manytag.framework.dao.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;

public class MyBatisDaoFactory {
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		DaoFactory.getInstance().init(sqlSessionFactory);
	}
}