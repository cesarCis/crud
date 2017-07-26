package com.example.demo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/person")
public class PersonaControlador {

	public static final Logger LOGGER = LoggerFactory.getLogger(PersonaControlador.class);

	@Autowired
	private RepositoryPersona repository;

	@Autowired
	private Persona person;


	@PostMapping(value = "/save/persona")
	public Map<String, Object> createPerson(@RequestBody Map<String, Object> personMap){

		person.setIdEmpleado(personMap.get("idEmpleado").toString());
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

	@GetMapping(value="/one/{nameEmpleado}")
	public Map<String, Object> getPersonDetails(@PathVariable("nameEmpleado") String nameEmpleado){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		Persona pp=repository.nombre(nameEmpleado);

		if(pp!=null){
			response.put("message", "This is the Person");
			response.put("person", pp);
			return response;		
		}

		response.put("message", "No match any person with these name");
		return response;
	}

	@PutMapping(value="/update")
	public Map<String, Object> editPerson(@RequestBody Map<String, Object> personMap){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		System.out.println(personMap.get("idEmpleado").toString()+"<---------");
		Persona findOne = repository.findOne(personMap.get("idEmpleado").toString());

		if (findOne!=null) {
			findOne.setNombre(personMap.get("nombre").toString());
			findOne.setApPaterno(personMap.get("apPaterno").toString());
			findOne.setApMaterno(personMap.get("apMaterno").toString());
			findOne.setEdad(Integer.parseInt(personMap.get("edad").toString()));
			findOne.setSexo(personMap.get("sexo").toString());
			repository.save(findOne);
			response.put("message", "Person Updated successfully");
			response.put("person", findOne);
		}

		response.put("message", "Person NO-Updated");
		return response;
	}


	@DeleteMapping(value="/delete")
	public Map<String, Object> deletePerson(@RequestBody String nameEmpleado){
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		Persona pp=repository.nombre(nameEmpleado);

		if(pp!=null){
			repository.delete(pp.getIdEmpleado());
			response.put("message", "Person deleted successfully "+pp.getIdEmpleado());
			response.put("person", pp);
			return response;		
		}

		response.put("message", "No match any person with these name");
		return response;
	}

	@GetMapping(value="/getpersonas")
	public Map<String, Object> getAllPersons(){
		List<Persona> persons = repository.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("totalPersons", persons.size());
		response.put("persons", persons);
		return response;
	}

}
