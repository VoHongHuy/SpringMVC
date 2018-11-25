package com.home.springmvc.service;

import java.util.List;

import com.home.springmvc.model.Person;

public interface PersonService {

	public void addPerson(Person p);
	public void updatePerson(Person p);
	public List<Person> listPersons();
	public Person getPersonById(long id);
	public void removePerson(long id);
	
}
