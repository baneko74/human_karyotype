package com.bootstrap.dao.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsLetter implements Serializable {
	private static final long serialVersionUID = 1001L;
	@NotEmpty(message = "{email.subject.empty.error.msg}")
	private String subject;
	@NotEmpty(message = "{email.body.empty.error.msg}")
	private String body;

}
