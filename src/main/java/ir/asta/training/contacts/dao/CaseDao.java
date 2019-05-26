package ir.asta.training.contacts.dao;

import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("caseDao")
public class CaseDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(CaseEntity entity) {
		entityManager.persist(entity);
	}
	
	public CaseEntity load(Long id) {
		CaseEntity caseEntity = entityManager.find(CaseEntity.class,id);
		return caseEntity;
	}
	
	public List<CaseEntity> findAll() {

		return null;
	}
	
}
