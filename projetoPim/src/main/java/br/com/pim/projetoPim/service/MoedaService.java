package br.com.pim.projetoPim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pim.projetoPim.model.Moeda;
import br.com.pim.projetoPim.repository.MoedaRepository;

@Service
public class MoedaService {

	@Autowired
	private MoedaRepository moedaRepository;

	public Moeda salvarMoeda(Moeda moeda) {

		moedaRepository.save(moeda);
		return moeda;
	}

	public List<Moeda> recuperaMoedas() {
		List<Moeda> moedas = moedaRepository.findAll();

		return moedas;
	}

}
