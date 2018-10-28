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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import forms.host.HostMenuForm;
import forms.tables.BookTableModel;
import models.Book;
import models.Code;
import service.Services;

public class BookTablePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BookTableModel tableModel= null;
	private JTable table = null;
	private String book_code = null;
	private String book_name = null;
	private String price = null;
	
	public BookTablePanel(String permission) {
		getBooks();
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		
		this.setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder(), 
                "책 목록", 
                TitledBorder.CENTER, 
                TitledBorder.TOP)); 
		
		tableModel = new BookTableModel(Book.getShelfList());
		
		table = new JTable(tableModel);
		DefaultTableRenderer(table);
		/*table.setFillsViewportHeight(true);*/				// 셀 수정 불가
		/*table.getTableHeader().setReorderingAllowed(false);	*/// 셀 이동불가
		table.getTableHeader().setResizingAllowed(false);	// 셀 크기 조정 불가
 
		JScrollPane scrollPane;
		
		// 4번째 컬럼의 셀 좌우크기를 키우는 함수
		setTableCell(table);
		
		if (permission.equals(Code.PERMISSION_HOST)) {
			table.addMouseListener(new TableClickAdapter(table));
			
			scrollPane = new JScrollPane(table);
			this.add(scrollPane);

			this.setPreferredSize(new Dimension(800, 500));			// 패널 크기 지정
			this.add(InsertBookPanel.createInstance());				// 책정보 입력 패널
		}else if(permission.equals(Code.PERMISSION_GUEST)) {
			scrollPane = new JScrollPane(table);
			this.add(scrollPane);
		}
	}
	
	// 테이블 컬럼 가운데 정렬 함수
	private void DefaultTableRenderer(JTable t) {
		DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tableColumnModel = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		for(int i=0; i<5; i++) 
			tableColumnModel.getColumn(i).setCellRenderer(defaultTableCellRenderer);
	}
	
	// 4번째 컬럼의 셀 좌우크기를 키우는 함수
	private void setTableCell(JTable table) {
		TableColumn column = null;
		for (int i = 0; i < 5; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 3) {
				column.setPreferredWidth(100); // third column is bigger } else {
				//column.setPreferredWidth(50);
			}
		}
	}
	
	// 책정보 내용변경 업데이트
	public void update() {
		getBooks();
		BookTableModel model = new BookTableModel(Book.getShelfList());
		table.setModel(model);
		DefaultTableRenderer(table);
		setTableCell(table);
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


class TableClickAdapter extends MouseAdapter{
	JTable table;
	public TableClickAdapter(JTable table) {
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

