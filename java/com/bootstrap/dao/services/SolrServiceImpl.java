package com.bootstrap.dao.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Locus;
import com.bootstrap.dao.model.SolrLocusEnDocument;
import com.bootstrap.dao.model.SolrLocusRsDocument;
import com.bootstrap.dao.repositories.solr.MatchEnRepository;
import com.bootstrap.dao.repositories.solr.MatchRsRepository;
import com.bootstrap.dao.repositories.solr.SolrLocusEnRepository;
import com.bootstrap.dao.repositories.solr.SolrLocusRsRepository;

import lombok.Data;

@Service("solrService")
@Transactional
@Data
public class SolrServiceImpl implements SolrService {

	private MatchEnRepository matchEnRepo;
	private MatchRsRepository matchRsRepo;
	private SolrLocusEnRepository solrEnRepository;
	private SolrLocusRsRepository solrRsRepository;

	public SolrServiceImpl(SolrLocusEnRepository solrEnRepository, SolrLocusRsRepository solrRsRepository, 
					MatchEnRepository matchEnRepo,MatchRsRepository matchRsRepo) {
		this.solrEnRepository = solrEnRepository;
		this.solrRsRepository = solrRsRepository;
		this.matchEnRepo = matchEnRepo;
		this.matchRsRepo = matchRsRepo;
	}

	@Transactional
	public void saveSolrLocusDocument(String lang, Locus locus) {
			saveNewSolrDoc(lang, locus);
	}

	private void saveNewSolrDoc(String lang, Locus locus) {
		switch (lang) {
		case "en":
			SolrLocusEnDocument solrEnDoc = new SolrLocusEnDocument();
			solrEnDoc.setLocusName(locus.getName());
			solrEnDoc.setLocusRole(locus.getBioRole());
			solrEnDoc.setFullName(locus.getFullName());
			solrEnDoc.setId(locus.getId());
			solrEnDoc.setChromId(locus.getChromosome().getId());
			solrEnDoc.setDiseaseRole(locus.getDisease().getDescription());
			solrEnDoc.setLocusId(locus.getId());
			solrEnDoc.setDiseaseName(locus.getDisease().getName());
			solrEnRepository.save(solrEnDoc);
			break;
		case "rs":
			SolrLocusRsDocument solrRsDoc = new SolrLocusRsDocument();

			solrRsDoc.setLocusName(locus.getName());
			solrRsDoc.setLocusRole(locus.getBioRole());
			solrRsDoc.setFullName(locus.getFullName());
			solrRsDoc.setId(locus.getId());
			solrRsDoc.setChromId(locus.getChromosome().getId());
			solrRsDoc.setDiseaseRole(locus.getDisease().getDescription());
			solrRsDoc.setLocusId(locus.getId());
			solrRsDoc.setDiseaseName(locus.getDisease().getName());
			solrRsRepository.save(solrRsDoc);
			break;
		}
	}
}