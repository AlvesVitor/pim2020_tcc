package br.com.pim.projetoPim.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pim.projetoPim.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class TokenService {
	
	@Value("${projetoPim.jwt.expiration}")
	private String expiration;
	
	@Value("${projetoPim.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) throws Exception {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Usuario usuario = logado;
		if(usuario.getInativo() == false) {
			
			Date hoje = new Date();
			
			
			return Jwts.builder()
					.setIssuer("API pim site")
					.setSubject(logado.getId().toString())
					.setIssuedAt(hoje)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
			
		}else {
			throw new Exception();
		}
		
	}
	
	public String usuariologado(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		return(logado.getNome());
	}
	public Long logadoid(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		return(logado.getId());
	}
	public String logadoEmail(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		return(logado.getEmail());
	}
	
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
