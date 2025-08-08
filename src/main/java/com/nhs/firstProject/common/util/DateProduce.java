package com.nhs.firstProject.common.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.FastDateFormat;

public class DateProduce {

	// 분(12H, 24H)
	// 시간(2, 3D, 1W, 2W, 1M)
	// 일(3M, 6M, 1Y)
	// 월(3Y, 5Y)
	/**
	 * 현재시간 이전 ~ 기간량 만큼의 증가분을 List로 출력한다.
	 * @param outFmt - 출력 Format (ex. yyyy-MM-dd[E] HH:mm:ss.SSS)
	 * @param increaseField - 증가 Field (ex. MINUTE, HOUR_OF_DAY, DAY_OF_MONTH, MONTH, YEAR)
	 * @param amountField - 기간량(Term) Field(ex. MINUTE, HOUR_OF_DAY, DAY_OF_MONTH, MONTH, YEAR)
	 * @param amount - 기간량
	 * @param isTimeList - TimeList 출력 여부
	 * @return List<String>
	 */
	private static Map<String, List<String>> getCalTimeList(String outFmt, int increaseField, int amountField, int amount, boolean isTimeList) {
			
		// 0. Return Variable 
		Map<String, List<String>> timeMap = new HashMap<>();
		List<String> startList = new ArrayList<>();
		List<String> endList = new ArrayList<>();
		List<String> timeList = new ArrayList<>();
		
		// 1. 현재일시 설정
		Calendar endCal = Calendar.getInstance();	// 현재일시
		long endTime = endCal.getTimeInMillis();
		
		endList.add(formatDateTime("yyyy-MM-dd HH:mm:ss", endTime));	// 검색 종료시간
		timeMap.put("endList", endList);
		//System.out.println(" 현재일시: " + formatDateTime(outFmt, endCal.getTimeInMillis()));
		
		// 2. 초기일시 설정 (현재일시 - amount)
		Calendar loopCal = getStartCalendar(endTime, increaseField, amountField, amount);
		long loopTime = loopCal.getTimeInMillis();
		//System.out.println(" 초기일시: " + formatDateTime(outFmt, loopCal.getTimeInMillis()));
		
		if ( !isTimeList ) {
			loopCal.add(increaseField, 1);
			loopTime = loopCal.getTimeInMillis();
			
			startList.add(formatDateTime("yyyy-MM-dd HH:mm:ss", loopTime));	// 검색 시작시간
			timeMap.put("startList", startList);
			
		} else {
			// 3. increaseField 1씩 증가 Loop
			int index = 0;
			while( loopTime < endTime) {
				loopCal.add(increaseField, 1);
				loopTime = loopCal.getTimeInMillis();
				String strLoopTime = formatDateTime(outFmt, loopTime);
	
				//System.out.println(" 산출: "+ strLoopTime);
				timeList.add(strLoopTime);
				
				if ( index == 0 ) {
					startList.add(formatDateTime("yyyy-MM-dd HH:mm:ss", loopTime));	// 검색 시작시간
					timeMap.put("startList", startList);
					
				}
				index++;
			}
			timeMap.put("timeList", timeList);
		}
		
		return timeMap;
	}		
	
	/**
	 * 최초 시작일시를 얻는다.
	 * @param endTime
	 * @param increaseField
	 * @param amountField
	 * @param amount
	 * @return
	 */
	private static Calendar getStartCalendar(long endTime, int increaseField, int amountField, int amount) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTimeInMillis(endTime);
		startCal.add(amountField, amount);
		
		switch (increaseField) {
			case Calendar.MONTH:
				startCal.set(Calendar.DAY_OF_MONTH, 1);
			case Calendar.DAY_OF_MONTH:
				startCal.set(Calendar.HOUR_OF_DAY, 0);
			case Calendar.HOUR_OF_DAY:
				startCal.set(Calendar.MINUTE, 0);
			case Calendar.MINUTE:
				startCal.set(Calendar.SECOND, 0);
				startCal.set(Calendar.MILLISECOND, 0);
			default:
		}
		
