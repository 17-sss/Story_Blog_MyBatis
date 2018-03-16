package com.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class DiaryDBMyBatis extends MybatisConnector {
	private final String namespace = "diary.mybatis";
	private static DiaryDBMyBatis instance = new DiaryDBMyBatis();
	private DiaryDBMyBatis() {}
	public static DiaryDBMyBatis getInstance() {
		return instance;
	}
	SqlSession sqlSession;
	
	// 각 일기장의 일기 수
	public int getDiaryCount(String diaryid, String email) {
		int x = 0;
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("diaryid", diaryid);
		map.put("email", email);
		x = sqlSession.selectOne(namespace+".getDiaryCount", map);
		sqlSession.close();
		return x;
	}
	
	// 일기(목록) 가져오기
	public List getDiaries(int startRow, int endRow, String email, String diaryid) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("email", email);
		map.put("diaryid", diaryid);
		List li = sqlSession.selectList(namespace + ".getDiaries" ,map);
		sqlSession.close();
		return li;
	}
	
	// 각 일기장의 사진 전체 갯수 카운트
	public int getImgDiaryCountTotal(String diaryid, String email) {
		int x = 0;
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("diaryid", diaryid);
		map.put("email", email);
		x = sqlSession.selectOne(namespace+".getImgDiaryCountTotal", map);
		sqlSession.close();
		return x;
	}	
}
