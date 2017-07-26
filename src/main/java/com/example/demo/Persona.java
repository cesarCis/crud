package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Persona {

	@Id
	private String idEmpleado;
	private String nombre;
	private String apPaterno;
	private String apMaterno;
	private int edad;
	private String sexo;

	public Persona(){}

	public Persona(String nombre, String apPaterno, String apMaterno, int edad, String sexo) {
		super();
		this.nombre = nombre;
		this.apPaterno = apPaterno;
		this.apMaterno = apMaterno;
		this.edad = edad;
		this.sexo = sexo;
	}

	

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPaterno() {
		return apPaterno;
	}

	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	public String getApMaterno() {
		return apMaterno;
	}

	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	} 

}
