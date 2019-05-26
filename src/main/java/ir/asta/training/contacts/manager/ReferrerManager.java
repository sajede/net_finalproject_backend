package ir.asta.training.contacts.manager;

import ir.asta.training.contacts.dao.ReferrerDao;
import ir.asta.training.contacts.entities.ReferrerEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("referrerManager")
public class ReferrerManager {

	@Inject
	ReferrerDao dao;

	@Transactional
	public void save(ReferrerEntity entity) {
		dao.save(entity);
	}

	public ReferrerEntity load(Long id) {
		return dao.load(id);
	}
	
	public List<ReferrerEntity> findAll() {
		// TODO implement this method
		return null;
	}

	
}
