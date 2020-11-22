package br.com.pim.projetoPim.config.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pim.projetoPim.config.dto.LoginDTO;
import br.com.pim.projetoPim.config.dto.TokenDTO;
import br.com.pim.projetoPim.config.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginDTO dto) throws Exception {
		UsernamePasswordAuthenticationToken dadosLogin = dto.converter();

		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			String nome = tokenService.usuariologado(authentication);
			Long id = tokenService.logadoid(authentication);
			String email = tokenService.logadoEmail(authentication);
			return ResponseEntity.ok(new TokenDTO(id, token, "Bearer", nome, email));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}