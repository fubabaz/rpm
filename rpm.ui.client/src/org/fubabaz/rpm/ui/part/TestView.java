/**
  * Copyright 2017 FuBaBaz Team.
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * 
  *     http://www.apache.org/licenses/LICENSE-2.0
  * 
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  */

package org.fubabaz.rpm.ui.part;

import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.fubabaz.rpm.service.sql.ColumnMetaInfo;
import org.fubabaz.rpm.service.sql.SqlResultInfo;
import org.fubabaz.rpm.ui.table.ObjectArrayColumnLabelProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ejpark
 *
 */
public class TestView extends SqlServiceView {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestView.class);
	private Text text;
	private TableViewer tableViewer;

	@PostConstruct
	public void createComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));

		text = new Text(composite, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_text = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_text.heightHint = 150;
		gd_text.widthHint = 360;
		text.setLayoutData(gd_text);

		tableViewer = new TableViewer(composite, SWT.BORDER);
		Table table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);

		text.setText("select * from v$session");
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.stateMask == SWT.CTRL && (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR)) {
					e.doit = false;
					executeSql(text.getText());
				}
			}
		});
	}

	@Override
	public void callback(Object result, String attachement) {
		LOGGER.debug("redraw:{}", result);
		tableViewer.getControl().getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				removeTableColumns();

				SqlResultInfo sqlResultInfo = (SqlResultInfo) result;
				List<ColumnMetaInfo> columnMetaInfoList = sqlResultInfo.getColumnMetaInfoList();

				createTalbeColumns(columnMetaInfoList);

				Object[] rowData = sqlResultInfo.getRowData();
				tableViewer.setInput(rowData);
			}
		});
	}

	private void removeTableColumns() {
		Table table = tableViewer.getTable();
		table.removeAll();
		for (int i = table.getColumnCount() - 1; i >= 0; i--) {
			table.getColumn(i).dispose();
		}
	}

	private void createTalbeColumns(List<ColumnMetaInfo> columnMetaInfoList) {
		int columnCnt = columnMetaInfoList.size();
		for (int i = 0; i < columnCnt; i++) {
			ColumnMetaInfo columnMetaInfo = columnMetaInfoList.get(i);
			TableViewerColumn dpNameViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
			dpNameViewerColumn.getColumn().setWidth(columnMetaInfo.getWidth());
			dpNameViewerColumn.getColumn().setAlignment(SWT.LEFT);
			dpNameViewerColumn.getColumn().setText(columnMetaInfo.getLabel());
			dpNameViewerColumn.setLabelProvider(new ObjectArrayColumnLabelProvider(i));
		}
	}
}
