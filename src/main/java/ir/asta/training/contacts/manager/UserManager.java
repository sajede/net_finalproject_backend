package ir.asta.training.contacts.manager;

import ir.asta.training.contacts.Util.IdGenerator;
import ir.asta.training.contacts.dao.ContactDao;
import ir.asta.training.contacts.dao.UserDao;
import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("userManager")
public class UserManager {

	@Inject
	UserDao dao;

	@Inject
	IdGenerator sessionIdGenarator;

	@Transactional
	public void save(UserEntity entity) {
		dao.save(entity);
	}

	public UserEntity load(Long id) {
		return dao.load(id);
	}
	
	public List<UserEntity> findAll() {
		// TODO implement this method
		return null;
	}

	
}
