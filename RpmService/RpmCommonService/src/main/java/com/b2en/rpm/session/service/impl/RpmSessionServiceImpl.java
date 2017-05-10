package com.b2en.rpm.session.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.b2en.common.service.AbstractCommonService;
import com.b2en.rpm.session.dao.RpmSessionDao;
import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.session.vo.SessionStatisticsInfo;
import com.b2en.rpm.session.vo.SessionViewInfo;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshMetricNameResultVO;
import com.b2en.rpm.viewpart.vo.AshSampleTimeResultVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchResultVO;
import com.b2en.rpm.viewpart.vo.InstanceNameSearchResultVO;

public class RpmSessionServiceImpl extends AbstractCommonService implements RpmSessionService {

	private Logger logger = Logger.getLogger(this.getClass());
	private RpmSessionDao rpmSessionDao;
	
	public RpmSessionServiceImpl(){
		this.rpmSessionDao = (RpmSessionDao)getDao(RpmSessionDao.class);
	}



	public List<SessionViewInfo> getSessionViewerList() {
		return rpmSessionDao.selectSessionViewerList();
	}
	


	public 	Map<String, Object> getSqlFullTextInfo(SessionViewInfo sessionViewInfo){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
							 					
			resultMap.put("sql",rpmSessionDao.selectSqlFullTextInfo(sessionViewInfo));		
			resultMap.put("plan",rpmSessionDao.selectSqlPlanInfo(sessionViewInfo));		
		
		return	resultMap;
		
	}

	public List<SessionStatisticsInfo> getStatisticsList(SessionViewInfo sessionViewInfo){
		return rpmSessionDao.selectStatisticsList(sessionViewInfo);
	}
	
	
	
	/**
	 * 샘플타임리스트 조회
	 * @return
	 */	
	public List<AshSampleTimeResultVO> getAshSampleTimeList() {	return rpmSessionDao.selectAshSampleTimeList();}	
	public List<AshSampleTimeResultVO> getAwrSampleTimeList() {return rpmSessionDao.selectAwrSampleTimeList();}
	
	/**
	 * 메트릭명 리스트 조회
	 * @return
	 */	
	public List<AshMetricNameResultVO> getAshMetricNameList() {return rpmSessionDao.selectAshMetricNameList();}
	public List<AshMetricNameResultVO> getAwrMetricNameList() {return rpmSessionDao.selectAwrMetricNameList();}


	/**
	 * 
	 */
	public List<InstanceNameSearchResultVO> getInstanceNameList() {
		return rpmSessionDao.selectInstanceNameList();
	}


	@Override
	public 	Map<String, Object>  getAshActivityList(AshActivitySearchParamVO paramVO) {	
		return  rpmSessionDao.selectAshActivityList(paramVO);
	}
	
	
	@Override
	public 	Map<String, Object>  getAwrActivityList(AshActivitySearchParamVO paramVO) {	
									
		return rpmSessionDao.selectAwrActivityList(paramVO);
	}


	
	@Override
	public List<AshSysmetricHistorySearchResultVO> getAshSysmetricHistory(AshSysmetricHistorySearchParamVO paramVO){
			return rpmSessionDao.selectAshSysmetricHistory(paramVO);		
	}



	/**
	 * 
	 */
	public Map<String, Object> getAshActivityDetail(AshActivityDetailSearchParamVO paramVO) { 	
	
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap.put("detail", rpmSessionDao.selectAshActivityDetail(paramVO));
		resultMap.put("sesiion", rpmSessionDao.selectAshActivitySession( paramVO.getAshActivitySessionSearchParamVO()));
		resultMap.put("sql", rpmSessionDao.selectAshActivitySql( paramVO.getAshActivitySqlSearchParamVO()));
		
		return resultMap; 
	
	}
	
	public List<AshActivityDetailSearchResultVO> getAwrActivityDetail(AshActivityDetailSearchParamVO paramVO) { 	return rpmSessionDao.selectAwrActivityDetail(paramVO); }



	@Override
	public List<AshActivitySessionSearchResultVO> getAshActivitySession(AshActivitySessionSearchParamVO paramVO) {
		return rpmSessionDao.selectAshActivitySession(paramVO);	
	}



	@Override
	public List<AshActivitySqlSearchResultVO> getAshActivitySql(AshActivitySqlSearchParamVO paramVO) {
		return rpmSessionDao.selectAshActivitySql(paramVO);
	}
}
