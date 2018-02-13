/**
 * Copyright (c) 2018 fubabaz 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.fubabaz.rpm.ui.handler.toolbar;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;
import org.fubabaz.rpm.ui.dialog.ConnectionDialog;

/**
 * @author ejpark
 *
 */
public class ConnectionHandler {

	@Execute
	public void execute(IWorkbench workbench, Shell shell) {
		ConnectionDialog dialog = new ConnectionDialog(shell);
		dialog.create();
		dialog.open();
	}

}
