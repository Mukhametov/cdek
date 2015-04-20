package ru.cdek.testwork;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.cdek.testwork.model.Person;
import ru.cdek.testwork.services.PersonService;

@Controller
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	
	
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public ModelAndView get(@ModelAttribute("SpringWeb") Person person, ModelMap model) {
		List<Person> persons = personService.findByName(null);
		
		ModelAndView mav = new ModelAndView("person", "command", new Person());
		mav.addObject("persons", persons);
		return mav;
	}
	
	
	
	@RequestMapping(value = "/personAdd", method = RequestMethod.POST)
	public ModelAndView personAdd(@ModelAttribute("SpringWeb") Person person, ModelMap model) {
		personService.add(person);
		
		return get(person, model);
	}
	
	
	
	
}
