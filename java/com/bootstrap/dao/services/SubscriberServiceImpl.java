package com.bootstrap.dao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootstrap.dao.model.Subscriber;
import com.bootstrap.dao.repositories.jpa.SubscriberRepository;

@Service("subscriberService")
@Transactional
public class SubscriberServiceImpl implements SubscriberService {

	@Autowired
	private final SubscriberRepository subscriberRepo;

	public SubscriberServiceImpl(SubscriberRepository subscriberRepo) {
		this.subscriberRepo = subscriberRepo;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subscriber> findAll() {
		return (List<Subscriber>) subscriberRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Subscriber> findByEmail(String email) {
		return ((List<Subscriber>) subscriberRepo.findAll()).stream().filter(sub -> sub.getEmail().equals(email))
				.findFirst();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Subscriber> findAllByLang(String lang) {
		return subscriberRepo.findAllByLang(lang);
	}

	@Override
	@Transactional(readOnly = true)
	public Subscriber findById(Long id) {
		return subscriberRepo.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Subscriber> findBySha1(String code) {
		return subscriberRepo.findBySha1(code);
	}

	@Override
	public Subscriber save(Subscriber subscriber) {
		return subscriberRepo.save(subscriber);
	}

	@Override
	public void delete(Subscriber subscriber) {
		subscriberRepo.delete(subscriber);

	}

	@Override
	@Transactional(readOnly = true)
	public boolean existByEmail(String email) {
		return subscriberRepo.existByEmail(email);
	}

}
