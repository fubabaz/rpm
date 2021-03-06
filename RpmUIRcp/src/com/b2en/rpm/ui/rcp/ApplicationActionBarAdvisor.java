package com.b2en.rpm.ui.rcp;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.b2en.rpm.ui.rcp.dialog.DBConnectionDialog;
import com.b2en.rpm.ui.rcp.perspective.manager.PerspectiveFactory;
import com.b2en.ui.rcp.view.tree.action.OpenViewAction;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private Logger logger = Logger.getLogger(getClass());
	
	private IWorkbenchAction exitAction;
	
	private IWorkbenchAction aboutAction;
	
	private IWorkbenchAction newConncectionAction;
	
	private IWorkbenchAction endConncectionAction;
	
	// System Monitor
	private OpenViewAction openViewSystemMonitor;
	
	// ASH Monitor
	private OpenViewAction openViewActionASHViewPart;
	
	// AWR Monitor
	private OpenViewAction openViewActionAWRViewPart;
	
	// Session Viewer
	private OpenViewAction  openViewSessionViewerPart;
	
	// SQL Viewer
	private OpenViewAction  openViewSqlViewrPart;
		
	// Object Viewer
	private OpenViewAction  openViewObjectViewrPart;
	
	// SQL Tuner
	private OpenViewAction  openViewSqlTunerPart;
	
	
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
    
        super(configurer);
        // ���� DMBS �Է� â 
        //WizardDialog dialog = new WizardDialog(null, new wiazar());
		//dialog.open();

		DBConnectionDialog dbConnectionDialog = new DBConnectionDialog( Display.getCurrent().getActiveShell(), -4);
		dbConnectionDialog.open();
  
    }



	protected void makeActions(IWorkbenchWindow window) {
    	if(logger.isDebugEnabled()){
    		logger.debug("::makeActions::start");
    	}
    	
    	exitAction = ActionFactory.QUIT.create(window);
    	exitAction.setToolTipText("EXIT");
		register(exitAction);
		
		aboutAction = ActionFactory.ABOUT.create(window);
		aboutAction.setText("About RPM");
		register(aboutAction);
		
		newConncectionAction = ActionFactory.PREFERENCES.create(window);
		newConncectionAction.setText("New Connection");
		register(newConncectionAction);
		
		endConncectionAction = ActionFactory.PREFERENCES.create(window);
		endConncectionAction.setText("End Connection");
		register(endConncectionAction);
		
	
		openViewSystemMonitor  = new OpenViewAction(window, "System Monitor", "RpmUIRcp.SystemMonitorViewPart","/icons/toolbar/1.png");
	    register(openViewSystemMonitor);
	    
		
		openViewActionASHViewPart = new OpenViewAction(window, "ASH Monitor", "RpmUIRcp.ASHViewPart","/icons/toolbar/2.png");
	    register(openViewActionASHViewPart);
	        
	    openViewActionAWRViewPart = new OpenViewAction(window, "AWR Monitor", "RpmUIRcp.AWRViewPart","/icons/toolbar/3.png");
	    register(openViewActionAWRViewPart);
	    
	    
	    openViewSessionViewerPart  = new OpenViewAction(window, "Session Viewer", "RpmUIRcp.SessionListView","/icons/toolbar/4.png");
	    register(openViewSessionViewerPart);
	    
	    openViewSqlViewrPart  = new OpenViewAction(window, "Sql Viewr", "RpmUIRcp.SqlViewerView","/icons/toolbar/5.png");
        register(openViewSqlViewrPart);
	    
	    
	    openViewObjectViewrPart  = new OpenViewAction(window, "Object Viewer", null,"/icons/toolbar/6.png");
	    register(openViewObjectViewrPart);
	    
	    
	    openViewSqlTunerPart  = new OpenViewAction(window, "Sql Tunner", null,"/icons/toolbar/7.png");
	    register(openViewSqlTunerPart);
	    
	    
		if(logger.isDebugEnabled()){
    		logger.debug("::makeActions::end");
    	}
		
		//Create Perspective Action.
		if(logger.isDebugEnabled()){
    		logger.debug("::Perspective create.");
    	}
		PerspectiveFactory.getInstance().create(window);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	if(logger.isDebugEnabled()){
    		logger.debug("::fillMenuBar");
    	}
    	
    	// File
    	MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
    	fileMenu.add(exitAction);
    	menuBar.add(fileMenu);
		
    	//Session
    	MenuManager sessionMenu = new MenuManager("&Session", IWorkbenchActionConstants.M_HELP);
    	sessionMenu.add(newConncectionAction);	
    	sessionMenu.add(endConncectionAction);
    	
    	menuBar.add(sessionMenu);
    	
    	//Help
    	MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
    	
    	helpMenu.add(aboutAction);
    	menuBar.add(helpMenu);
    	
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar){
    	if(logger.isDebugEnabled()){
    		logger.debug("::fillCoolBar");
    	}
    	IToolBarManager toolbar = new ToolBarManager(SWT.NONE);
        ToolBarContributionItem toolBarContributionItem = new ToolBarContributionItem(toolbar, "main");
        toolBarContributionItem.setUseChevron(false);
        toolBarContributionItem.setVisible(false);
        coolBar.add(toolBarContributionItem);

        
        IToolBarManager toolbar2 = new ToolBarManager(SWT.NONE);
        ToolBarContributionItem toolbar2Item = new ToolBarContributionItem(toolbar2, "main2");
        toolbar2Item.setUseChevron(false);
        toolbar2Item.setVisible(false);
        coolBar.add(toolbar2Item);
        
        
        IToolBarManager toolbar3 = new ToolBarManager(SWT.NONE);
        ToolBarContributionItem toolbar3Item = new ToolBarContributionItem(toolbar3, "main3");
        toolbar3Item.setUseChevron(false);
        toolbar3Item.setVisible(false);
        coolBar.add(toolbar3Item);
        
        
        
        toolbar.add(openViewSystemMonitor);
        toolbar.add(openViewActionASHViewPart);
        toolbar.add(openViewActionAWRViewPart);
        
        
        
        toolbar2.add(openViewSessionViewerPart);        
        toolbar2.add(openViewSqlViewrPart);       
        toolbar2.add(openViewObjectViewrPart);       
        toolbar3.add(openViewSqlTunerPart);
    }
}
