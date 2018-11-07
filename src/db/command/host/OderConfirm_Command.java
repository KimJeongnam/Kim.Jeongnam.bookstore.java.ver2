package db.command.host;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.Oracledb;
import db.command.Command;

public class OderConfirm_Command implements Command{
	Connection con = null;
	PreparedStatement pstmt = null;
	private Savepoint savepoint1 = null;
	private ArrayList<String> order_codes = null;

	public OderConfirm_Command(String order_code) {
		order_codes = new ArrayList<String>();
		this.order_codes.add(order_code);
		con = Oracledb.getInstance().getConnection();
	}
	
	public OderConfirm_Command(ArrayList<String>  order_codes) {
		this.order_codes = order_codes;
		con = Oracledb.getInstance().getConnection();
	}

	@Override
	public void execute() throws SQLException {
		// TODO Auto-generated method stub
		con.setAutoCommit(false);
		savepoint1 = con.setSavepoint("SavePoint1");
		
		boolean status = true;
		
		for(String order_code : order_codes) {
			int rowsInserted = 0;
	
			String sql = "UPDATE orders \r\n" + 
					"    SET payment_status = 1\r\n" +
					"	 , confirm_date = SYSDATE \r\n"+
					"    WHERE order_code = ?";
	
			pstmt = Oracledb.getInstance().getConnection().prepareStatement(sql);
	
			pstmt.setString(1, order_code);
	
			rowsInserted = pstmt.executeUpdate();
			
			if(rowsInserted==0) {
				JOptionPane.showMessageDialog(null, "[Error]Exception!", "주문 승인 오류"
						, JOptionPane.WARNING_MESSAGE);
				status=false;
				break;
			}
		}
		
		if(!status) con.rollback(savepoint1);
		else JOptionPane.showMessageDialog(null, "'"+order_codes.size()+"건' 승인 완료", "success"
				, JOptionPane.INFORMATION_MESSAGE);
		commit();
		
		pstmt.close();
	}
	
	private void commit() throws SQLException{
		con.commit();
		con.setAutoCommit(true);
	}
}
