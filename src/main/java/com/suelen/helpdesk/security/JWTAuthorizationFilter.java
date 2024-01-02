package com.suelen.helpdesk.security;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/*
 * BasicAuthenticationFilter -> é obrigatório criar ao menos um construtor , já que extendemos
 *  o BasicAuthenticationFilter.
 *  e nesse construtor é passado o AuthenticationManager
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


	private JWTUtil jwtUtil;
	private UserDetailsService userDetailsService;


	/*
	 * construtor
	 */
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	
	/*
	 * métodos sobrescritos do BasicAuthenticationFilter
	 * HttpServletRequest -> conseguimos pegar requisições que vem no cabeçalho desse request
	 * para não passar a chave e o valor do token em params, e sim no header 
	 * String header = request.getHeader("Authorization"); , pegando o valor de Authorization , que é o token
	 * if(header != null && header.startsWith("Bearer ")) -> se é diferente de nulo, e se inicia com Bearer + espaço
	 * o token inicia com Bearer (6) + espaço (7) 
	 * depois que passar na validação instancia um UsernamePasswordAuthenticationToken
	 * recebe um getAuthentication, que passamos o header removendo o Bearer+ + espaço (somando 7 character) com o 
	 * header.substring(7), então o token é pego a partir do 7 valor, que é onde se inicia o token
	 * 
	 * if(authToken != null) = SecurityContextHolder.getContext() pega o contexto em que estamos na aplicação
	 * e seta o Authentication com setAuthentication passando o authToken (token)
	 * 
	 * chain.doFilter(request, response); passa o request e o response

	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
			if(authToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		chain.doFilter(request, response);
	}

	
	/*
	 * se o token é válido (com método lá do jwtUtil) 
	 * String username = jwtUtil.getUsername, que esse método recebe também o token
	 * UserDetails details = userDetailsService.loadUserByUsername(username); que carrega o usuário pelo o username(email)
	 * retorna o principal (email), senha passada como null, credenciais, mas não estamos validando acesso por credenciais e sim por authorities
	 * se o token não é valido , retorne null
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.tokenValido(token)) {  
			String username = jwtUtil.getUsername(token);
			UserDetails details = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(details.getUsername(), null, details.getAuthorities());
		}
		return null;
	}

}