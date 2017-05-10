package com.b2en.ui.rcp.view.tree.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

public class TreeExpandAllAction extends Action{
	
	private TreeViewer treeViewer;
	
	public TreeExpandAllAction(TreeViewer treeViewer){
		super("Expand All");
		this.treeViewer = treeViewer;
	}
	
	@Override
	public void run() {
		this.treeViewer.expandAll();
	}
}
