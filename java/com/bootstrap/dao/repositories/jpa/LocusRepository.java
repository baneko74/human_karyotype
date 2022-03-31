package com.bootstrap.dao.repositories.jpa;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Locus;

@Repository
@Transactional(readOnly = true)
public interface LocusRepository extends CrudRepository<Locus, Integer> {

	Locus findByLink(String name);

	@Query("select l from Locus l where l.link = :link and l.lang = :lang")
	Locus findByLink(@Param("link") String name, @Param("lang") String lang);

	@Query("select distinct l from Chromosome c join c.loci l where c.id = :id and c.lang = :lang")
	Set<Locus> findAllLociByChromosome(@Param("id") Integer id, @Param("lang") String lang);

}
