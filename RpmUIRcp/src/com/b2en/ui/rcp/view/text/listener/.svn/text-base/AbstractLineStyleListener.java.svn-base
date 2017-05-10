package com.b2en.ui.rcp.view.text.listener;

import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

/**
 * AbstractLineStyleListener.java
 *
 * @author EJ.Park
 * @since 2012. 11. 8.
 */
public abstract class AbstractLineStyleListener implements LineStyleListener{
	private Color foreground;
	private Color background;
	private int fontStyle;
	
	/**
	 *
	 * @param foreground
	 * @param background
	 * @param fontStyle
	 * @author EJ.Park
	 * @since 2012. 11. 8.
	 */
	public AbstractLineStyleListener(Color foreground, Color background, int fontStyle){
		this.foreground = foreground;
		this.background = background;
		this.fontStyle = fontStyle;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.custom.LineStyleListener#lineGetStyle(org.eclipse.swt.custom.LineStyleEvent)
	 * @author EJ.Park
	 * @since 2012. 11. 8.
	 * 
	 *  It should be Implemented by child class what will be get defined style(foreground, background and font style).
	 */
	@Override
	public abstract void lineGetStyle(LineStyleEvent event);
	
	/**
	 * @return StyleRange
	 * @author EJ.Park
	 * @since 2012. 11. 8.
	 */
	public StyleRange getStyleRange(int startPoint, int endPoint){
		return new StyleRange(startPoint, endPoint, this.foreground, this.background, this.fontStyle);
	}
}
