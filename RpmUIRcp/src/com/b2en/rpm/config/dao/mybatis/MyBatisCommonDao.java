package com.b2en.rpm.config.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionManager;

import com.b2en.framework.dao.ifs.ICommonDao;
import com.b2en.rpm.config.exception.BizDaoException;

public class MyBatisCommonDao implements ICommonDao {

	private SqlSessionManager sqlSessionManager;
	
	public MyBatisCommonDao(SqlSessionManager sqlSessionManager){
		this.sqlSessionManager = sqlSessionManager;
	}
	
	@Override
	public <E> List<E> selectList(String sqlId, Object param) {
		List<E> result = null;
		SqlSession session = this.sqlSessionManager.openSession();
		try{
			result = session.selectList(sqlId, param);
		}catch(Exception e){
			throw new BizDaoException("selectList>>"+sqlId, e);
		}finally {
		    session.close();
		}
		return result;
	}

	@Override
	public <T> T selectOne(String sqlId, Object param) {
		T result = null;
		SqlSession session = this.sqlSessionManager.openSession();
		try{
			result = session.selectOne(sqlId, param);
		}catch(Exception e){
			throw new BizDaoException("selectOne>>"+sqlId, e);
		}finally {
		    session.close();
		}
		return result;
	}

	@Override
	public int insert(String sqlId, Object param) {
		int result = 0;
		try{
			result = this.sqlSessionManager.openSession(true).insert(sqlId, param);
		}catch(Exception e){
			throw new BizDaoException("insert>>"+sqlId, e);
		}
		return result;
	}

	@Override
	public int update(String sqlId, Object param) {
		int result = 0;
		try{
			result = this.sqlSessionManager.openSession(true).update(sqlId, param);
		}catch(Exception e){
			throw new BizDaoException("update>>"+sqlId, e);
		}
		return result;
	}

	@Override
	public int delete(String sqlId, Object param) {
		int result = 0;
		try{
			result = this.sqlSessionManager.openSession(true).delete(sqlId, param);
		}catch(Exception e){
			throw new BizDaoException("delete>>"+sqlId, e);
		}
		return result;
	}

	@Override
	public int insertBatch(String sqlId, List<?> list) {
		int result = 0;
		try{
			SqlSession batchSqlsession = sqlSessionManager.openSession(ExecutorType.BATCH , false);
			if(list!=null){
				for(Object o : list){
					result =+ batchSqlsession.insert(sqlId, o);
				}
			}
			sqlSessionManager.commit();
		}catch(Exception e){
			throw new BizDaoException("insertBatch>>"+sqlId, e);
		}finally{
			sqlSessionManager.rollback();	
		}
		return result;
	}

	@Override
	public int updateBatch(String sqlId, List<?> list) {
		int result = 0;
		try{
			SqlSession batchSqlsession = sqlSessionManager.openSession(ExecutorType.BATCH , false);
			if(list!=null){
				for(Object o : list){
					result =+ batchSqlsession.update(sqlId, o);
				}
			}
			sqlSessionManager.commit();
		}catch(Exception e){
			throw new BizDaoException("updateBatch>>"+sqlId, e);
		}finally{
			sqlSessionManager.rollback();	
		}
		return result;
	}

	@Override
	public int deleteBatch(String sqlId, List<?> list) {
		int result = 0;
		try{
			SqlSession batchSqlsession = sqlSessionManager.openSession(ExecutorType.BATCH , false);
			if(list!=null){
				for(Object o : list){
					result =+ batchSqlsession.delete(sqlId, o);
				}
			}
			sqlSessionManager.commit();
		}catch(Exception e){
			throw new BizDaoException("deleteBatch>>"+sqlId, e);
		}finally{
			sqlSessionManager.rollback();	
		}
		return result;
	}
	
	
}
