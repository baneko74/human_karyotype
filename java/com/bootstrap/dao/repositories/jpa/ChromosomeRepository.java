package com.bootstrap.dao.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Chromosome;
import com.bootstrap.dao.model.Locus;

@Repository
@Transactional(readOnly = true)
public interface ChromosomeRepository extends PagingAndSortingRepository<Chromosome, Integer> {
	@Override
	List<Chromosome> findAll();

	Chromosome findByName(String name);

	@Override
	Optional<Chromosome> findById(Integer id);

	@Query("select distinct c from Chromosome c where c.id = :id and c.lang = :lang")
	Chromosome findById(@Param("id") Integer id, @Param("lang") String lang);

	@Query("select distinct c from Chromosome c left join fetch c.loci l")
	List<Chromosome> findAllWithLoci();

	@Query("select l from Chromosome c JOIN c.loci l")
	List<Locus> findAllLoci();

	@Query("select distinct c from Chromosome c left join fetch c.loci l join l.disease d")
	List<Chromosome> findAllWithLociAndDisease();

	@Query("select distinct c from Chromosome c where c.lang = :lang")
	Page<Chromosome> findAll(Pageable pageable, @Param("lang") String lang);

	@Query("select distinct c from Chromosome c where c.lang = :lang")
	List<Chromosome> findAll(@Param("lang") String lang);

	@Query("select distinct c from Chromosome c join c.loci l where l.name = :name and l.lang = :lang")
	Chromosome findChromosomeByLocusName(@Param("name") String name, @Param("lang") String lang);

}
