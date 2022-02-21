package org.ty.strongerme.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@JsonInclude(Include.NON_NULL)
public class User implements Serializable {
	@Id
	@Email
	private String email;
	@Size(min = 5 , max = 16)
	private String password;
	private double ponits;
	private String myReferenceCode;
	@JsonIgnore
	private boolean count;
	private String token;
	@Transient
	private String referenceCode;
}
