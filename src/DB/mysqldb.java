package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class mysqldb {
	private Connection con = null;
	private Statement stmt = null;
	private static mysqldb db = new mysqldb();
	
	public mysqldb() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://13.125.242.194/testdb?" + "user=kim910712&password=kim15885");
			stmt = con.createStatement();
			System.out.println("Connection DB!");
		} catch (SQLException e) {
			printSQLError(e);
			System.exit(1);
		}finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void printSQLError(SQLException e) {
		System.out.println("SQLException : "+ e.getMessage());
		System.out.println("SQLState : "+ e.getSQLState());
		System.out.println("VenderError : "+ e.getErrorCode());
	}
	
	public static mysqldb getDB() { return db; }
	public Connection getConnection() { return con; }
	
	
	public Statement getStatement(){ 
		try {
			return con.createStatement();
		}catch(SQLException e) {
			printSQLError(e);
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		mysqldb.getDB();
	}
}
