package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class UserDBMyBatis extends MybatisConnector {
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
	
	// �̸��� �ߺ�Ȯ�� 
	public boolean confirmEmail(String email) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		Map map2 = new HashMap();
		map.put("email", email);
		boolean chk=true;
		
		map2=sqlSession.selectOne(namespace+".confirmEmail",map);
		
		if (map2!=null) {
			chk=true;
		}else {
			chk=false;
		}
		sqlSession.close();
		
		return chk;
	}
	
	// �α��� üũ 
	 public int loginCheck(String email, String pwd) {
		sqlSession= sqlSession();
		Map map = new HashMap();
		map.put("email", email);
		map.put("pwd", pwd);
		int x = sqlSession.selectOne(namespace+".loginCheck", map);
		sqlSession.commit();
		sqlSession.close();
		
		return x;
	}
	 /*public int loginCheck(String email, String pwd) 
	    {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	 
	        String dbPW = ""; // db���� ���� ��й�ȣ�� ���� ����
	        int x = -1;
	 
	        try {
	            // ���� - ���� �Էµ� ���̵�� DB���� ��й�ȣ�� ��ȸ�Ѵ�.
	            StringBuffer query = new StringBuffer();
	            query.append("select pwd from userlist where email=?");
	 
	            conn = getConnection();
	            pstmt = conn.prepareStatement(query.toString());
	            pstmt.setString(1, email);
	            rs = pstmt.executeQuery();
	 
	            if (rs.next()) // �Էµ� ���̵� �ش��ϴ� ��� �������
	            {
	                dbPW = rs.getString("pwd"); // ����� ������ �ִ´�.
	 
	                if (dbPW.equals(pwd)) 
	                    x = 1; // �Ѱܹ��� ����� ������ ��� ��. ������ ��������
	                else                  
	                    x = 0; // DB�� ��й�ȣ�� �Է¹��� ��й�ȣ �ٸ�, ��������
	                
	            } else {
	                x = -1; // �ش� ���̵� ���� ���
	            }
	 
	            return x;
	 
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        } finally {
	        	 close(conn, null, pstmt);
	        }
	    } // end loginCheck()
*/	           
}
