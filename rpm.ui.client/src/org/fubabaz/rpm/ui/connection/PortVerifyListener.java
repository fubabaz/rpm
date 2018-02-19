package org.fubabaz.rpm.ui.connection;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;

public class PortVerifyListener implements VerifyListener {

	@Override
	public void verifyText(VerifyEvent e) {
		String currentText = ((Text) e.widget).getText();
		String port = currentText.substring(0, e.start) + e.text + currentText.substring(e.end);
		try {
			int portNum = Integer.valueOf(port);
			if (portNum < 0 || portNum > 65535) {
				e.doit = false;
			}
		} catch (NumberFormatException ex) {
			if (!port.equals("")) {
				e.doit = false;
			}
		}
	}
}
