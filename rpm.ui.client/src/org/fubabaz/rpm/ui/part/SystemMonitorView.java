package org.fubabaz.rpm.ui.part;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemMonitorView {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemMonitorView.class);
	private SashForm mainSashForm;

	@PostConstruct
	public void createComposite(Composite parent) {
		mainSashForm = new SashForm(parent, SWT.SMOOTH);

		SashForm leftSashForm = new SashForm(mainSashForm, SWT.NONE | SWT.SMOOTH | SWT.VERTICAL);
		leftSashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		ScrolledComposite dbInfoScomp = new ScrolledComposite(leftSashForm, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
		dbInfoScomp.setExpandHorizontal(true);
		dbInfoScomp.setExpandVertical(true);
		
		Composite dbInfoComp = new Composite(dbInfoScomp, SWT.NONE);
		dbInfoComp.setLayout(new GridLayout(1, false));
		

		dbInfoScomp.setContent(dbInfoComp);
		dbInfoScomp.setMinSize(dbInfoComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite resourcesScomp = new ScrolledComposite(leftSashForm, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
		resourcesScomp.setExpandHorizontal(true);
		resourcesScomp.setExpandVertical(true);
		
		Composite resourcesComp = new Composite(resourcesScomp, SWT.NONE);
		resourcesComp.setLayout(new GridLayout(1, false));

		Label resourcesLabel = new Label(resourcesComp, SWT.NONE);
		resourcesLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		resourcesLabel.setText("Resources");

		resourcesScomp.setContent(resourcesComp);
		resourcesScomp.setMinSize(resourcesComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		leftSashForm.setWeights(new int[] { 60, 60 });

		SashForm rightSashForm = new SashForm(mainSashForm, SWT.NONE | SWT.SMOOTH | SWT.VERTICAL);
		rightSashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		ScrolledComposite activityScomp = new ScrolledComposite(rightSashForm, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
		activityScomp.setExpandHorizontal(true);
		activityScomp.setExpandVertical(true);
		
		Composite activityComp = new Composite(activityScomp, SWT.NONE);
		activityComp.setLayout(new GridLayout(1, false));

		Label activityLabel = new Label(activityComp, SWT.NONE);
		activityLabel.setText("Activicty");

		activityScomp.setContent(activityComp);
		activityScomp.setMinSize(activityComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite loadProfileScomp = new ScrolledComposite(rightSashForm, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
		loadProfileScomp.setExpandHorizontal(true);
		loadProfileScomp.setExpandVertical(true);
		
		Composite loadProfileComp = new Composite(loadProfileScomp, SWT.NONE);
		loadProfileComp.setLayout(new GridLayout(1, false));
		
		Label loadProfileLabel = new Label(loadProfileComp, SWT.NONE);
		loadProfileLabel.setText("LoadProfile");

		loadProfileScomp.setContent(loadProfileComp);
		loadProfileScomp.setMinSize(loadProfileComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		SashForm bottomSashForm = new SashForm(rightSashForm, SWT.NONE | SWT.SMOOTH | SWT.VERTICAL);

		ScrolledComposite sqlMonitoringScomp = new ScrolledComposite(bottomSashForm, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
		sqlMonitoringScomp.setExpandHorizontal(true);
		sqlMonitoringScomp.setExpandVertical(true);
		
		Composite sqlMonitoringComp = new Composite(sqlMonitoringScomp, SWT.NONE);
		sqlMonitoringComp.setLayout(new GridLayout(1, false));

		Label sqlMonitoringLabel = new Label(sqlMonitoringComp, SWT.NONE);
		sqlMonitoringLabel.setText("SQL Monitoring");
		
		sqlMonitoringScomp.setContent(sqlMonitoringComp);
		sqlMonitoringScomp.setMinSize(sqlMonitoringComp.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		bottomSashForm.setWeights(new int[] { 100 });

		rightSashForm.setWeights(new int[] { 20, 60, 20 });

		mainSashForm.setWeights(new int[] { 250, 750 });

	}
}
