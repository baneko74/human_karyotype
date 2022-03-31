package com.bootstrap.dao.repositories.solr;

import java.util.Collection;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.Highlight;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

import com.bootstrap.dao.model.Match;
import com.bootstrap.dao.model.MatchEn;

public interface MatchEnRepository extends SolrCrudRepository<MatchEn, Integer> {

	@Query(value = "{!edismax v='?0' qf='chromosomeName description^5.0 locusName fullName locusRole^3.0 diseaseRole^2.0 content' pf='description locusRole diseaseRole content' pf2='description^15.0 locusRole^8.0 diseaseRole^10 content' q.op='OR'}", fields = {
			"id", "chromosomeName", "locusName", "chromId", "link", "linkName", "locusUrl" })
	@Highlight(prefix = "<span class='highlight'>", postfix = "</span>", fields = { "description", "locusRole",
			"diseaseRole", "fullName", "content" }, fragsize = 199)
	HighlightPage<Match> findByNameIn(@Boost(2) Collection<String> names, Pageable pageable);
}
