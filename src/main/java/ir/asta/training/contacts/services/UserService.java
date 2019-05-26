package ir.asta.training.contacts.services;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/load/{pk}")
	public UserEntity load(@PathParam("pk") Long id);

	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ActionResult<Long> save(UserEntity entity);
	
}
