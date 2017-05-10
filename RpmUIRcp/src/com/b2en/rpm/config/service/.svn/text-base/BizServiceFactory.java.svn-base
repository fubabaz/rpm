package com.b2en.rpm.config.service;

import java.util.HashMap;
import java.util.Map;

import com.b2en.common.service.IBizServiceFactory;
import com.b2en.rpm.common.service.RpmCommonService;
import com.b2en.rpm.common.service.impl.RpmCommonServiceImpl;
import com.b2en.rpm.config.exception.BizServiceException;
import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.session.service.impl.RpmSessionServiceImpl;
import com.b2en.rpm.sqlviewer.service.RpmSqlViewerService;
import com.b2en.rpm.sqlviewer.service.impl.RpmSqlViewerServiceImpl;

public class BizServiceFactory implements IBizServiceFactory {
	
	private static Map<Class<?>,Class<?>> serviceMap;
	
	private enum SERVICE_CLASS_INFO {
		COMMON(RpmCommonService.class, RpmCommonServiceImpl.class)
		,SESSION(RpmSessionService.class, RpmSessionServiceImpl.class)
		,SQLVIEWER(RpmSqlViewerService.class, RpmSqlViewerServiceImpl.class)
		;
		private Class<?> interfaceClass;
		private Class<?> implementClass;
		private SERVICE_CLASS_INFO(Class<?> interfaceClass, Class<?> implementClass ){
			this.interfaceClass = interfaceClass;
			this.implementClass = implementClass;
		}
	}
	
	static {
		serviceMap = new HashMap<Class<?>,Class<?>>();
		for(SERVICE_CLASS_INFO serviceClassInfo : SERVICE_CLASS_INFO.values()){
			serviceMap.put(serviceClassInfo.interfaceClass, serviceClassInfo.implementClass);
		}
	}
	
	@Override
	public Object getService(Class<?> interfaceClass){
		Object service = null;
		try {
			service = serviceMap.get(interfaceClass).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BizServiceException("interface class not found==>>"+interfaceClass.getName(), e);
		}
		return service;
	}

}
