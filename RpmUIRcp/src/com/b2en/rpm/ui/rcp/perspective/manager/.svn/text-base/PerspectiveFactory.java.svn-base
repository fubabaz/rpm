package com.b2en.rpm.ui.rcp.perspective.manager;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchWindow;

public class PerspectiveFactory {
	
	private static volatile PerspectiveFactory perspectiveFactory;
	
	private Map<String, Action> perspectiveActionMap;
	
	private PerspectiveFactory(){
		perspectiveActionMap = new HashMap<String, Action>();
	}
	
	public static PerspectiveFactory getInstance(){
		if(perspectiveFactory == null){
			synchronized (PerspectiveFactory.class) {
				if(perspectiveFactory == null){
					perspectiveFactory = new PerspectiveFactory();
				}
			}
		}
		return perspectiveFactory;
	}
	
	public void create(IWorkbenchWindow window){
		
		PerspectiveInfo[] perspectiveInfos = PerspectiveInfo.values();
		for (int i = 0; i < perspectiveInfos.length; i++) {
			PerspectiveInfo perspectiveInfo = perspectiveInfos[i];
			IPerspectiveDescriptor perspectiveDescriptor = window.getWorkbench().getPerspectiveRegistry().findPerspectiveWithId(perspectiveInfo.getId());
			OpenPerspectiveAction openPerspectiveAction = new OpenPerspectiveAction(window, perspectiveDescriptor.getId());
			openPerspectiveAction.setText(perspectiveDescriptor.getLabel());
			openPerspectiveAction.setToolTipText(perspectiveDescriptor.getLabel());
			openPerspectiveAction.setImageDescriptor(perspectiveDescriptor.getImageDescriptor());
			perspectiveActionMap.put(perspectiveInfo.getId(), openPerspectiveAction);
		}
		
		
	}
	
	public Action getAction(PerspectiveInfo perspectiveInfo){
		return this.perspectiveActionMap.get(perspectiveInfo.getId());
	}
}




