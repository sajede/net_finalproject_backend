package ir.asta.training.contacts.manager;

import ir.asta.training.contacts.Util.IdGenerator;
import ir.asta.training.contacts.dao.UserDao;
import ir.asta.training.contacts.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public UserEntity test(String username){

		return dao.loadUserByEmail(username);
	}


	public Map<String,String> doLoginProccess(String email,String password,String sessionID){
		Map<String,String> result = new HashMap<>();

		UserEntity userEntity = dao.loadUserByEmail(email);
		if (userEntity==null || !userEntity.isApproved() || !password.equals(userEntity.getPassword())){
			result.put("access","false");
			result.put("role",null);
		}else {
			result.put("access","true");
			result.put("role",userEntity.getRole());
			userEntity.setSessionId(sessionID);
			dao.updateUserSessionID(userEntity.getId(),sessionID);
		}
		return result;

	}

	public Map<String,String> doRegisterProccess(UserEntity userEntity){

		Map<String,String> result = new HashMap<>();

		UserEntity entity = dao.loadUserByEmail(userEntity.getEmailAddress());
		if (entity==null){
			if (userEntity.getPassword().length()<6){
				result.put("success","false");
				result.put("message","Password size must be more than 5 character");
			}else {
				dao.save(userEntity);
				result.put("success","true");
				result.put("message","User registered successfully");
			}
		}else {
			result.put("success","false");
			result.put("message","User with this email exist");
		}

		return result;

	}

	public Map<String,String> doUpdateProccess(UserEntity userEntity){

		Map<String,String> result = new HashMap<>();

		UserEntity entity = dao.loadUserByEmail(userEntity.getEmailAddress());
		if (entity==null){
			result.put("success","false");
			result.put("message","There isn't this user !");
		}else {
			entity.setName(userEntity.getName());
			entity.setPassword(userEntity.getPassword());
			entity.setEmailAddress(userEntity.getEmailAddress());
			entity.setFamily(userEntity.getFamily());
			entity.setRole(userEntity.getRole());

			dao.update(entity);
			result.put("success","true");
			result.put("message","User update successfully");
		}
		return result;

	}

	public Map<String,String> handleUserAccess(String sessionId ,String role){

		Map<String,String> result = new HashMap<>();
		UserEntity userEntity = dao.loadUserBySessionId(sessionId);
		if (userEntity!=null){
			result.put("success","false");
			result.put("message","User doesn't login!");
		}else if (role.equals("all")){
			result.put("success","true");
			result.put("message","User have access !");
		}else {
			if (userEntity.getRole().equals(role)){
				result.put("success","true");
				result.put("message","User have access !");
			}else {
				result.put("success","false");
				result.put("message","User haven't access to this page!");
			}
		}
		return result;

	}



	
}
