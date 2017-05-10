package com.b2en.rpm.ui.rcp;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class RealmRunnable implements Runnable{
	
	private Display display;
	private int returnCode;
	
	public RealmRunnable(Display display){
		this.display = display;
	}
	
	@Override
	public void run() {
		this.returnCode = PlatformUI.createAndRunWorkbench(this.display, new ApplicationWorkbenchAdvisor());
	}
	
	public int getReturnCode(){
		return this.returnCode;
	}

}
