package com.b2en.rpm.ui.rcp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import com.b2en.rpm.ui.rcp.view.AbstractUtilView;

public class RpmRcpUtil {

	public static int showMessageBox(Shell shell, String title, String content, int icon) {
		MessageBox m = new MessageBox(shell, icon);
		m.setText(title);
		m.setMessage(content);
		int response = m.open();
		return response;
	}
	
	public static boolean isIp(String value){
		if("localhost".equals(value)){
			return true;
		}
		String regex = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public static boolean isRealNumber(String value) {
		String regex = "^[+-]?\\d*(\\.?\\d*)$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public static boolean isSimpleNumber(String value) {
		String regex = "^[0-9]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	public static void activeViewReflesh(){
		IViewReference [] viewRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
		for(IViewReference viewRef : viewRefs){
			AbstractUtilView viewPart = (AbstractUtilView)viewRef.getView(true);
			viewPart.reflesh();
		}
	}
}
