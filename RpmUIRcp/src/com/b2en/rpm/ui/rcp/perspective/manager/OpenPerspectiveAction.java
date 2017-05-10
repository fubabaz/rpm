package com.b2en.rpm.ui.rcp.perspective.manager;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchWindow;

import com.b2en.rpm.ui.rcp.util.DBInfoUtil;
import com.b2en.rpm.ui.rcp.util.RpmRcpUtil;

public class OpenPerspectiveAction extends Action{
	
	private final IWorkbenchWindow window;
	private String perspectiveId;
	
	public OpenPerspectiveAction(IWorkbenchWindow window, String perspectiveId) {
        super();
        this.window = window;
        this.perspectiveId = perspectiveId;
    }
	
	@Override
	public void run() {
		if (window != null) {
			IPerspectiveDescriptor perspectiveDescriptor = window.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(perspectiveId);
			if(DBInfoUtil.checkDBConnectionByPref()){
				window.getActivePage().setPerspective(perspectiveDescriptor);				
			}else{
				RpmRcpUtil.showMessageBox(window.getShell(), "DB Info", "Please, Set Database Information.[Help>Preferences>DB Info]", SWT.ICON_ERROR | SWT.OK);
			}
		}
	}
}
