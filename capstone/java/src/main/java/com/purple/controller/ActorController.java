package com.purple.controller;


import com.purple.dao.ActorDao;


import com.purple.dao.MovieDao;
import com.purple.handler.ActorHandler;
import com.purple.handler.ActorHandlerable;
import com.purple.handler.UserHandlerable;
import com.purple.model.Actor;
import com.purple.model.Movie;
import com.purple.model.MovieSearchCriteria;
import com.purple.model.User;
import com.purple.security.AuthAspect;
import com.purple.security.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActorController extends BaseController {

	private final ActorHandlerable actorHandler;

	@Autowired
	public ActorController(ActorHandlerable actorHandler) {
		this.actorHandler = actorHandler;
	}
	@GetMapping(value = "/actorSearch/{search}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Authorized
	public ResponseEntity<?> searchActor(HttpServletRequest request, HttpServletResponse response, @PathVariable String search) {
		if (search == null) {
			return ResponseEntity.badRequest().body("Search is empty");
		}
		User user = super.getCurrentUserFromAttribute(request);

		return ResponseEntity.ok(this.actorHandler.searchActors(user,search));
	}
	@GetMapping(value = "/actor/all",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Authorized(requiredRole = "Admin")
	public ResponseEntity<?> getAllActors(HttpServletRequest request, HttpServletResponse response) {
		User user = super.getCurrentUserFromAttribute(request);
		return ResponseEntity.ok(this.actorHandler.searchActors(user,"%"));
	}
	@GetMapping(value = "/actor/detail/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Authorized
	public ResponseEntity<?> getActorDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) {
		User user = super.getCurrentUserFromAttribute(request);
		return ResponseEntity.ok(this.actorHandler.getActorById(user, id));
	}
	@PutMapping(value = "/actor/detail",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Authorized(requiredRole = "Admin")
	public ResponseEntity<?> updateActor(HttpServletRequest request, HttpServletResponse response, @RequestBody Actor actor) {
		User user = super.getCurrentUserFromAttribute(request);
		return ResponseEntity.ok(this.actorHandler.saveActor(user,actor));
	}
}
