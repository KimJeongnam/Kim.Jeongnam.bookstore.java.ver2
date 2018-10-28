package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class Oracledb {
	private Connection con = null;
	private Statement stmt = null;
	private static Oracledb instance = new Oracledb();
	
	public Oracledb() {
		try {
			OracleDataSource ds = new OracleDataSource();
			String driver = "oracle.jdbc.driver.OracleDriver";
			String dbURL = "jdbc:oracle:thin:@localhost:1521:xe"; //:1521:xe
            String username = "scott";
            String password = "tiger";
            
            Class.forName(driver);
            ds.setURL(dbURL);
            con = ds.getConnection(username,password); 
            
			stmt = con.createStatement();
			System.out.println("Connection DB!");
		} catch (SQLException e) {
			printSQLError(e);
			System.exit(1);
		}catch(Exception e){
			e.printStackTrace();
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
	
	public static Oracledb getInstance() { return instance; }
	public Connection getConnection() { return con; }
	
	
	public Statement getStatement(){ 
		try {
			return con.createStatement();
		}catch(SQLException e) {
			printSQLError(e);
			return null;
		}
	}
}
