package com.example.demo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryPersona extends MongoRepository<Persona, String> {

	public Persona nombre(String nombre);
	public List<Persona> apPaterno(String apPaterno);

}
