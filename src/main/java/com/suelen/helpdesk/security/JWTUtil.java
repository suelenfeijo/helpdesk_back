package com.suelen.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
/*
 * @Component para ser possível injetar
 * 
 */
public class JWTUtil {
	
	
	/*
	 * pegando o valor que está em propertie.properties, no caso, o tempo de expiração
	 */
	@Value("${jwt.expiration}")
	private Long expiration;
	
	
	/*
	 * pegando o valor que está em propertie.properties, no caso, a chave secreta
	 */
	@Value("${jwt.secret}")
	private String secret;

	/*
	 * método que gera o token, gera como String
	 * e como retorno o email, tipo string.
	 * 
	 * set Subject , as informações do token vai no  email
	 * no setExpiration, é o tempo de expiração do token = data de expiração deve ser a data atual + o tempo que está no properties
	 * signWith = qual o algoritmo que vamos utilizar para assinar o token , que é o hs512, e pega a chave secreta, que é a variavél que 
	 * pega o valor do properties com a anotação Value, e é um array de bytes
	 * compact() -> compacta o corpo do jwt e deixa a api mais performatica
	 */
	public String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	
	/*
	 * 
	 */
	
	/*public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}*/

	/*
	 * 
	 */
	/*private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}*/

	
	/*
	 * 
	 */
	/*public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}*/
	
}