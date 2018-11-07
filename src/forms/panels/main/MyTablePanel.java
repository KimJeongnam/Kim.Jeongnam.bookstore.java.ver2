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
		public void DefaultTableRenderer(JTable t) {
			DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
			defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

			TableColumnModel tableColumnModel = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

			for(int i=0; i<t.getColumnCount(); i++) {
				Object o = t.getColumnClass(i);
				if(!(o instanceof Boolean))
					tableColumnModel.getColumn(i).setCellRenderer(defaultTableCellRenderer);
			}
		}
		
		// 원하는 컬럼 의 사이즈를 변경하는 함수 (변경할 테이블, 사이즈, 변경할 컬럼값...)
		public void setTableCell(JTable table, int size, int ...cols) {
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
