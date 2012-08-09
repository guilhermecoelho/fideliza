package br.com.fideliza.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="testeController")
@RequestScoped
public class TesteController {

	public String teste() {
		return "teste";
	}

}
