package com.b2en.rpm.config.dao;

import java.util.HashMap;
import java.util.Map;

import com.b2en.common.dao.IBizDaoFactory;
import com.b2en.rpm.common.dao.RpmCommonDao;
import com.b2en.rpm.common.dao.impl.RpmCommonDaoImpl;
import com.b2en.rpm.config.exception.BizDaoException;
import com.b2en.rpm.session.dao.RpmSessionDao;
import com.b2en.rpm.session.dao.impl.RpmSessionDaoImpl;
import com.b2en.rpm.sqlviewer.dao.RpmSqlViewerDao;
import com.b2en.rpm.sqlviewer.dao.impl.RpmSqlViewerDaoImpl;

public class BizDaoFactory implements IBizDaoFactory {
	
	private static Map<Class<?>,Class<?>> daoMap;
	
	private enum DAO_CLASS_INFO{
		RPM_COMMON(RpmCommonDao.class, RpmCommonDaoImpl.class)
		,RPM_SESSION(RpmSessionDao.class, RpmSessionDaoImpl.class)
		,RPM_SQLVIEWER(RpmSqlViewerDao.class, RpmSqlViewerDaoImpl.class)
		;
		private Class<?> interfaceClass;
		private Class<?> implementClass;
		private DAO_CLASS_INFO(Class<?> interfaceClass, Class<?> implementClass ){
			this.interfaceClass = interfaceClass;
			this.implementClass = implementClass;
		}	
	}
	
	static {
		daoMap = new HashMap<Class<?>,Class<?>>();
		for(DAO_CLASS_INFO daoClassInfo : DAO_CLASS_INFO.values()){
			daoMap.put(daoClassInfo.interfaceClass, daoClassInfo.implementClass);
		}
	}
	
	@Override
	public Object getDao(Class<?> interfaceClass){
		Object dao = null;
		try {
			dao = daoMap.get(interfaceClass).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BizDaoException("interface class not found==>>"+interfaceClass.getName(), e);
		}
		return dao;
	}

}
