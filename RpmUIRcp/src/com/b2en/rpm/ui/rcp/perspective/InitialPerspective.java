package com.b2en.rpm.ui.rcp.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;

public class InitialPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
	
		PlatformUI.getPreferenceStore().setValue( IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
		
		layout.setEditorAreaVisible(false);

		layout.addView("RpmUIRcp.SystemMonitorViewPart", IPageLayout.LEFT, 0.30f, IPageLayout.ID_EDITOR_AREA);
	}	
}
