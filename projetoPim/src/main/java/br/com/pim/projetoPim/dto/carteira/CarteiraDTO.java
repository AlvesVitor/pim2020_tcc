package br.com.pim.projetoPim.dto.carteira;

import br.com.pim.projetoPim.model.Carteira;
import lombok.Data;

@Data
public class CarteiraDTO {

	private Long id;
	private String nome;
	private Long usuarioId;

	public CarteiraDTO() {
		
	}
	public CarteiraDTO(Carteira carteira) {
		this.id = carteira.getId();
		this.nome = carteira.getNome();
		this.usuarioId = carteira.getUsuarioId().getId();

	}


}
