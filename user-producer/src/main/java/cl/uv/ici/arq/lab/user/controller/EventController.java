package cl.uv.ici.arq.lab.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.uv.ici.arq.lab.user.dto.UserDTO;
import cl.uv.ici.arq.lab.user.service.UserProducerService;

@RestController
@RequestMapping("/users")
public class EventController {

@Autowired
UserProducerService service;
	
	@PostMapping("/send")
	public ResponseEntity<Boolean> createUser(@RequestBody UserDTO user) {
		service.sendMessage(user);
		return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
	}
}
