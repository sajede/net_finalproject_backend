package ir.asta.training.contacts.manager;

import ir.asta.training.contacts.dao.CaseDao;
import ir.asta.training.contacts.dao.UserDao;
import ir.asta.training.contacts.entities.CaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("caseManager")
public class CaseManager {

	@Inject
	CaseDao dao;

	@Transactional
	public void save(CaseEntity entity) {
		dao.save(entity);
	}

	public CaseEntity load(Long id) {
		return dao.load(id);
	}
	
	public List<CaseEntity> findAll() {
		// TODO implement this method
		return null;
	}

	
}
