package ir.asta.training.contacts.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ir.asta.training.contacts.Util.IdGenerator;
import ir.asta.training.contacts.entities.UserEntity;
import ir.asta.training.contacts.manager.UserManager;
import ir.asta.training.contacts.services.UserService;
import ir.asta.wise.core.datamanagement.ActionResult;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Named("userService")
public class UserServiceImpl implements UserService {

	@Context HttpServletRequest servletRequest;
	ObjectMapper mapper = new ObjectMapper();

	@Inject
	UserManager manager;

	@Inject
	IdGenerator idGenerator;

	@Override
	public UserEntity load(Long id) {

		UserEntity userEntity = manager.load(id);
		return userEntity;
	}

	@Override
	public ActionResult<UserEntity> save(UserEntity entity) throws IOException {

		manager.save(entity);
		ObjectNode resultJson = mapper.createObjectNode();
		String json=mapper.writeValueAsString(entity);
		resultJson = (ObjectNode) mapper.readTree(json);
		return new ActionResult<UserEntity>(true, "New user register successfully.",entity);
	}

	@Override
	public Response register(UserEntity entity) throws IOException {

		String sessionID = servletRequest.getHeader("SessionID");
		Map<String,String> result;

		if (sessionID == null || sessionID.equals("Not assigned")){
			sessionID = idGenerator.generateId(10);}

		result = manager.doRegisterProccess(entity);
		JsonNode node = mapper.convertValue(new ActionResult<UserEntity>(Boolean.parseBoolean(result.get("success")), result.get("message"),null), JsonNode.class);

		return Response.status(201).entity(mapper.writeValueAsString(node)).header("SessionID",sessionID).build();
	}

	@Override
	public Response update(UserEntity entity) throws IOException {

		String sessionID = servletRequest.getHeader("SessionID");
		Map<String,String> result;
		JsonNode node;

		if (sessionID == null || sessionID.equals("Not assigned")){
			sessionID = idGenerator.generateId(10);
			node = mapper.convertValue(new ActionResult<UserEntity>(false, "User haven't session id",null), JsonNode.class);
			return Response.status(420).entity(mapper.writeValueAsString(node)).header("SessionID",sessionID).build();
		}else {
			result = manager.handleUserAccess(sessionID,"all");
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

	@Override
	public Response login(UserEntity entity) throws JsonProcessingException {

		String sessionID = servletRequest.getHeader("SessionID");
		Map<String,String> result;

		if (sessionID == null || sessionID.equals("Not assigned")){
			sessionID = idGenerator.generateId(10);}
		result = manager.doLoginProccess(entity.getEmailAddress(),entity.getPassword(),sessionID);

		ObjectNode resultJson = mapper.createObjectNode();
		resultJson.put("role",result.get("role"));

		return Response.status(201).entity(mapper.writeValueAsString(resultJson)).header("access",result.get("access")).header("SessionID",sessionID).build();
	}



	@Override
	public Response test(UserEntity entity) throws JsonProcessingException {

		UserEntity userEntity = manager.test(entity.getName());

		String json = null;
		try {
			json = mapper.writeValueAsString(userEntity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ObjectNode jsonNode = new ObjectMapper().createObjectNode();
		try {
			jsonNode = (ObjectNode) mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}


		//return Response.status(201).entity(new ObjectMapper().writeValueAsString(jsonNode)).build();
		return Response.status(201).entity(servletRequest.getHeader("User-Agent")).build();

	}


}
