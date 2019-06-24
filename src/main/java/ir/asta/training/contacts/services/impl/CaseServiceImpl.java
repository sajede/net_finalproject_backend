package ir.asta.training.contacts.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.asta.training.contacts.Util.IdGenerator;
import ir.asta.training.contacts.entities.CaseEntity;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.training.contacts.manager.CaseManager;
import ir.asta.training.contacts.manager.UserManager;
import ir.asta.training.contacts.services.CaseService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Named("caseService")
public class CaseServiceImpl implements CaseService {

	@Context
	HttpServletRequest servletRequest;
	ObjectMapper mapper = new ObjectMapper();

	@Inject
	CaseManager manager;

	@Inject
	UserManager userManager;

	@Inject
	IdGenerator idGenerator;


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

	/*@Override
	public Response add(CaseEntity entity) throws IOException {

		String sessionID = servletRequest.getHeader("SessionID");
		Map<String,String> result;
		JsonNode node;

		if (sessionID == null || sessionID.equals("Not assigned")){
			sessionID = idGenerator.generateId(10);
			node = mapper.convertValue(new ActionResult<UserEntity>(false, "User haven't session id",null), JsonNode.class);
			return Response.status(420).entity(mapper.writeValueAsString(node)).header("SessionID",sessionID).build();
		//
		}else {
			result = userManager.handleUserAccess(sessionID,"all");
			if (Boolean.parseBoolean(result.get("success"))){
				manager.doUpdateProccess(entity);
				node = mapper.convertValue(new ActionResult<UserEntity>(Boolean.parseBoolean(result.get("success")), result.get("message"), null), JsonNode.class);
				return Response.status(201).entity(mapper.writeValueAsString(node)).header("SessionID", sessionID).build();
			}else {
				node = mapper.convertValue(new ActionResult<UserEntity>(Boolean.parseBoolean(result.get("success")), result.get("message"), null), JsonNode.class);
				return Response.status(420).entity(mapper.writeValueAsString(node)).header("SessionID", sessionID).build();
			}
		}

	}
*/

}
