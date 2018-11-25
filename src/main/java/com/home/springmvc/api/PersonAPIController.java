package com.home.springmvc.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.home.springmvc.model.Person;
import com.home.springmvc.service.PersonService;

@Controller
public class PersonAPIController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = PersonAPIConstant.GET_PERSON_BY_ID, method = RequestMethod.GET)
	public ResponseEntity<Person> getPersonById(@PathVariable("id") int id) {
		logger.info("Start getEmployee. ID=" + id);
		Person person = personService.getPersonById(id);
		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	@RequestMapping(value = PersonAPIConstant.GET_ALL_PERSON, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Person>> listPersons() {
		logger.info("Start getAllPerson.");
		List<Person> persons = personService.listPersons();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}

	@RequestMapping(value = PersonAPIConstant.ADD_PERSON, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addPerson(@RequestBody Person person, UriComponentsBuilder ucBuilder) {

		logger.info("Start createPerson.");

		personService.addPerson(person);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = PersonAPIConstant.DELETE_PERSON, method = RequestMethod.PUT)
	public ResponseEntity<Person> deleteEmployee(@PathVariable("id") int id) {
		logger.info("Start deletePerson.");

		personService.removePerson(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

	private static final Logger logger = LoggerFactory.getLogger(PersonAPIController.class);
}
