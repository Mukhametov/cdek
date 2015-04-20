package ru.cdek.testwork.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cdek.testwork.dao.PersonDao;
import ru.cdek.testwork.exceptions.ServiceException;
import ru.cdek.testwork.model.Person;
import ru.cdek.testwork.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public Person add(Person person) throws ServiceException {
		return personDao.add(person);
	}

	@Override
	public List<Person> findByName(String name) throws ServiceException {
		return personDao.findByName(name);
	}

}
