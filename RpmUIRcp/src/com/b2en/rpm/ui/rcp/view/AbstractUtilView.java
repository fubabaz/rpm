package com.b2en.rpm.ui.rcp.view;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.b2en.rpm.config.service.BizServiceUtil;
import com.b2en.rpm.ui.rcp.Activator;
import com.b2en.rpm.ui.rcp.util.GetImagePath;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.ui.rcp.view.tree.action.TreeCollapseAllAction;
import com.b2en.ui.rcp.view.tree.action.TreeExpandAllAction;

public abstract class AbstractUtilView extends ViewPart {

	public abstract void createPartControl(Composite parent);
	
	public abstract void reflesh();
	
	public void setFocus() {
		//Do nothing.
	}
	
	protected Object getBizService(Class<?> serviceClass){
		return BizServiceUtil.getService(serviceClass);
	}
	
	protected String nullToSpace(String strObj){
		return (strObj == null) ? "" : strObj;
	}
	
	protected Font getDefaultFont(){
		return SWTResourceUtil.getFont("맑은 고딕", 9, 0, false, false);
	}
	
	protected Font getFont(int size, int style){
		return SWTResourceUtil.getFont("맑은 고딕", size, style, false, false);
	}
	
	protected void addToolBarAction(Action action) {
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
		toolbarManager.add(action);
	}
	
	protected void addExpandAllToolBarAction(TreeViewer treeViewer){
		Action action = new TreeExpandAllAction(treeViewer);
		action.setImageDescriptor(SWTResourceUtil.getPluginImageDescriptor(Activator.PLUGIN_ID, GetImagePath.expandAll.getPath()));
		addToolBarAction(action);
	}
	
	protected void addCollapseAllToolBarAction(TreeViewer treeViewer){
		Action action = new TreeCollapseAllAction(treeViewer);
		action.setImageDescriptor(SWTResourceUtil.getPluginImageDescriptor(Activator.PLUGIN_ID, GetImagePath.collapseAll.getPath()));
		addToolBarAction(action);
	}
	
	protected void createTableColumn(TableViewer view, String[] titles, int[] bounds, int[] aligns){
		for(int i=0; i<titles.length; i++){
			TableViewerColumn column = new TableViewerColumn(view, SWT.NONE);
			column.getColumn().setText(titles[i]);
			column.getColumn().setWidth(bounds[i]);
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
			column.getColumn().setAlignment(aligns[i]);
		}
	}
	
	protected IViewPart getView(String viewID){
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		return page.findView(viewID);
	}
}

