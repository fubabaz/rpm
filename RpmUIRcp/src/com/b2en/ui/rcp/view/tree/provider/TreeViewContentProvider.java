package com.b2en.ui.rcp.view.tree.provider;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.b2en.ui.rcp.view.tree.TreeNodeInfo;

public class TreeViewContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return ((List<TreeNodeInfo>)inputElement).toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] object = null;
		List<TreeNodeInfo> treeNodeInfoList = ((TreeNodeInfo)parentElement).getTreeNodeInfoList();
		if(treeNodeInfoList!=null){
			object = treeNodeInfoList.toArray();
		}
		return object;
	}

	@Override
	public Object getParent(Object element) {
		return ((TreeNodeInfo)element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((TreeNodeInfo)element).hasChildren();
	}

}
