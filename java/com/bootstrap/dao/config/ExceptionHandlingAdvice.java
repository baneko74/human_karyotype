package com.bootstrap.dao.config;

import java.net.ConnectException;

import org.apache.http.conn.HttpHostConnectException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingAdvice {

	@ExceptionHandler({ HttpHostConnectException.class })
	public String solrError() {
		return "service_not_found";
	}

	@ExceptionHandler({ JDBCConnectionException.class, ConnectException.class, SQLGrammarException.class,
			InvalidDataAccessResourceUsageException.class })
	public String mysqlConnectError() {
		return "mysql_connect_error";
	}
}
