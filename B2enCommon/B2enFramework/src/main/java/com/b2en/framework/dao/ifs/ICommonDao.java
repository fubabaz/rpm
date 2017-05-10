/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.dao.ifs;

import java.util.List;

/**
 * ICommonDao
 * 
 * @author ej.park
 *
 */
public interface ICommonDao {

	public <E> List<E> selectList(String sqlId, Object param);
	
	public <T> T selectOne(String sqlId, Object param);
	
	public int insert(String sqlId, Object param);
	
	public int update(String sqlId, Object param);
	
	public int delete(String sqlId, Object param);
	
	public int insertBatch(String sqlId, List<?> list); 
	
	public int updateBatch(String sqlId, List<?> list); 
	
	public int deleteBatch(String sqlId, List<?> list);
	
}
