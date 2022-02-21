package org.ty.strongerme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ty.strongerme.dto.User;

@Repository
public interface StrongerMeRepo extends JpaRepository<User, String>{

	User findByMyReferenceCode(String referenceCode);

	int countByCount(boolean b);
	
}
