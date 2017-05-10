package com.b2en.rpm.session.service;

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

public interface RpmSessionService {

	public List<SessionViewInfo> getSessionViewerList();
	
	public Map<String, Object>  getSqlFullTextInfo(SessionViewInfo sessionViewInfo);
	
	public List<SessionStatisticsInfo> getStatisticsList(SessionViewInfo sessionViewInfo);
	
	/**
	 * 샘플타임리스트 조회
	 * @return
	 */
	public List<AshSampleTimeResultVO> getAshSampleTimeList();
	public List<AshSampleTimeResultVO> getAwrSampleTimeList();
	
	/**
	 * 메트릭명 리스트 조회
	 * @return
	 */
	public List<AshMetricNameResultVO> getAshMetricNameList();
	public List<AshMetricNameResultVO> getAwrMetricNameList();
	
	/**
	 * 인스턴스명 리스트 조회
	 * @return
	 */		
	public List<InstanceNameSearchResultVO> getInstanceNameList();
	
	
	/**
	 * 
	 * @return
	 */
	public 	Map<String, Object>  getAshActivityList(AshActivitySearchParamVO paramVO);
	public 	Map<String, Object>  getAwrActivityList(AshActivitySearchParamVO paramVO);
	
	
	public List<AshSysmetricHistorySearchResultVO> getAshSysmetricHistory(AshSysmetricHistorySearchParamVO paramVO);
	
	/**
	 * 
	 * @param paramVO
	 * @return
	 */
	public Map<String, Object> getAshActivityDetail(AshActivityDetailSearchParamVO paramVO);
	public List<AshActivityDetailSearchResultVO> getAwrActivityDetail(AshActivityDetailSearchParamVO paramVO);
	
	
	
	
	public List<AshActivitySessionSearchResultVO> getAshActivitySession(AshActivitySessionSearchParamVO paramVO);
	
	public List<AshActivitySqlSearchResultVO> getAshActivitySql(AshActivitySqlSearchParamVO paramVO);

}
