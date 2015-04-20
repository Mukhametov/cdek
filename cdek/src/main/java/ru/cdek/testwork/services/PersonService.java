package ru.cdek.testwork.services;

import java.util.List;

import ru.cdek.testwork.exceptions.ServiceException;
import ru.cdek.testwork.model.Person;

public interface PersonService {
	
	public Person add(Person person) throws ServiceException;
	
	public List<Person> findByName(String name) throws ServiceException;
	
}
