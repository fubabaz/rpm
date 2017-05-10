package com.b2en.rpm.ui.rcp.perspective.manager;

public enum PerspectiveInfo {
	
	InitialPerspective("RpmUIRcp.InitialPerspective")
	,SystemPerspective("RpmUIRcp.SystemPerspective")
	,SessionViewerPerspective("RpmUIRcp.SessionViewerPerspective")
	;
	
	private String id;
	private PerspectiveInfo(String id){
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
}
