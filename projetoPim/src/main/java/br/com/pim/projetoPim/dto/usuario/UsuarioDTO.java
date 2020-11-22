package br.com.pim.projetoPim.dto.usuario;

import br.com.pim.projetoPim.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private Boolean inativo;

	public UsuarioDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getSenha();
		this.inativo = usuario.getInativo();

	}

	public UsuarioDTO() {

	}

}
