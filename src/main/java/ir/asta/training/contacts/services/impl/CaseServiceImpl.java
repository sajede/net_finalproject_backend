package ir.asta.training.contacts.services.impl;

import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.training.contacts.manager.CaseManager;
import ir.asta.training.contacts.manager.UserManager;
import ir.asta.training.contacts.services.CaseService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;

@Named("caseService")
public class CaseServiceImpl implements CaseService {

	@Inject
	CaseManager manager;
	
	@Override
	public CaseEntity load(Long id) {

		CaseEntity caseEntity= manager.load(id);
		return caseEntity;
	}

	@Override
	public ActionResult<Long> save(CaseEntity entity) {

		manager.save(entity);
		return new ActionResult<Long>(true, "New case added successfully.", entity.getId());
	}
}
