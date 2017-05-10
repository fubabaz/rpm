package com.b2en.ui.rcp.view.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.b2en.common.vo.AbstractCommonVO;

public class TreeNodeInfo extends AbstractCommonVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;
	private Image image;
	private Object data;
	private String[] rowData;
	private TreeNodeInfo parent;
	private List<TreeNodeInfo> treeNodeInfoList = new ArrayList<TreeNodeInfo>();
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String[] getRowData() {
		return rowData;
	}
	public void setRowData(String[] rowData) {
		this.rowData = rowData;
	}
	public TreeNodeInfo getParent() {
		return parent;
	}
	public void setParent(TreeNodeInfo parent) {
		this.parent = parent;
	}
	public List<TreeNodeInfo> getTreeNodeInfoList() {
		return treeNodeInfoList;
	}
	public void setTreeNodeInfoList(List<TreeNodeInfo> treeNodeInfoList) {
		this.treeNodeInfoList = treeNodeInfoList;
	}
	public void setTreeNodeInfoList(List<TreeNodeInfo> treeNodeInfoList, TreeNodeInfo parent) {
		this.treeNodeInfoList = treeNodeInfoList;
		if(this.treeNodeInfoList!=null){
			for(TreeNodeInfo treeNodeInfo : this.treeNodeInfoList){
				treeNodeInfo.setParent(parent);
			}
		}
	}
	public void addTreeNodeInfo(TreeNodeInfo treeNodeInfo) {
		this.treeNodeInfoList.add(treeNodeInfo);
	}	
	public boolean contains(TreeNodeInfo treeNodeInfo) {
		return this.treeNodeInfoList.contains(treeNodeInfo);
	}
	public boolean hasChildren(){
		if(this.treeNodeInfoList==null || this.treeNodeInfoList.size()==0){
			return false;
		}
		return true;
	}
}
