package ir.asta.training.contacts.services.impl;

import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.training.contacts.manager.UserManager;
import ir.asta.training.contacts.services.UserService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;

@Named("userService")
public class UserServiceImpl implements UserService {

	@Inject
	UserManager manager;
	
	@Override
	public UserEntity load(Long id) {

		UserEntity userEntity= manager.load(id);
		return userEntity;
	}

	@Override
	public ActionResult<Long> save(UserEntity entity) {

		manager.save(entity);
		return new ActionResult<Long>(true, "New user register successfully.", entity.getId());
	}
}
