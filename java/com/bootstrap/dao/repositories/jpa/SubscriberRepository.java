package com.bootstrap.dao.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bootstrap.dao.model.Subscriber;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {

	@Query("select case when count(s) > 0 then true else false end from Subscriber s where s.email = :email")
	boolean existByEmail(@Param("email") String email);

	@Query("select distinct s from Subscriber s where s.lang = :lang")
	List<Subscriber> findAllByLang(@Param("lang") String lang);

	@Query("select distinct s from Subscriber s where s.sha1 = :code")
	Optional<Subscriber> findBySha1(@Param("code") String code);
}
