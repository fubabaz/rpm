package com.b2en.ui.rcp.view.table.provider;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * StringArrayListLabelProvider.java
 *
 * @author EJ.Park
 * @since 2012. 11. 9.
 */
public class StringArrayListLabelProvider extends LabelProvider implements ITableLabelProvider{

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 * @author EJ.Park
	 * @since 2012. 11. 9.
	 */
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 * @author EJ.Park
	 * @since 2012. 11. 9.
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		String[] elements = (String[])element;
		if(elements==null || elements.length<=columnIndex){
			return null;
		}
		return elements[columnIndex];
	}

}
