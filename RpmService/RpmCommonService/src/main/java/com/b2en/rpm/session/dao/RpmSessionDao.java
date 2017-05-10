package com.b2en.rpm.session.dao;

import java.util.List;
import java.util.Map;

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

public interface RpmSessionDao {

	public List<SessionViewInfo> selectSessionViewerList();
	
	public String selectSqlFullTextInfo(SessionViewInfo sessionViewInfo);

	public List<SessionStatisticsInfo> selectSqlPlanInfo(SessionViewInfo sessionViewInfo);
	
	public List<SessionStatisticsInfo> selectStatisticsList(SessionViewInfo sessionViewInfo);
	
	public List<AshSampleTimeResultVO> selectAshSampleTimeList();	
	public List<AshSampleTimeResultVO> selectAwrSampleTimeList();
	
	public List<AshMetricNameResultVO> selectAshMetricNameList();
	public List<AshMetricNameResultVO> selectAwrMetricNameList();
	
	
	public List<InstanceNameSearchResultVO> selectInstanceNameList();
	
	/**
	 * 
	 * @param paramVO
	 * @return
	 */
	public Map<String, Object> selectAshActivityList(AshActivitySearchParamVO paramVO);
	public Map<String, Object> selectAwrActivityList(AshActivitySearchParamVO paramVO);
	
	public List<AshSysmetricHistorySearchResultVO> selectAshSysmetricHistory(AshSysmetricHistorySearchParamVO paramVO);
	
	/**
	 * 
	 * @param paramVO
	 * @return
	 */
	public List<AshActivityDetailSearchResultVO> selectAshActivityDetail(AshActivityDetailSearchParamVO paramVO);
	public List<AshActivityDetailSearchResultVO> selectAwrActivityDetail(AshActivityDetailSearchParamVO paramVO);
	
	public List<AshActivitySessionSearchResultVO> selectAshActivitySession(AshActivitySessionSearchParamVO paramVO);
	
	public List<AshActivitySqlSearchResultVO> selectAshActivitySql(AshActivitySqlSearchParamVO paramVO);
}
