package org.ty.strongerme.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ty.strongerme.dto.User;
import org.ty.strongerme.exception.ReferenceNotFound;
import org.ty.strongerme.exception.UserExistException;
import org.ty.strongerme.repository.StrongerMeRepo;

@Service
public class StrongerMeService {

	@Autowired
	StrongerMeRepo repo;

	public User registerUser(User user) {
		if (repo.findById(user.getEmail()).isPresent())
			throw new UserExistException("User Already Exist");
		String referenceCode = user.getReferenceCode();
		if (referenceCode != null) {
			User frndUser = repo.findByMyReferenceCode(referenceCode);
			if (frndUser != null) {
				if (repo.countByCount(true) < 50) {
					user.setPonits(user.getPonits() + 5);
					frndUser.setPonits(frndUser.getPonits() + 5);
				} else
					throw new ReferenceNotFound("Reference are not acceptable now");
			} else
				throw new ReferenceNotFound("Reference is not valid");
		}
		return repo.save(user);
	}

	public String referenceCode(String email) {
		// validation is already done
		User user = repo.findById(email).get();
		String generatedString = user.getMyReferenceCode();
		if (generatedString == null) {
			generatedString = new Random().ints(48, 122 + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
					.limit(8).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
					.toString();
			user.setMyReferenceCode(generatedString);
			repo.save(user);
		}
		return generatedString;
	}

}
