package br.com.pim.projetoPim.config.dto;

import br.com.pim.projetoPim.model.Usuario;
import lombok.Data;

@Data
public class TokenDTO {

	private Long id;
	private String token;
	private String tipo;
	private String nome;
	private String email;

	public TokenDTO(Long id, String token, String tipo, String nome, String email) {
		this.token = token;
		this.tipo = tipo;
		this.nome = nome;
		this.email = email;
		this.id = id;
	}

	public static TokenDTO dto(Usuario usuario, String tipo, String token) {
		return new TokenDTO(usuario.getId(),usuario.getEmail(), usuario.getNome(), tipo, token);
	}
}
