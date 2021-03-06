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

package org.fubabaz.rpm.ui.table;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * @author ejpark
 *
 */
public class ObjectArrayColumnLabelProvider extends ColumnLabelProvider {

	private final int columnIndex;

	public ObjectArrayColumnLabelProvider(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	@Override
	public String getText(Object element) {
		Object object = null;
		if(element instanceof Object[]) {
			Object[] rows = (Object[])element;
			object = rows[columnIndex];
		}
		return object == null ? "" : object.toString();//$NON-NLS-1$
	}
}
