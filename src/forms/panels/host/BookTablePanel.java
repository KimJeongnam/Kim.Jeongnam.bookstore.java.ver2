package forms.panels.host;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import db.Oracledb;
import db.command.Command;
import db.command.host.getbooks_Command;
import forms.tables.BookTableModel;
import models.Book;

public class BookTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BookTableModel tableModel= null;
	private JTable table = null;
	
	public BookTablePanel() {
		getBooks();
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		this.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "책 목록", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		
		tableModel = new BookTableModel(Book.getShelfList());
		
		TableColumn column = null;
		table = new JTable(tableModel);
		DefaultTableRenderer(table);
		table.setFillsViewportHeight(false);				// 셀 수정 불가
		table.getTableHeader().setReorderingAllowed(false);	// 셀 이동불가
		table.getTableHeader().setResizingAllowed(false);	// 셀 크기 조정 불가
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		// 4번째 컬럼의 셀 좌우크기를 키우는 함수
		for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 3) {
				column.setPreferredWidth(100); // third column is bigger } else {
				//column.setPreferredWidth(50);
			}
		}
		
		
		this.add(scrollPane);
		this.add(InsertBookPanel.createInstance());
	}
	
	private void DefaultTableRenderer(JTable t) {
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tableColumnModel = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		tableColumnModel.getColumn(1).setCellRenderer(defaultTableCellRenderer);
		tableColumnModel.getColumn(2).setCellRenderer(defaultTableCellRenderer);
		tableColumnModel.getColumn(3).setCellRenderer(defaultTableCellRenderer);
		tableColumnModel.getColumn(4).setCellRenderer(defaultTableCellRenderer);
	}

	// 테이블에 삽입할 책 리스트를 database에서 읽어오는 함수
	public void getBooks() {
		Command command = new getbooks_Command();
		
		try {
			command.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Oracledb.printSQLError(e);
		}
		
		System.out.println("getBooks()");
		for(Book book : Book.getShelfList()) {
			System.out.println(book);
		}
	}
	
	public BookTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(BookTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
}
