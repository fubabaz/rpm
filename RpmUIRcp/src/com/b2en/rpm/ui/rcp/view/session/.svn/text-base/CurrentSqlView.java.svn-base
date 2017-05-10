package com.b2en.rpm.ui.rcp.view.session;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;
import com.b2en.ui.rcp.view.text.listener.SqlCommandLineStyleListener;

public class CurrentSqlView extends AbstractUtilView {

	public static final String ID = "RpmUIRcp.CurrentSqlView";
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Form form;
	
	private StyledText sqlInfoText;
	
	@Override
	public void createPartControl(Composite parent) {
		
		form = formToolkit.createForm(parent);
		formToolkit.paintBordersFor(form);
		form.getHead().setFont(getDefaultFont());
		form.getHead().setBackground(SWTResourceUtil.getColor(4,46,110));
		form.setFont(getFont(8, SWT.ITALIC));
		form.setForeground(SWTResourceUtil.getColor(SWT.COLOR_WHITE));
		form.getBody().setLayout(new FillLayout(SWT.HORIZONTAL));
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(form.getBody(), SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		formToolkit.adapt(scrolledComposite);
		formToolkit.paintBordersFor(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite mainComposite = new Composite(scrolledComposite, SWT.NONE);
		mainComposite.setBackground(SWTResourceUtil.getColor(SWT.COLOR_WHITE));
		mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		mainComposite.setLayout(new GridLayout(1, false));
		
		sqlInfoText = new StyledText(mainComposite, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_sqlInfoText = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_sqlInfoText.heightHint = 250;
		sqlInfoText.setLayoutData(gd_sqlInfoText);
		sqlInfoText.addLineStyleListener(new SqlCommandLineStyleListener(SWTResourceUtil.getColor(SWT.COLOR_BLACK), SWTResourceUtil.getColor(SWT.COLOR_WHITE), SWT.BOLD));
		formToolkit.adapt(sqlInfoText);
		formToolkit.paintBordersFor(sqlInfoText);
		
		scrolledComposite.setContent(mainComposite);
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	public void setSQLText(String sqlText){
		sqlInfoText.setText(sqlText);
	}
	
	@Override
	public void reflesh() {
		
	}

}
