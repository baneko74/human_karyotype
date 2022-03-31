package com.bootstrap.dao.services;

import com.bootstrap.dao.model.Locus;

public interface SolrService {
	void saveSolrLocusDocument(String lang, Locus locus);
}
