package com.b2en.rpm.ui.rcp.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class SystemPerspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addView("RpmUIRcp.SummaryView", IPageLayout.LEFT, 0.30f, IPageLayout.ID_EDITOR_AREA);
		
		IFolderLayout bottomLayout = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, "RpmUIRcp.SummaryView");
		bottomLayout.addView("RpmUIRcp.SessionView");
		bottomLayout.addView("RpmUIRcp.BlockingView");
		
		layout.getViewLayout("RpmUIRcp.SummaryView").setCloseable(false);
		layout.getViewLayout("RpmUIRcp.SessionView").setCloseable(false);
		layout.getViewLayout("RpmUIRcp.BlockingView").setCloseable(false);
	}

}
