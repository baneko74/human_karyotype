package com.bootstrap.dao.repositories.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Disease;

@Repository
@Transactional(readOnly = true)
public interface DiseaseRepository extends CrudRepository<Disease, Integer> {

	@Query("select distinct d from Locus l JOIN l.disease d where l.name = :name and l.lang = :lang")
	Disease findDiseaseByLocusName(@Param("name") String name, @Param("lang") String lang);

}
