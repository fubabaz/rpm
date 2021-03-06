package com.b2en.rpm.ui.rcp.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class SessionViewerPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addView("RpmUIRcp.SessionListView", IPageLayout.LEFT, 0.30f, IPageLayout.ID_EDITOR_AREA);
		
		IFolderLayout bottomLayout = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, "RpmUIRcp.SessionListView");
	//	bottomLayout.addView("RpmUIRcp.CurrentSqlView");
	//	bottomLayout.addView("RpmUIRcp.SessionStatisticView");
		
	//	layout.getViewLayout("RpmUIRcp.SessionListView").setCloseable(false);
	//	layout.getViewLayout("RpmUIRcp.CurrentSqlView").setCloseable(false);
	//	layout.getViewLayout("RpmUIRcp.SessionStatisticView").setCloseable(false);
	}
}
