package com.bootstrap.dao.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bootstrap.dao.model.Match;

public interface MatchService {

	int DEFAULT_PAGE_SIZE = 10;

	Page<Match> findByTerm(String searhTerm, Pageable pageable, String lang);

}
