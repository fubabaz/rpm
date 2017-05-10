/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.dao;

import java.util.List;

import com.b2en.framework.dao.ifs.ICommonDao;

/**
 * AbstractCommonDao
 * 
 * @author ej.park
 *
 */
public abstract class AbstractCommonDao {

	private ICommonDao bizDao;

	public AbstractCommonDao(){
		this.bizDao = CommonDaoFactoryBuilder.getInstance().getDao();
	}
	
	protected <E> List<E> selectList(String sqlId, Object param) {
		return this.bizDao.selectList(sqlId, param);
	}
	
	protected <T> T selectOne(String sqlId, Object param) {
		return this.bizDao.selectOne(sqlId, param);
	}
	
	protected int insert(String sqlId, Object param) {
		return this.bizDao.insert(sqlId, param);
	}
	
	protected int update(String sqlId, Object param) {
		return this.bizDao.update(sqlId, param);
	}
	
	protected int delete(String sqlId, Object param) {
		return this.bizDao.delete(sqlId, param);
	}
	
	protected int insertBatch(String sqlId, List<?> list) {
		return this.bizDao.insertBatch(sqlId, list);
	} 
	
	protected int updateBatch(String sqlId, List<?> list) {
		return this.bizDao.updateBatch(sqlId, list);
	} 
	
	protected int deleteBatch(String sqlId, List<?> list) {
		return this.bizDao.deleteBatch(sqlId, list);
	}
}
