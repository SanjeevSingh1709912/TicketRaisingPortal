package org.nagarro.application2.dao;

import org.nagarro.application2.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
	
	public Employer findByUsernameAndPassword(String username, String password); 
	public Employer findByUsername(String username);
		
}
