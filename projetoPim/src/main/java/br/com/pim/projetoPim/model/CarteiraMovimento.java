package br.com.pim.projetoPim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "carteira_movimento")
@Table(name = "carteira_movimento")
@Data
public class CarteiraMovimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "carteira_id")
	private Carteira carteiraId;
	@ManyToOne
	@JoinColumn(name = "moeda_id")
	private Moeda moedaId;
	private float quantidade;
	private float preco;
	private float valorTotal;
	private float lucro;

	public CarteiraMovimento() {

	}

}
