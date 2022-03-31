package com.bootstrap.dao.repositories.solr;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.bootstrap.dao.model.SolrLocusRsDocument;

public interface SolrLocusRsRepository extends SolrCrudRepository<SolrLocusRsDocument, Integer> {

}
