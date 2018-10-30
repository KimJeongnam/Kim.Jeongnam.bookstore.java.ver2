package forms.panels.host;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import forms.guest.GuestMenuForm;
import forms.panels.guest.CartAddNowBuyPanel;
import forms.panels.main.MyTablePanel;
import forms.tables.BookTableModel;
import models.Book;
import models.Code;
import service.Services;

public class BookTablePanel extends MyTablePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BookTableModel tableModel= null;
	private JTable table = null;
	
	public BookTablePanel(String permission) {
		getBooks();
		this.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		
		this.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "책 목록",
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		
		tableModel = new BookTableModel(Book.getShelfList());
		
		table = new JTable(tableModel);
		table.getTableHeader().setReorderingAllowed(false);
		DefaultTableRenderer(table);
		table.getTableHeader().setResizingAllowed(false);	// 셀 크기 조정 불가
 
		JScrollPane scrollPane;
		
		// 원하는 칼럼의 셀 좌우크기를 키우는 함수
		setTableCell(table, 200, 0, 3);
		setTableCell(table, 130, 1,2,4);
		
		if (permission.equals(Code.PERMISSION_HOST)) {
			table.addMouseListener(new HostClickAdapter(table));
			
			scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(650, 500));
			this.add(scrollPane);

			this.setPreferredSize(new Dimension(1000, 600));			// 패널 크기 지정
			this.add(InsertBookPanel.createInstance());				// 책정보 입력 패널
		}else if(permission.equals(Code.PERMISSION_GUEST)) {
			table.addMouseListener(new GuestClickAdapter(table));
			scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(650, 500));
			this.add(scrollPane);
		}
	}

	
	// 책정보 내용변경 업데이트
	public void update() {
		getBooks();
		AbstractTableModel model = new BookTableModel(Book.getShelfList());
		table.setModel(model);
		DefaultTableRenderer(table);
		setTableCell(table, 200, 0, 3);
		setTableCell(table, 130, 1,2,4);
	}

	// 테이블에 삽입할 책 리스트를 database에서 읽어오는 함수
	public void getBooks() {
		Services.getInstance().getMap().get(Code.HOST_BOOK_LIST).activation();
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

}
class GuestClickAdapter extends MouseAdapter{
	JTable table;
	public GuestClickAdapter(JTable table) {
		this.table = table;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		CartAddNowBuyPanel.setSelectBook_code((String)table.getValueAt(row, 0));
		CartAddNowBuyPanel.setSelectBook_name((String)table.getValueAt(row, 1));
	}
}

class HostClickAdapter extends MouseAdapter{
	JTable table;
	public HostClickAdapter(JTable table) {
		this.table = table;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		
		System.out.println(table.getValueAt(row, 0));
		InsertBookPanel.getInstance().getLabel_bookCode().setText((String) table.getValueAt(row, 0));
		InsertBookPanel.getInstance().getTf_book_name().setText((String) table.getValueAt(row, 1));
		InsertBookPanel.getInstance().getTf_author().setText((String) table.getValueAt(row, 2));
		String price = ((String) table.getValueAt(row, 3)).replace(",", "").replace("￦","").trim();
		InsertBookPanel.getInstance().getTf_price().setText(price);
		InsertBookPanel.getInstance().getTf_stock().setText((String) table.getValueAt(row, 4));
		
	}
}

