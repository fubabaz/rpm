package com.b2en.rpm.config.dao.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;

import com.b2en.framework.config.PropertyManager;
import com.b2en.framework.dao.ifs.ICommonDao;
import com.b2en.framework.dao.ifs.ICommonDaoFactory;
import com.b2en.rpm.config.exception.BizDaoException;

public class MyBatisDaoFactory implements ICommonDaoFactory {

	private MyBatisCommonDao myBatisCommonDao;
	
	public MyBatisDaoFactory(){
		try {
			Reader reader = Resources.getResourceAsReader(PropertyManager.getProperty("mybatis.config.file"));
			SqlSessionManager sqlSessionManager = SqlSessionManager.newInstance(reader, PropertyManager.getProperties());
			this.myBatisCommonDao = new MyBatisCommonDao(sqlSessionManager);
		} catch (IOException e) {
			throw new BizDaoException("mybatis.config.file ==>"+PropertyManager.getProperty("mybatis.config.file"), e);
		}
	}
	
	@Override
	public ICommonDao getDao(){
		return this.myBatisCommonDao;
	}
}
