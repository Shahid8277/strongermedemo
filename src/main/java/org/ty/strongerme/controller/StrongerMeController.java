package org.ty.strongerme.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.ty.strongerme.dto.PushNotificationRequest;
import org.ty.strongerme.dto.User;
import org.ty.strongerme.service.FireBaseService;
import org.ty.strongerme.service.StrongerMeService;

import com.google.firebase.messaging.FirebaseMessagingException;

import io.swagger.annotations.ApiOperation;

@RestController
public class StrongerMeController {

	@Autowired
	StrongerMeService service;	
	
	@Autowired
	FireBaseService baseService;
	
	@ApiOperation(value = "Registration ", response = User.class, tags = "getRegister")
	@PostMapping(path = "/registeration")
	public ResponseEntity<?> register(@Valid @RequestBody User user) {
		User details = service.registerUser(user);
		return new ResponseEntity<User>(details, HttpStatus.OK);
	}

	@ApiOperation(value = "Generating code", response = String.class, tags = "reference")
	@PostMapping(path = "/referencecode")
	public @ResponseBody ResponseEntity<?> referenceCode(@RequestBody String email) {
		return new ResponseEntity<String>(" ReferenceCode " + service.referenceCode(email), HttpStatus.OK);
	}

	@ApiOperation(value = "Push Notification", response = String.class, tags = "sendNotification")
	@PostMapping("/pushnotification")
	public ResponseEntity<?> sendNotification(@RequestBody PushNotificationRequest request)
			throws FirebaseMessagingException {
		baseService.sendNotification(request);
		return new ResponseEntity<String>("Sent Notification", HttpStatus.OK);
	}
}
