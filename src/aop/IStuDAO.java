package aop;

import java.sql.SQLException;

public interface IStuDAO {
	public void addStudent(String name) throws SQLException;
	
	public void delStudent(int id) throws SQLException;
}
