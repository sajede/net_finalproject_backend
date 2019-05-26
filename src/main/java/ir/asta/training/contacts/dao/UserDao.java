package ir.asta.training.contacts.dao;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named("userDao")
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(UserEntity entity) {
		entityManager.persist(entity);
	}
	
	public UserEntity load(Long id) {
		UserEntity userEntity = entityManager.find(UserEntity.class,id);
		return userEntity;
	}
	
	public List<ContactEntity> findAll() {

		return null;
	}
	
}
