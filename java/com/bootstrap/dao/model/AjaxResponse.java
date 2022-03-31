package com.bootstrap.dao.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.experimental.SuperBuilder;

@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxResponse {

	private String status;
	private String message;

	public AjaxResponse() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
