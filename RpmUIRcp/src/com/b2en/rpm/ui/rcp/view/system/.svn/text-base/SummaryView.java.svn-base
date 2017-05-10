package com.b2en.rpm.ui.rcp.view.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString.Styler;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.b2en.rpm.common.service.RpmCommonService;
import com.b2en.rpm.common.vo.SchemaTableInfo;
import com.b2en.rpm.ui.rcp.util.DBInfoUtil;
import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;
import com.b2en.rpm.ui.rcp.view.AbstractUtilView;
import com.b2en.ui.rcp.view.tree.TreeNodeInfo;
import com.b2en.ui.rcp.view.tree.provider.TreeLabelProvider;
import com.b2en.ui.rcp.view.tree.provider.TreeViewContentProvider;

public class SummaryView extends AbstractUtilView {

	private Logger logger = Logger.getLogger(getClass());
	
	public static final String ID = "RpmUIRcp.SummaryView";
	
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Form form;
	
	private RpmCommonService rpmCommonService;
	
	private TreeViewer treeViewer;
	
	public SummaryView(){
		this.rpmCommonService = (RpmCommonService)getBizService(RpmCommonService.class);
	}
	
	@Override
	public void createPartControl(Composite parent) {
		
		form = formToolkit.createForm(parent);
		formToolkit.paintBordersFor(form);
		form.getHead().setFont(getDefaultFont());
		form.getHead().setBackground(SWTResourceUtil.getColor(4,46,110));
		form.setFont(getFont(8, SWT.ITALIC));
		form.setForeground(SWTResourceUtil.getColor(SWT.COLOR_WHITE));
		form.setText("- 테이블 목록입니다.");
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
		
		FilteredTree filteredTree = new FilteredTree(mainComposite, SWT.BORDER, new PatternFilter(), true);
		filteredTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		filteredTree.setInitialText("name filter");
		
		treeViewer = filteredTree.getViewer();
		Tree tree = treeViewer.getTree();
		formToolkit.paintBordersFor(tree);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tree.setFont(getDefaultFont());
		
		tree.setHeaderVisible(true);
		tree.setLinesVisible(false);
		
		TreeViewerColumn titleColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		titleColumn.getColumn().setAlignment(SWT.LEFT);
		titleColumn.getColumn().setText("Table");
		titleColumn.getColumn().setWidth(250);
		titleColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new TreeLabelProvider(0)));
		
		TreeViewerColumn desc = new TreeViewerColumn(treeViewer, SWT.NONE);
		desc.getColumn().setAlignment(SWT.LEFT);
		desc.getColumn().setText("Description");
		desc.getColumn().setWidth(250);
		TreeLabelProvider descLabelProvider = new TreeLabelProvider(1, new Styler(){
			@Override
			public void applyStyles(TextStyle textStyle) {
				textStyle.font = getFont(8, SWT.ITALIC);
			}
		});
		desc.setLabelProvider(new DelegatingStyledCellLabelProvider(descLabelProvider));
		
		treeViewer.setContentProvider(new TreeViewContentProvider());
		
		scrolledComposite.setContent(mainComposite);
		scrolledComposite.setMinSize(mainComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		addExpandAllToolBarAction(treeViewer);
		addCollapseAllToolBarAction(treeViewer);
		
		init();
		
	}
	
	private void init(){
		SchemaTableInfo schemaTableInfo = new SchemaTableInfo();
		schemaTableInfo.setTblType("TABLE");
		schemaTableInfo.setOwner(DBInfoUtil.getOwner());
		List<SchemaTableInfo> schemaTableInfoList = this.rpmCommonService.getSchemaTableColumnInfoList(schemaTableInfo);
		List<TreeNodeInfo> treeList = convertList(schemaTableInfoList);
		setTreeInfo(treeList);
	}

	private List<TreeNodeInfo> convertList(List<SchemaTableInfo> schemaTableInfoList){
		List<TreeNodeInfo> list = new ArrayList<TreeNodeInfo>();
		Map<String,List<TreeNodeInfo>> columnListMap = new HashMap<String,List<TreeNodeInfo>>();
		for(int i=0; schemaTableInfoList!=null && i<schemaTableInfoList.size(); i++){
			SchemaTableInfo schemaTableInfo = schemaTableInfoList.get(i);
			String tblNm = schemaTableInfo.getTblNm();
			List<TreeNodeInfo> columnTreeList = columnListMap.get(tblNm);
			if(columnTreeList==null){
				columnTreeList = new ArrayList<TreeNodeInfo>();
				TreeNodeInfo tableNodeInfo = new TreeNodeInfo();
				tableNodeInfo.setLabel(schemaTableInfo.getTblNm());
				tableNodeInfo.setData(schemaTableInfo);
				tableNodeInfo.setRowData(new String[] {schemaTableInfo.getTblNm(), schemaTableInfo.getTblComnt()});
				list.add(tableNodeInfo);
				columnListMap.put(tblNm, columnTreeList);
			}
			
			TreeNodeInfo columnNodeInfo = new TreeNodeInfo();
			columnNodeInfo.setLabel(schemaTableInfo.getColNm());
			columnNodeInfo.setData(schemaTableInfo);
			columnNodeInfo.setRowData(new String[] {schemaTableInfo.getColNm(), schemaTableInfo.getColComnt()});
			columnTreeList.add(columnNodeInfo);
		}
		
		for(TreeNodeInfo treeNodeInfo : list){
			List<TreeNodeInfo> columnTreeList = columnListMap.get(treeNodeInfo.getLabel());
			if(columnTreeList!=null){
				treeNodeInfo.setTreeNodeInfoList(columnTreeList);
			}
		}
		
		return list;
	}
	
	private void setTreeInfo(List<TreeNodeInfo> treeList){
		treeViewer.getTree().removeAll();
		treeViewer.setInput(treeList);
	}
	
	@Override
	public void reflesh() {
		init();
	}
}
