package com.bootstrap.dao.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Match;
import com.bootstrap.dao.repositories.solr.MatchEnRepository;
import com.bootstrap.dao.repositories.solr.MatchRsRepository;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

	private static final Pattern IGNORED_CHARS_PATTERN = Pattern.compile("\\p{Punct}");

	private MatchEnRepository matchEnRepo;
	private MatchRsRepository matchRsRepo;
	private MessageSource messageSource;

	public MatchServiceImpl(MatchEnRepository matchEnRepo, MatchRsRepository matchRsRepo, MessageSource messageSource) {
		this.matchEnRepo = matchEnRepo;
		this.matchRsRepo = matchRsRepo;
		this.messageSource = messageSource;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Match> findByTerm(String searchTerm, Pageable pageable, String lang) throws IllegalArgumentException {
		if (StringUtils.isEmpty(searchTerm)) {
			throw new IllegalArgumentException(
					messageSource.getMessage("empty.search", null, LocaleContextHolder.getLocale()));
		}
		if (lang.equals("en")) {
			return matchEnRepo.findByNameIn(splitSearchTerms(searchTerm), pageable);
		} else {
			return matchRsRepo.findByNameIn(splitSearchTerms(searchTerm), pageable);
		}
	}

	private Collection<String> splitSearchTerms(String searchTerm) {
		String[] searchTerms = StringUtils.split(searchTerm, " ");
		List<String> result = new ArrayList<>(searchTerms.length);
		for (String term : searchTerms) {
			if (StringUtils.isNotEmpty(term)) {
				result.add(IGNORED_CHARS_PATTERN.matcher(term).replaceAll(" "));
			}
		}
		return result;
	}
}
