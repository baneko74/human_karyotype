package com.bootstrap.dao.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Chromosome;
import com.bootstrap.dao.model.Disease;
import com.bootstrap.dao.model.Locus;
import com.bootstrap.dao.repositories.jpa.ChromosomeRepository;
import com.bootstrap.dao.repositories.jpa.DiseaseRepository;
import com.bootstrap.dao.repositories.jpa.LocusRepository;

@Service("chromosomeService")
public class ChromosomeServiceImpl implements ChromosomeService {

	private final LocusRepository locusRepository;

	private final ChromosomeRepository chromosomeRepository;

	private final DiseaseRepository diseaseRepository;

	public ChromosomeServiceImpl(ChromosomeRepository chromosomeRepository, LocusRepository locusRepository,
			DiseaseRepository diseaseRepository) {
		this.chromosomeRepository = chromosomeRepository;
		this.locusRepository = locusRepository;
		this.diseaseRepository = diseaseRepository;
	}

	@Override
	public List<Chromosome> findAll() {
		return chromosomeRepository.findAll();
	}

	@Override
	public List<Chromosome> findAllWithLoci() {
		return chromosomeRepository.findAllWithLoci();
	}

	@Override
	public Optional<Chromosome> findById(Integer id) {
		return chromosomeRepository.findById(id);
	}

	@Override
	public Chromosome findByName(String name) {
		return chromosomeRepository.findByName(name);
	}

	@Override
	public List<Locus> findAllLoci() {
		return chromosomeRepository.findAllLoci();
	}

	@Override
	public Page<Chromosome> findAll(Pageable pageable) {
		return chromosomeRepository.findAll(pageable);
	}

	@Override
	public List<Chromosome> findAllWithLociAndDisease() {
		return chromosomeRepository.findAllWithLociAndDisease();
	}

	@Override
	public Set<Locus> findAllLociByChromosome(Integer id, String lang) {
		return locusRepository.findAllLociByChromosome(id, lang);
	}

	@Override
	public Locus findLocusById(Integer id) {
		return locusRepository.findById(id).get();
	}

	@Override
	public Locus findLocusByName(String link) {
		return locusRepository.findByLink(link);
	}

	@Override
	public Locus findLocusByName(String link, String lang) {
		return locusRepository.findByLink(link, lang);
	}

	@Override
	public Page<Chromosome> findAll(Pageable pageable, String lang) {
		return chromosomeRepository.findAll(pageable, lang);
	}

	@Override
	public List<Chromosome> findAll(String lang) {
		return chromosomeRepository.findAll(lang);
	}

	@Override
	public Chromosome findById(Integer id, String lang) {
		return chromosomeRepository.findById(id, lang);
	}

	@Override
	public Chromosome findChromosomeByLocusName(String name, String lang) {
		return chromosomeRepository.findChromosomeByLocusName(name, lang);
	}

	@Override
	@Transactional
	public Chromosome saveChromosome(Chromosome chromosome) {
		return chromosomeRepository.save(chromosome);
	}

	@Override
	public Disease findDiseaseByLocusName(String name, String lang) {
		return diseaseRepository.findDiseaseByLocusName(name, lang);
	}

	@Override
	@Transactional
	public Locus saveLocus(Locus locus) {
		return locusRepository.save(locus);
	}

	@Override
	@Transactional
	public Disease saveDisease(Disease disease) {
		return diseaseRepository.save(disease);
	}

}
