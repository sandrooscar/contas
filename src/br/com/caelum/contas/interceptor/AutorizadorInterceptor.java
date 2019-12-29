package br.com.caelum.contas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
	throws Exception {
		
		//ignora as urls de login e resources, ou seja, deixa acessar essas urls
		String uri = request.getRequestURI();
		if(uri.endsWith("loginForm") || uri.endsWith("efetuaLogin") || uri.contains("resources")) {
			return true;
		}
		
		//para as outras urls é verificado se o usuário já está logado, se estiver continua, caso contrário redireciona para login
		if(request.getSession().getAttribute("usuarioLogado")!=null) {
			return true;
		} else {
			response.sendRedirect("loginForm");
			return false;
		}
		
	}
}
