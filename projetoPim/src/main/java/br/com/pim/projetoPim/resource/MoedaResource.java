package br.com.pim.projetoPim.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pim.projetoPim.model.Moeda;
import br.com.pim.projetoPim.service.MoedaService;

@RestController
@RequestMapping("/moedas")
public class MoedaResource {

	@Autowired
	private MoedaService moedaService;
	
	@PostMapping
	public ResponseEntity<Moeda> cadastraMoeda(@RequestBody @Valid Moeda moeda) {
		Moeda moedaSalvo = moedaService.salvarMoeda(moeda);
		return ResponseEntity.status(HttpStatus.CREATED).body(moedaSalvo);
	}
	
	@GetMapping
	public ResponseEntity<List<Moeda>> getAllMoeda() {
		List<Moeda> moeda = moedaService.recuperaMoedas();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(moeda);
	}
}
