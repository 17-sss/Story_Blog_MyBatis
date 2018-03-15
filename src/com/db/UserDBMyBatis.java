package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class UserDBMyBatis extends UserConnector {
	private final String namespace = "user.mybatis";
	private static UserDBMyBatis instance = new UserDBMyBatis();
	private UserDBMyBatis() {}
	public static UserDBMyBatis getInstance() {
		return instance;
	}
	
	SqlSession sqlSession;
	
	// ȸ�� �� �޼ҵ�
	public int getUserCount() {
		int x = 0;
		sqlSession=sqlSession();
		x = sqlSession.selectOne(namespace+".getUserCount");
		sqlSession.close();
		return x;
	}
	
	
	// ȸ������Ʈ ������ �޼ҵ�?
	public List getUsers(int startRow, int endRow) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List li = sqlSession.selectList(namespace + ".getUsers",map);
		sqlSession.close();
		return li;
	}
	
	// ȸ�� �����Ҷ� ���� �ҷ�����
	public UserDataBean getUser(String email, String pwd) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// ȸ�� �����Ҷ� ���� �ҷ�����_2
	public UserDataBean getUser (String email) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		
		UserDataBean user = sqlSession.selectOne(namespace+".getUser", map);
		
		sqlSession.commit();
		sqlSession.close();
		
		return user;
	}
	
	// ȸ�� Update
	public int updateUser (UserDataBean user) {
		sqlSession= sqlSession();
		int chk = sqlSession.update(namespace+".updateUser", user);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	}
	
	// ȸ�� ���� Ż��
	public int deleteUser (String email, String pwd) throws Exception {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		int chk = sqlSession.delete(namespace+".deleteUser", map);
		sqlSession.commit();
		sqlSession.close();
		
		return chk;
	} 
	
	
	// ȸ������ (�߰�) �޼ҵ�
	public void insertUser(UserDataBean user) {
		sqlSession= sqlSession();
		sqlSession.insert(namespace + ".insertUser" ,user);
		sqlSession.commit();
		sqlSession.close();
	}
	
	// �̸��� �ߺ�Ȯ�� <<<<<MyBatis �̿�>>>>>
	public boolean confirmEmail(String email) {
		boolean result = false;
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		result = sqlSession.selectOne(namespace + ".confirmEmail", map);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
    }
	
	// �α��� Ȯ�� <<<<<MyBatis �̿�>>>>>
	public int loginCheck(String email, String pwd) {
		int x=-1;
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		x = sqlSession.selectOne(namespace+".loginCheck", map);
		sqlSession.commit();
		sqlSession.close();
		
		return x;
	} 
	
	  //select pwd from userlist where email=#{email}
	 
	           
}
