package com.b2en.rpm.session.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.b2en.framework.dao.AbstractCommonDao;
import com.b2en.rpm.session.dao.RpmSessionDao;
import com.b2en.rpm.session.vo.SessionStatisticsInfo;
import com.b2en.rpm.session.vo.SessionViewInfo;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivityDetailSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySessionSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchParamVO;
import com.b2en.rpm.viewpart.vo.AshActivitySqlSearchResultVO;
import com.b2en.rpm.viewpart.vo.AshMetricNameResultVO;
import com.b2en.rpm.viewpart.vo.AshSampleTimeResultVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchParamVO;
import com.b2en.rpm.viewpart.vo.AshSysmetricHistorySearchResultVO;
import com.b2en.rpm.viewpart.vo.InstanceNameSearchResultVO;

public class RpmSessionDaoImpl extends AbstractCommonDao implements RpmSessionDao {
	
	public List<SessionViewInfo> selectSessionViewerList() {
		return selectList("SqlMapRpmSessionDao.selectSessionViewerList", null);
	}
	
	public String selectSqlFullTextInfo(SessionViewInfo sessionViewInfo) {
		return selectOne("SqlMapRpmSessionDao.selectSqlFullTextInfo", sessionViewInfo);
	}
	
	
	public List<SessionStatisticsInfo> selectSqlPlanInfo(SessionViewInfo sessionViewInfo) {
		return selectList("SqlMapRpmSessionDao.selectSqlPlanInfo", sessionViewInfo);
	}
	
	public List<SessionStatisticsInfo> selectStatisticsList(SessionViewInfo sessionViewInfo) {
		return selectList("SqlMapRpmSessionDao.selectStatisticsList", sessionViewInfo);
	}
	
	/**
	 * 
	 */
	public List<AshSampleTimeResultVO> selectAshSampleTimeList() { 	return selectList("SqlMapRpmSessionDao.selectAshSampleTimeList", null); }
	public List<AshSampleTimeResultVO> selectAwrSampleTimeList() { return selectList("SqlMapRpmSessionDao.selectAwrSampleTimeList", null); }

	/**
	 * 
	 */
	public List<AshMetricNameResultVO> selectAshMetricNameList() { return selectList("SqlMapRpmSessionDao.selectAshMetricNameList", null);}
	public List<AshMetricNameResultVO> selectAwrMetricNameList() { return selectList("SqlMapRpmSessionDao.selectAwrMetricNameList", null);}

	/**
	 * 
	 */
	/**
	 * 
	 */
	public List<InstanceNameSearchResultVO> selectInstanceNameList() {
		return selectList("SqlMapRpmSessionDao.selectInstanceNameList", null);	
	}

	/**
	 * 
	 */
	public Map<String, Object> selectAshActivityList(AshActivitySearchParamVO paramVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		
		resultMap.put("AshActivityList", selectList("SqlMapRpmSessionDao.selectAshActivityList", paramVO));
		resultMap.put("AshSysmetricHistory", selectList("SqlMapRpmSessionDao.selectAshSysmetricHistory", paramVO.getAshSysmetricHistorySearchParamVO()));
		
		return resultMap;
	}
	public Map<String, Object> selectAwrActivityList(AshActivitySearchParamVO paramVO) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("AwrActivityList", selectList("SqlMapRpmSessionDao.selectAwrActivityList", paramVO));
		resultMap.put("AwrSysmetricHistory", selectList("SqlMapRpmSessionDao.selectAshSysmetricHistory", paramVO.getAshSysmetricHistorySearchParamVO()));
		
		return resultMap ;		
		
	}

	@Override
	public List<AshSysmetricHistorySearchResultVO> selectAshSysmetricHistory(AshSysmetricHistorySearchParamVO paramVO) {
		return selectList("SqlMapRpmSessionDao.selectAshSysmetricRealTime", paramVO);
	}
	
	

	/**
	 * 
	 */
	public List<AshActivityDetailSearchResultVO> selectAshActivityDetail(AshActivityDetailSearchParamVO paramVO) { 	return selectList("SqlMapRpmSessionDao.selectAshActivityDetail", paramVO);}
	public List<AshActivityDetailSearchResultVO> selectAwrActivityDetail(AshActivityDetailSearchParamVO paramVO) { 	return selectList("SqlMapRpmSessionDao.selectAwrActivityDetail", paramVO);}

	@Override
	public List<AshActivitySessionSearchResultVO> selectAshActivitySession(AshActivitySessionSearchParamVO paramVO) {
		return selectList("SqlMapRpmSessionDao.selectAshActivitySession", paramVO); 
	}

	@Override
	public List<AshActivitySqlSearchResultVO> selectAshActivitySql(	AshActivitySqlSearchParamVO paramVO) {
		return selectList("SqlMapRpmSessionDao.selectAshActivitySql", paramVO);  
	}


	
	
}
