package com.example.demo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonaControlador {

	@Autowired
	private RepositoryPersona repository;

	@Autowired
	private Persona person;


	@RequestMapping(value = "/save/{idEmpleado}/{nombre}", method = RequestMethod.GET)
	public String createPerson(@PathVariable int idEmpleado,@PathVariable String nombre){
		person.setIdEmpleado(idEmpleado);
		person.setNombre(nombre);
		person.setApPaterno("");
		person.setApMaterno("");
		person.setEdad(0);
		person.setSexo("");
		repository.save(person);

		return "Se agrego una persona exitosamente";
	}


	@RequestMapping(value = "/save/persona",method = RequestMethod.POST)
	public Map<String, Object> createPerson(@RequestBody Map<String, Object> personMap){

		person.setNombre(personMap.get("nombre").toString());
		person.setApPaterno(personMap.get("apPaterno").toString());
		person.setApMaterno(personMap.get("apMaterno").toString());
		person.setEdad(Integer.parseInt(personMap.get("edad").toString()));
		person.setSexo(personMap.get("sexo").toString());
		repository.save(person);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Person created successfully");
		response.put("persona", person);
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value="/one/{personId}")
	public Persona getPersonDetails(@PathVariable("personId") String personId){
		return repository.findOne(personId);
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{personId}")
	public Map<String, Object> editPerson(@PathVariable("personId") String personId,
			@RequestBody Map<String, Object> personMap){

		person.setIdEmpleado(Integer.parseInt(personMap.get("idEmpleado").toString()));
		person.setNombre(personMap.get("nombre").toString());
		person.setApPaterno(personMap.get("apPaterno").toString());
		person.setApMaterno(personMap.get("apMaterno").toString());
		person.setEdad(Integer.parseInt(personMap.get("edad").toString()));
		person.setSexo(personMap.get("sexo").toString());
		repository.save(person);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Person Updated successfully");
		response.put("person", person);
		return response;
	}


	@RequestMapping(method = RequestMethod.DELETE, value="/{personId}")
	public Map<String, String> deletePerson(@PathVariable("personId") int personId){
		repository.delete(personId+"");
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Person deleted successfully");

		return response;
	}

	@RequestMapping(method = RequestMethod.GET ,value="/getpersonas")
	public Map<String, Object> getAllPersons(){
		List<Persona> persons = repository.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("totalPersons", persons.size());
		response.put("persons", persons);
		return response;
	}

}
