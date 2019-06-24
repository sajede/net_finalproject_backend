package ir.asta.training.contacts.dao;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;

@Named("contactDao")
public class ContactDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(ContactEntity entity) {
		entityManager.persist(entity);
	}
	
	public ContactEntity load(Long id) {
		entityManager.find(UserEntity.class,id);
		return null;
	}
	
	public List<ContactEntity> findAll() {
		// TODO implement this method
		return null;
	}
	
}
