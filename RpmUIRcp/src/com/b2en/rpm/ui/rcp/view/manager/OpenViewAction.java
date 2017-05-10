package com.b2en.rpm.ui.rcp.view.manager;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.IViewDescriptor;

import com.b2en.common.util.DateUtil;
import com.b2en.rpm.ui.rcp.util.RpmRcpUtil;

public class OpenViewAction extends Action{
	
	private String viewId;
	private boolean allowMultiple;
	public OpenViewAction(String viewId) {
        super();
        this.viewId = viewId;
        
        IViewDescriptor viewDescriptor = PlatformUI.getWorkbench().getViewRegistry().find(this.viewId);
        this.allowMultiple = viewDescriptor.getAllowMultiple();
        
        setText(viewDescriptor.getLabel());
        setToolTipText(viewDescriptor.getLabel());
        setImageDescriptor(viewDescriptor.getImageDescriptor());
    }
	
	@Override
	public void run() {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IViewPart view = null;
			if(this.allowMultiple){
				view = page.showView(this.viewId, this.viewId+DateUtil.getToday("yyyyMMddhhmmssss"), IWorkbenchPage.VIEW_CREATE);
			}else{
				view = page.showView(this.viewId);
			}
			page.activate(view);
		} catch (PartInitException e) {
			RpmRcpUtil.showMessageBox(null, "ERROR", "Can not initial View.["+this.viewId+"]", SWT.ICON_ERROR | SWT.OK);
		}
	}

}
