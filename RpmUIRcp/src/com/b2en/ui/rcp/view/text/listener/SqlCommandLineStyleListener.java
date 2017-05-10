package com.b2en.ui.rcp.view.text.listener;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

import com.b2en.rpm.ui.rcp.util.SWTResourceUtil;

/**
 * SqlCommandLineStyleListener.java
 *
 * @author EJ.Park
 * @since 2012. 11. 8.
 */
public class SqlCommandLineStyleListener extends AbstractLineStyleListener{
	
	private static final Color KEYWORD_COLOR = SWTResourceUtil.getColor(SWT.COLOR_BLUE);
	private static final Color COMMENT_COLOR = SWTResourceUtil.getColor(SWT.COLOR_DARK_GREEN);
	private static final Color VAR_COLOR = SWTResourceUtil.getColor(SWT.COLOR_RED);
	
    private List<String> keywords = new ArrayList<String>();
    private List<String> compKeywords = new ArrayList<String>();
	
	/**
	 * 
	 * SqlCommandLineStyleListener
	 *
	 * @param foreground
	 * @param background
	 * @param fontStyle
	 * @author EJ.Park
	 * @since 2012. 11. 8.
	 */
	public SqlCommandLineStyleListener(Color foreground, Color background, int fontStyle){
		super(foreground, background, fontStyle);
		keywords.add("select");
		keywords.add("insert");
		keywords.add("update");
		keywords.add("delete");
		keywords.add("count");
        keywords.add("from");
        keywords.add("where");
        keywords.add("and");
        keywords.add("or");
        keywords.add("over");
        
        compKeywords.add("order");
        compKeywords.add("group");
        compKeywords.add("partition");
        compKeywords.add("connect");
	}
	
	/* (non-Javadoc)
	 * @see com.b2en.ui.rcp.view.text.listener.AbstractLineStyleListener#lineGetStyle(org.eclipse.swt.custom.LineStyleEvent)
	 */
	@Override
	public void lineGetStyle(LineStyleEvent event) {
		List<StyleRange> styles = new ArrayList<StyleRange>();
        int start = 0;
        int length = event.lineText.length();
        while (start < length) {
        	boolean isCommentEnd = true;
            if (Character.isLetter(event.lineText.charAt(start))) {
                StringBuffer buf = new StringBuffer();
                int i = start;
                for (; i < length && Character.isLetter(event.lineText.charAt(i)); i++) {
                    buf.append(event.lineText.charAt(i));
                }
                if (keywords.contains(buf.toString().toLowerCase())) {
                    styles.add(new StyleRange(event.lineOffset + start, i - start, KEYWORD_COLOR, null, SWT.BOLD));
                }
                
                if (compKeywords.contains(buf.toString().toLowerCase())) {
                	if((i+1)<length && event.lineText.substring(i+1).toLowerCase().startsWith("by")){
                        styles.add(new StyleRange(event.lineOffset + start, (i - start)+3, KEYWORD_COLOR, null, SWT.BOLD));
                    }	
                }
                
                start = i;
            }else{
            	if(event.lineText.charAt(start)=='\''){
            		int i = start+1;
                    for (; i < length; i++) {
                    	if(i+1<length && event.lineText.charAt(i)=='\''){
                    		styles.add(new StyleRange(event.lineOffset + start, (i - start)+1, VAR_COLOR, null, SWT.NONE));
                    		start = i+1;
                    		break;
                    	}
                    }
                    start = i+1;
            	}else if(event.lineText.substring(start).startsWith("/*") || !isCommentEnd){
            		int i = start;
                    for (; i < length; i++) {
                    	if(event.lineText.substring(start,i+1).endsWith("*/")){
                    		styles.add(new StyleRange(event.lineOffset + start, i - start, COMMENT_COLOR, null, SWT.NONE));
                    		start = i;
                    		isCommentEnd = true;
                    	}
                    }
                    
                    if(!isCommentEnd){
                    	isCommentEnd = false;
                    	styles.add(new StyleRange(event.lineOffset + start, length, COMMENT_COLOR, null, SWT.NONE));
                    	start = length;
                    }
            	}else if(event.lineText.substring(start).startsWith("--")){
            		styles.add(new StyleRange(event.lineOffset + start, length, COMMENT_COLOR, null, SWT.NONE));
            		start = length;
            	}else{
            		start ++;            		
            	}
            }
        }
        
        if(styles.size()>0){
        	event.styles = (StyleRange[]) styles.toArray(new StyleRange[0]);        	
        }
	}
}
