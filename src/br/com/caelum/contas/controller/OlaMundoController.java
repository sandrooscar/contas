package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

	@RequestMapping("/olaMundo")
	public String olaMundo() {
		System.out.println("Ol� springMVC spring4");
		return "olaMundo";
	}
}
