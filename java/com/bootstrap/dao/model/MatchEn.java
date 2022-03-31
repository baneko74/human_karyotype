package com.bootstrap.dao.model;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SolrDocument(collection = "chromosomes")
public class MatchEn implements Match {

	@Field
	@Id
	private Integer id;

	@Field
	private String chromosomeName;

	@Field
	private String locusName;

	@Field
	private Integer chromId;

	@Field
	private String link;

	@Field
	private String linkName;

	@Field
	private String locusUrl;

}
