package forms.panels.main;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MyTablePanel extends JPanel{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public MyTablePanel() {}
	
	// 테이블 컬럼 가운데 정렬 함수
		protected void DefaultTableRenderer(JTable t) {
			DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
			defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

			TableColumnModel tableColumnModel = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

			for(int i=0; i<5; i++) 
				tableColumnModel.getColumn(i).setCellRenderer(defaultTableCellRenderer);
		}
		
		// 4번째 컬럼의 셀 좌우크기를 키우는 함수
		protected void setTableCell(JTable table, int size, int ...cols) {
			TableColumn column = null;
			
			for (int i = 0; i < table.getColumnCount(); i++) {
				column = table.getColumnModel().getColumn(i);
				
				for(int j=0; j<cols.length; j++) {
					if (i == cols[j]) {
						column.setPreferredWidth(size); // third column is bigger } else {
					}
				}
			}
		}
}
