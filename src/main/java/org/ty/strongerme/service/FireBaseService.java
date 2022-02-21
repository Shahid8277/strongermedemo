package org.ty.strongerme.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.strongerme.dto.PushNotificationRequest;
import org.ty.strongerme.exception.UserExistException;
import org.ty.strongerme.repository.StrongerMeRepo;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FireBaseService {

	@Autowired
	StrongerMeRepo repo;

	@Autowired
	private FirebaseMessaging firebaseMessaging;

	public String sendNotification(PushNotificationRequest contain) throws FirebaseMessagingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message need to alert", "alert shahid");
		Notification notification = Notification
				.builder()
				.setTitle(contain.getTitle())
				.setBody(contain.getMessage())
				.build();
		Message message = Message.builder()
				.setToken(repo.findById(contain.getEmail())
				.map(x -> x.getToken())
				.orElseThrow(UserExistException::new))
				.setNotification(notification)
				.putAllData(map)
				.build();
		return firebaseMessaging.send(message);
	}
}
