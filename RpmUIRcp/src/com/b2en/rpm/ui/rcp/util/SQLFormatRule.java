package com.b2en.rpm.ui.rcp.util;

import kry.sql.format.ISqlFormatRule;
import kry.sql.format.SqlFormatRule;
import kry.sql.util.StringUtil;

/**
 *
 */
public class SQLFormatRule {
	
	/**
	 */
	public static SqlFormatRule getSqlFormatRule() {
		SqlFormatRule rule = new SqlFormatRule();
		
		rule.setIndentString(StringUtil.padLeft("", 4, ' '));
		rule.setDecodeSpecialFormat(true);
		rule.setInSpecialFormat(true);
		rule.setOutSqlSeparator(SqlFormatRule.SQL_SEPARATOR_SEMICOLON);
		rule.setRemoveEmptyLine(true);
		rule.setIndentEmptyLine(true);
		rule.setConvertName(ISqlFormatRule.CONVERT_STRING_NONE);
		rule.setConvertKeyword(ISqlFormatRule.CONVERT_STRING_NONE);
		rule.setNewLineBeforeAndOr(true);
		rule.setNewLineBeforeComma(true);
		rule.setWordBreak(true);
		rule.setWidth(100);
		
		return rule;
	}
}