		return startCal;
	}
	
	
	/**
	 * 시간 데이터를 패턴(yyyy-MM-dd[E] HH:mm:ss.SSS)에 맞게 String으로 돌려준다. 
	 * @param pattern - 출력 패턴
	 * @param lTime - 일시
	 * @return
	 */
	private static String formatDateTime( String pattern, long lTime ) {
		return FastDateFormat.getInstance(pattern).format(lTime) ;
	}
	
	/**
	 * 시간축의 List 를 검색하기 편리하게 하기 위해 Map으로 변환
	 * @param timeList
	 * @return
	 */
	public static Map<String, Integer> convertTimeList(List<String> timeList) {
		
		Map<String, Integer> timeKeyInfo = new HashMap<>();
		
		int index = 0;
		for(String strTime : timeList) {
			timeKeyInfo.put(strTime, index++);
		}
		
		return timeKeyInfo;
	}
	
	/** 현재 안쓰이고 있음
	 * 현재 시간과 현재 시간 - 설정한 시간 값을 출력한다.
	 * @param dateFormat - 날짜형
	 * @param dateTime - 설정한 시간
	 * @return
	 */
	public static Map<String, String> getStartEndDt(String dateFormat, int dateTime) {
		
		Map<String, String> startEndDtInfo = new HashMap<>();
		String endDt = ""; 
		String startDt = "";
		
		// 1. 현재일시 설정
		Calendar endCal = Calendar.getInstance();	// 현재일시
		Calendar startCal = Calendar.getInstance();
		long endTime = endCal.getTimeInMillis();
		long startTime = 0;
		endDt = formatDateTime("yyyy-MM-dd HH:mm:ss", endTime);
		
		startCal.setTimeInMillis(endTime);
		
		if(dateFormat.equals("hour")) {	// 12, 24
			startCal.add(Calendar.HOUR_OF_DAY, -dateTime);
		}else if (dateFormat.equals("day")) {
			startCal.add(Calendar.DAY_OF_MONTH, -dateTime);
		}else if (dateFormat.equals("month")) {
			startCal.add(Calendar.MONTH, -dateTime);
		}else if (dateFormat.equals("year")) {
			startCal.add(Calendar.YEAR, -dateTime);
		}
		
		startTime = startCal.getTimeInMillis();
		startDt = formatDateTime("yyyy-MM-dd HH:mm:ss", startTime);
		
		startEndDtInfo.put("START_DT", startDt);
		startEndDtInfo.put("END_DT", endDt);
		
		return startEndDtInfo;
	}
	
	/**
	 * 현재 시간과 현재 시간 - 설정한 시간 값을 출력한다.
	 * @param dateType - 그래프 x축에 나타낼 날짜 형식 (분, 시간, 일, 월)
	 * @return
	 */
	public static String getDateType(String dateType) {
		if(dateType.equals("minute")) {
			dateType = "%Y%m%d%H%i";
		}else if (dateType.equals("hour")) {
			dateType = "%Y%m%d%H";
		}else if (dateType.equals("day")) {
			dateType = "%Y%m%d";
		}else if (dateType.equals("month")) {
			dateType = "%Y%m";
		}
		return dateType;
	}
	
	/**
	 * 현재 시간과 현재 시간 - 설정한 시간 값을 출력한다.
	 * @param xAxisType - 그래프 x축에 나타낼 날짜 형식
	 * @param dateFormat - 출력범위 날짜 타입 (시간, 일, 주, 월, 년)
	 * @param dateTime - 출력 범위
	 * @param isTimeList - TimeList 출력 여부
	 * @return
	 */
	public static Map<String, List<String>> getTimeMap(String xAxisType, String dateFormat, int dateTime){
		return getTimeMap(xAxisType, dateFormat, dateTime, true);
	}
	public static Map<String, List<String>> getTimeMap(String xAxisType, String dateFormat, int dateTime, boolean isTimeList){
		Map<String, List<String>> timeMap = new HashMap<>();
		if(xAxisType.equals("minute")) {
			timeMap = DateProduce.getCalTimeList("dd일 HH:mm", Calendar.MINUTE, Calendar.HOUR_OF_DAY, -dateTime, isTimeList);	
		}else if(xAxisType.equals("hour")) {
			if(dateFormat.equals("day")) {
				timeMap = DateProduce.getCalTimeList("MM-dd HH시", Calendar.HOUR_OF_DAY, Calendar.DAY_OF_MONTH, -dateTime, isTimeList);
			}else {
				timeMap = DateProduce.getCalTimeList("MM-dd HH시", Calendar.HOUR_OF_DAY, Calendar.MONTH, -dateTime, isTimeList);
			}
			
		}else if(xAxisType.equals("day")) {
			timeMap = DateProduce.getCalTimeList("yyyy-MM-dd", Calendar.DAY_OF_MONTH, Calendar.MONTH, -dateTime, isTimeList);
		}else if(xAxisType.equals("month")) {
			timeMap = DateProduce.getCalTimeList("yyyy-MM", Calendar.MONTH, Calendar.YEAR, -dateTime, isTimeList);
		}
		return timeMap;
	}
}
