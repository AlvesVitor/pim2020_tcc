package br.com.pim.projetoPim.dto.carteiraMovimento;

import br.com.pim.projetoPim.model.CarteiraMovimento;
import lombok.Data;
@Data
public class CarteiraMovimentoDTO {

	private Long id;
	private Long carteiraId;
	private Long moedaId;
	private String moedaNome;
	private float quantidade;
	private float preco;
	private float valorTotal;
	private float lucro;

	public CarteiraMovimentoDTO(CarteiraMovimento carteiraMovimento) {
		this.id = carteiraMovimento.getId();
		this.quantidade = carteiraMovimento.getQuantidade();
		this.preco = carteiraMovimento.getPreco();
		this.valorTotal = carteiraMovimento.getValorTotal();
		this.lucro = carteiraMovimento.getLucro();
		this.moedaId = carteiraMovimento.getMoedaId().getId();
		this.moedaNome = carteiraMovimento.getMoedaId().getNome();

	}

	public CarteiraMovimentoDTO() {

	}

}
