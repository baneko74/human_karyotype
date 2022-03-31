package com.bootstrap.dao.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bootstrap.dao.model.Chromosome;
import com.bootstrap.dao.model.Disease;
import com.bootstrap.dao.model.Locus;

public interface ChromosomeService {

	List<Chromosome> findAll();

	List<Chromosome> findAllWithLoci();

	Optional<Chromosome> findById(Integer id);

	Chromosome findById(Integer id, String lang);

	Chromosome findByName(String name);

	List<Locus> findAllLoci();

	Page<Chromosome> findAll(Pageable pageable);

	Page<Chromosome> findAll(Pageable pageable, String lang);

	List<Chromosome> findAll(String lang);

	List<Chromosome> findAllWithLociAndDisease();

	Set<Locus> findAllLociByChromosome(Integer id, String lang);

	Locus findLocusById(Integer id);

	Locus findLocusByName(String name);

	Locus findLocusByName(String name, String lang);

	Locus saveLocus(Locus locus);

	Chromosome findChromosomeByLocusName(String name, String lang);

	Chromosome saveChromosome(Chromosome chromosome);

	Disease findDiseaseByLocusName(String name, String lang);

	Disease saveDisease(Disease disease);

}
