package com.bootstrap.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.bootstrap.dao.services.UniqueEmail;

@Entity
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 1001L;

	@Version
	@Column(name = "version")
	private Integer version;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "{com.bootstrap.dao.NotEmpty.message}")
	@UniqueEmail(message = "{com.bootstrap.dao.UniqueEmail.message}")
	@Email(message = "{com.bootstrap.dao.Email.message}")
	private String email;

	@Column
	private String lang;

	@Column
	private String sha1;

	public Subscriber() {
	}

	public Subscriber(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSha1() {
		return sha1;
	}

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

}
