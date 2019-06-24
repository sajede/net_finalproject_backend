package ir.asta.training.contacts.dao;

import ir.asta.training.contacts.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Named("userDao")
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(UserEntity entity) {
		entityManager.persist(entity);
	}

	public void update(UserEntity entity) {
		entityManager.merge(entity);
	}


	
	public UserEntity load(Long id) {
		UserEntity userEntity = entityManager.find(UserEntity.class,id);
		return userEntity;
	}

	public UserEntity loadUserByEmail(String email) {

		Query query = entityManager.createNamedQuery("find user by email");
		query.setParameter("emailAddress", email);
		List<UserEntity> list = query.getResultList( );
		UserEntity resultUserEntity;
		if (list.size()==0){
			resultUserEntity = null;
		}else {
			resultUserEntity =list.get(0);
		}
		return resultUserEntity;
	}

	public UserEntity loadUserBySessionId(String sessionId) {

		Query query = entityManager.createNamedQuery("find user by sessionid");
		query.setParameter("sessionId", sessionId);
		List<UserEntity> list = query.getResultList( );
		UserEntity resultUserEntity;
		if (list.size()==0){
			resultUserEntity = null;
		}else {
			resultUserEntity =list.get(0);
		}
		return resultUserEntity;
	}


	public void updateUserSessionID(long userID,String sessionID){
		UserEntity userEntity= entityManager.find(UserEntity.class, userID);
		userEntity.setSessionId(sessionID);
		entityManager.merge(userEntity);
	}
	
}
