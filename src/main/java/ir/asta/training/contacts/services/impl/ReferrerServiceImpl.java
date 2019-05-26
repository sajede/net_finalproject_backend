package ir.asta.training.contacts.services.impl;

import ir.asta.training.contacts.entities.ReferrerEntity;
import ir.asta.training.contacts.manager.ReferrerManager;
import ir.asta.training.contacts.services.ReferrerService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;

@Named("referrerService")
public class ReferrerServiceImpl implements ReferrerService {

	@Inject
	ReferrerManager manager;
	
	@Override
	public ReferrerEntity load(Long id) {

		ReferrerEntity referrerEntity= manager.load(id);
		return referrerEntity;
	}

	@Override
	public ActionResult<Long> save(ReferrerEntity entity) {

		manager.save(entity);
		return new ActionResult<Long>(true, "New user register successfully.", entity.getId());
	}
}
