package aop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StuDAO implements IStuDAO {
	public void addStudent(String name) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("insert into student(name) values(?)");
		ps.setString(1, name);
		ps.execute();
	}

	public void delStudent(int id) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("delete from student where id=?");
		ps.setInt(1, id);
		ps.execute();
	}
}
