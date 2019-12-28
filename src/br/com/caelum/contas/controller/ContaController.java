package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/form")
	public String formulario() {
		return "formulario";
	}
	
	/**
	 * O springMVC tenta atribuir o nome do input da view para a propriedade o objeto no parametro
	 * Faz o mapeamento automatico entre o nome que esta no HTML e o nome que esta na classe conta.
	 * O nome do componente HTML é o nome que sera rocurado no atributo da classe, se os nome baterem o binde sera automatico
	 * @param conta
	 * @return
	 */
	@RequestMapping("/adicionaConta")
	public String adicionaConta(Conta conta) {
		System.out.println("A conta adicionada é: "+conta.getDescricao());
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.adiciona(conta);
		
		return "contaAdicionada";
	}
}
