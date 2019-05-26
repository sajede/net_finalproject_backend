package ir.asta.training.contacts.services;

import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/case")
public interface CaseService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/load/{pk}")
	public CaseEntity load(@PathParam("pk") Long id);

	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActionResult<Long> save(CaseEntity entity);
	
}
