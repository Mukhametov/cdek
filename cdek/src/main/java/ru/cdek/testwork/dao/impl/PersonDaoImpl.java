package ru.cdek.testwork.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.cdek.testwork.constants.DbConstants;
import ru.cdek.testwork.dao.PersonDao;
import ru.cdek.testwork.exceptions.ServiceException;
import ru.cdek.testwork.model.Person;

@Service
public class PersonDaoImpl implements PersonDao {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);
	
	@Autowired
	private DataSource dataSource;

	
	private final String SQL_CREATE_TABLE = "CREATE TABLE " + DbConstants.TABLE_NAME_PERSON + " (id serial NOT NULL, name character varying(255) NOT NULL)";
	@PostConstruct
	private void init() throws SQLException {
		try {
			if (!isTableExist()) {
				PreparedStatement preparedStatement = getConnection()
						.prepareStatement(SQL_CREATE_TABLE);
				preparedStatement.execute();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	
	
	private Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	
	private final String SQL_IS_TABLE_EXIST = "Show tables from " + DbConstants.DATABASE_NAME + " like '" + DbConstants.TABLE_NAME_PERSON + "'";
	private boolean isTableExist() throws SQLException {
		PreparedStatement preparedStatement = getConnection()
				.prepareStatement(SQL_IS_TABLE_EXIST);
		ResultSet resultSet = preparedStatement.executeQuery();
		return resultSet.next();
	}
	
	
	private final String SQL_INSERT_PERSON = "INSERT INTO " + DbConstants.TABLE_NAME_PERSON + " (name) values (?)";
	@Override
	public Person add(Person person) throws ServiceException {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(SQL_INSERT_PERSON);
			preparedStatement.setString(1, person.getName());
			preparedStatement.execute();
			
			return person;			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException("Can't save to database", e);
		}
	}
	
	
	private final String SQL_SELECT_PERSONS = "SELECT * FROM " + DbConstants.TABLE_NAME_PERSON + " WHERE name like ?";
	@Override
	public List<Person> findByName(String name) throws ServiceException {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(SQL_SELECT_PERSONS);
			
			if (null == name) {
				name = "";
			}
			preparedStatement.setString(1, "%" + name + "%");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Person> result = new ArrayList<Person>();
			
			while (resultSet.next()) {
				Person person = new Person();
				person.setId(resultSet.getLong("id"));
				person.setName(resultSet.getString("name"));
				
				result.add(person);
			}
			
			return result;			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException("Can't get data from database", e);
		}
		
	}

}
