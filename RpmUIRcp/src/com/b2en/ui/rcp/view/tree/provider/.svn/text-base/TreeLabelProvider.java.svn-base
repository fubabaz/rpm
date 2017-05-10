package com.b2en.ui.rcp.view.tree.provider;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.swt.graphics.Image;

import com.b2en.ui.rcp.view.tree.TreeNodeInfo;

public class TreeLabelProvider extends LabelProvider implements IStyledLabelProvider {

	private int columnIndex;
	private Styler styler;
	
	public TreeLabelProvider(){
		
	}
	
	public TreeLabelProvider(int columnIndex){
		this.columnIndex = columnIndex;
	}
	
	public TreeLabelProvider(int columnIndex, Styler styler){
		this.columnIndex = columnIndex;
		this.styler = styler;
	}
	
	@Override
	public Image getImage(Object element) {
		Image image = null;
		if (element instanceof TreeNodeInfo) {
			switch(columnIndex){
				case 0 :
					image = ((TreeNodeInfo) element).getImage();
					break;
				default : 
					break;
			}
		}
		return image;
	}
	
	@Override
	public StyledString getStyledText(Object element) {
		String text = "";
		if (element instanceof TreeNodeInfo) {
			TreeNodeInfo treeNodeInfo = (TreeNodeInfo) element;
			String[] rowData = treeNodeInfo.getRowData();
			if(rowData==null){
				text = treeNodeInfo.getLabel();
			}else{
				if(rowData.length>columnIndex){
					if(rowData[columnIndex]!=null){
						text = rowData[columnIndex];						
					}
				}
			}
		}
		return new StyledString(text, this.styler);
	}
}
