package com.bootstrap.dao.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Score;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;

@Data
@SolrDocument(collection = "chromosomes_rs")
public class SolrLocusRsDocument {


	public SolrLocusRsDocument() {
	}

	@Id
	@Field
	private Integer id;

	@Score
	private float score;

	@Field
	private String locusName;

	@Field
	private String locusRole;

	@Field
	private String diseaseName;

	@Field
	private String diseaseRole;

	@Field
	private String fullName;

	@Field
	private Integer chromId;

	@Field
	private Integer locusId;

}
