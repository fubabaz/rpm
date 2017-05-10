package com.b2en.rpm.ui.rcp;

import org.apache.log4j.Logger;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	
	private Logger logger = Logger.getLogger(getClass());
	
    /**
     * @wbp.parser.entryPoint
     */
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
    	if(logger.isDebugEnabled()){
    		logger.debug("::preWindowOpen");
    	}
    	
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setShowPerspectiveBar(false);
        configurer.setShowStatusLine(false);
        //PlatformUI.getPreferenceStore().setValue(IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS, false);
        
    }
    
    public void postWindowOpen() {	
    	if(logger.isDebugEnabled()){
    		logger.debug("::postWindowOpen");
    	}
    	//Window maximize.
    	getWindowConfigurer().getWindow().getShell().setMaximized(true);
    }
}
