package com.b2en.rpm.ui.rcp.view.session;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.b2en.rpm.session.service.RpmSessionService;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;

public class SessionStatisticView extends AbstractUtilView {

	private Logger logger = Logger.getLogger(getClass());
	
	public static final String ID = "RpmUIRcp.SessionStatisticView";
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Form form;
	
	private RpmSessionService rpmSessionService;
	
	private TableViewer tableViewer;
	private Table table;
	
	public SessionStatisticView(){
		this.rpmSessionService = (RpmSessionService)getBizService(RpmSessionService.class);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		form = formToolkit.createForm(parent);
		formToolkit.paintBordersFor(form);
		form.setFont(getFont(8, SWT.ITALIC));
		form.setForeground(SWTResourceUtil.getColor(SWT.COLOR_WHITE));
		form.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(form.getBody(), SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite mainComposite = new Composite(scrolledComposite, SWT.NONE);
		formToolkit.adapt(mainComposite);
		formToolkit.paintBordersFor(mainComposite);
		mainComposite.setLayout(new GridLayout(1, false));
		mainComposite.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		
		
		scrolledComposite.setContent(mainComposite);
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}	

	@Override
	public void reflesh() {
		
	}
}
