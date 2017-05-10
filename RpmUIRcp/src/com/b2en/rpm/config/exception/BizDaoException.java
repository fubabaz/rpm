package com.b2en.rpm.config.exception;

import com.b2en.framework.exception.AbstractSystemException;

public class BizDaoException extends AbstractSystemException {

	private static final long serialVersionUID = 1L;

	public BizDaoException(String errorMessage, Throwable throwable) {
		super("SYS_DAO002", errorMessage, throwable);
	}
}
