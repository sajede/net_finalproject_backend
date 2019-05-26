package ir.asta.training.contacts.dao;

import ir.asta.training.contacts.entities.ReferrerEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("referrerDao")
public class ReferrerDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(ReferrerEntity entity) {
		entityManager.persist(entity);
	}
	
	public ReferrerEntity load(Long id) {
		ReferrerEntity referrerEntity = entityManager.find(ReferrerEntity.class,id);
		return referrerEntity;
	}
	
	public List<ReferrerEntity> findAll() {

		return null;
	}
	
}
