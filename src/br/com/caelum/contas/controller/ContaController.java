package br.com.caelum.contas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public String adicionaConta(@Valid Conta conta, BindingResult result) {
		if(result.hasErrors()) {
			return "formulario";
		}
		System.out.println("A conta adicionada é: "+conta.getDescricao());
		ContaDAO contaDAO = new ContaDAO();
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
		
		ContaDAO contaDAO = new ContaDAO();
		List<Conta> contas = contaDAO.lista();
		
		ModelAndView mv = new ModelAndView("conta/listaConta");
		mv.addObject("todasContas", contas);
		return mv;
	}
	
	@RequestMapping("/listaContasModel")
	public String lista(Model mv) {
	  ContaDAO dao = new ContaDAO();
	  List<Conta> contas = dao.lista();

	  mv.addAttribute("todasContas", contas);
	  return "conta/listaConta";
	}
	
	@RequestMapping("/removeConta")
	public String removeConta(Conta conta) {
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.remove(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/mostraConta")
	public ModelAndView mostraConta(Long id) {
		ContaDAO contaDAO = new ContaDAO();
		ModelAndView modelAndView = new ModelAndView("conta/mostraConta");
		modelAndView.addObject(contaDAO.buscaPorId(id));
		return modelAndView;
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
	  ContaDAO dao = new ContaDAO();
	  dao.altera(conta);
	  return "redirect:listaContas";
	}	
	
	@RequestMapping("/pagaConta")
	public String pagaConta(Conta conta) {
		ContaDAO contaDAO = new ContaDAO();
		contaDAO.paga(conta.getId());
		return "redirect:listaContas";
	}
}
