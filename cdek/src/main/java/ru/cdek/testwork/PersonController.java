package ru.cdek.testwork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.cdek.testwork.constants.HttpConstants;
import ru.cdek.testwork.exceptions.ServiceException;
import ru.cdek.testwork.model.Person;
import ru.cdek.testwork.services.PersonService;

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public ModelAndView get(@ModelAttribute("SpringWeb") Person person, ModelMap model) {
		
		List<Person> persons;
		try {
			persons = personService.findByName(person.getName());
		} catch (ServiceException e) {
			return new ModelAndView(HttpConstants.PAGE_ERROR);
		}
		
		ModelAndView mav = new ModelAndView(HttpConstants.PAGE_HOME, "command", new Person());
		mav.addObject("persons", persons);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/personAdd", method = RequestMethod.POST)
	public ModelAndView personAdd(@ModelAttribute("SpringWeb") Person person, ModelMap model) {
		try {
			personService.add(person);
		} catch (ServiceException e) {
			return new ModelAndView(HttpConstants.PAGE_ERROR);
		}
		
		return get(new Person(), model);
	}
	
	
	
	
}
