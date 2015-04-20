package ru.cdek.testwork.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ru.cdek.testwork.dao.PersonDao;
import ru.cdek.testwork.model.Person;

@Service
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	private Map<Long, Person> persons = new HashMap<Long, Person>();
	private long nextId = 1;
	
	@Override
	public Person add(Person person) {
		person.setId(nextId++);
		
		return persons.put(person.getId(), person);
	}

	@Override
	public List<Person> findByName(String name) {
		if (null == name || "".equals(name)) {
			return new ArrayList<Person>(persons.values());
		}
		
		List<Person> result = new ArrayList<Person>();
		
		for (Person person: persons.values()) {
			if (person.getName().equals(name)) {
				result.add(person);
			}
		}
		
		return result;
	}

}
