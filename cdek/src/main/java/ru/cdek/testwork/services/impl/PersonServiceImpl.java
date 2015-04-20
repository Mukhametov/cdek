package ru.cdek.testwork.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cdek.testwork.dao.PersonDao;
import ru.cdek.testwork.model.Person;
import ru.cdek.testwork.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonDao personDao;
	
	@Override
	public Person add(Person person) {
		return personDao.add(person);
	}

	@Override
	public List<Person> findByName(String name) {
		return personDao.findByName(name);
	}

}
