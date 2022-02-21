package org.ty.strongerme.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PushNotificationRequest {
	private String email;
	@Size(min = 5 , max = 15)
	private String title;
	@NotNull
	private String message;
	private String topic;
}
