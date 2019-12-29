package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	private ContaDAO contaDAO;

	/**
	 * Spring injeta o contadao por causa do autowired
	 * @param contaDAO
	 */
	@Autowired
	public ContaController(ContaDAO contaDAO) {
		super();
		this.contaDAO = contaDAO;
	}
	
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
	public String adicionaConta(@Valid Conta conta, BindingResult result) {
		if(result.hasErrors()) {
			return "formulario";
		}
		System.out.println("A conta adicionada é: "+conta.getDescricao());
		contaDAO.adiciona(conta);
		
		return "conta/contaAdicionada";
	}
	

	/**
	 * No ModelAndView adiciono a JSP de retorno e o atributo com valor que será acessada por essa JSP.
	 * O ModelAndView é utilizado quando precisamos enviar valores para a JSP.
	 * A JSp acessa a lista de contas através do nome todasContas
	 * @return
	 */
	@RequestMapping("/listaContas")
	public ModelAndView listaContas() {
		
		List<Conta> contas = contaDAO.lista();
		
		ModelAndView mv = new ModelAndView("conta/listaConta");
		mv.addObject("todasContas", contas);
		return mv;
	}
	
	@RequestMapping("/listaContasModel")
	public String lista(Model mv) {
	  List<Conta> contas = contaDAO.lista();

	  mv.addAttribute("todasContas", contas);
	  return "conta/listaConta";
	}
	
	@RequestMapping("/removeConta")
	public String removeConta(Conta conta) {
		contaDAO.remove(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public ModelAndView mostraConta(Long id) {
		ModelAndView modelAndView = new ModelAndView("conta/mostraConta");
		modelAndView.addObject(contaDAO.buscaPorId(id));
		return modelAndView;
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
	  contaDAO.altera(conta);
	  return "redirect:listaContas";
	}	
	
	/**
	 * Informo com codigo 200 que deu certo, sem precisar devolver todo o hatml processado da lista contas
	 * Requisição via AJAX
	 * @param conta
	 * @param response
	 */
	@RequestMapping("/pagaConta")
	public void pagaConta(Conta conta, HttpServletResponse response) {
		contaDAO.paga(conta.getId());
		response.setStatus(200);
	}
}
