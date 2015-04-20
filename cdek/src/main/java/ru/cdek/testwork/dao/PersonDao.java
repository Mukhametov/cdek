package ru.cdek.testwork.dao;

import java.util.List;

import ru.cdek.testwork.exceptions.ServiceException;
import ru.cdek.testwork.model.Person;

public interface PersonDao {
	
	public Person add(Person person) throws ServiceException;

	public List<Person> findByName(String name) throws ServiceException;
	
}
