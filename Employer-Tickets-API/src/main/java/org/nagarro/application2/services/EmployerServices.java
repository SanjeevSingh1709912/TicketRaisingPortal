package org.nagarro.application2.services;


import java.util.List;
import java.util.NoSuchElementException;

import org.nagarro.application2.constants.Constants;
import org.nagarro.application2.dao.EmployerRepository;
import org.nagarro.application2.exceptions.ServiceException;
import org.nagarro.application2.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmployerServices implements Constants {
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@CachePut(cacheNames = "employer", key = "#employer.id")
	public Employer addOrUpdate(Employer employer) {
		
		if(employer.getFirstname().isEmpty() || employer.getLastname().isEmpty() || employer.getUsername().isEmpty() || employer.getPassword().isEmpty())
			throw new ServiceException(EMPLOYER_DETAILS_BLANK_ERROR_CODE,EMPLOYER_DETAILS_BLANK);
		try {
			Employer savedEmployer =  this.employerRepository.save(employer);
			return savedEmployer;
		}catch(IllegalArgumentException e) {
			throw new ServiceException(EMPLOYER_DETAILS_NULL_ERROR_CODE ,e.getMessage());
		}catch(Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}
	
	@CacheEvict(cacheNames = "employer", key = "#id")
	public void deleteById(int id) {
		try {
		this.employerRepository.deleteById(id);
		}catch (NoSuchElementException e) {
			throw new ServiceException(ID_NOT_EXIST_ERROR_CODE,ID_NOT_EXIST);
		}catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}
	
	public List<Employer> getAll(){
		List<Employer> empList = null;
		try {
			empList = this.employerRepository.findAll();
			return empList;
		}catch (Exception e) {
			throw new ServiceException(LIST_EMPTY_ERROR_CODE, LIST_EMPTY_ERROR);
		}
	}
		
	@Cacheable(cacheNames = "employer", key = "#id")
	public Employer getById(int id) {
		try {
		Employer employer = this.employerRepository.findById(id).get();
		return employer;
		}catch (NoSuchElementException e) {
			throw new ServiceException(ID_NOT_EXIST_ERROR_CODE,ID_NOT_EXIST);
		}catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}
	
	public Employer getByUsernameAndPassword(String username, String password) {
		if(username.isEmpty() || password.isEmpty())
			throw new ServiceException(USERNAME_PASSWORD_NULL_ERROR_CODE,USERNAME_PASSWORD_NULL_ERROR);
		
		try {
			Employer employer = this.employerRepository.findByUsernameAndPassword(username, password);
			return employer;
		}catch (IllegalArgumentException e) {
			throw new ServiceException(USERNAME_PASSWORD_NULL_ERROR_CODE,USERNAME_PASSWORD_NULL_ERROR);
		}catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}
	
	public Employer getByUsername(String username) {
		if(username.isEmpty())
			throw new ServiceException(USERNAME_NULL_ERROR_CODE,USERNAME_NULL_ERROR);
		try {
			Employer employer = this.employerRepository.findByUsername(username);
			return employer;
		}catch (IllegalArgumentException e) {
			throw new ServiceException(USERNAME_NULL_ERROR_CODE,USERNAME_NULL_ERROR);
		}catch (Exception e) {
			throw new ServiceException(SERVICE_ERROR_CODE, SOMETHING_WRONG_IN_SERVICE);
		}
	}
	
		
}
