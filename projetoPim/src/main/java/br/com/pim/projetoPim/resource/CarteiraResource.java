package br.com.pim.projetoPim.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pim.projetoPim.dto.carteira.CarteiraDTO;
import br.com.pim.projetoPim.model.Carteira;
import br.com.pim.projetoPim.repository.CarteiraRepository;
import br.com.pim.projetoPim.service.CarteiraService;

@RestController
@RequestMapping("/carteira")
public class CarteiraResource {

	@Autowired
	private CarteiraService carteiraService;
	@Autowired
	private CarteiraRepository carteiraRepository;

	@PostMapping
	public ResponseEntity<Carteira> cadastrarCarteira(@RequestBody @Valid CarteiraDTO carteira) throws Exception {
		Carteira carteiraSalvo = carteiraService.salvarCarteira(carteira);
		return ResponseEntity.status(HttpStatus.CREATED).body(carteiraSalvo);
	}

	@GetMapping("/{codigoUsuario}")
	public ResponseEntity<List<CarteiraDTO>> getAllCarteira(
			@PathVariable(value = "codigoUsuario", required = true) Long codigo) throws Exception {
		List<CarteiraDTO> carteiras = carteiraService.recuperaCarteiras(codigo);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(carteiras);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Carteira> carteiraOptional = carteiraRepository.findById(id);
		if (carteiraOptional.isPresent()) {
			try {

				carteiraRepository.deleteById(id);
			} catch (Exception error) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

			return ResponseEntity.ok().build();
		} else {

			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("/{codigoCarteira}")
	@Transactional
	public ResponseEntity<Carteira> atualizaCarteira(@RequestBody CarteiraDTO carteiradto,
			@PathVariable(value = "codigoCarteira", required = true) Long codigo) throws Exception {
		Carteira carteira = carteiraService.atualizar(codigo, carteiradto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(carteira);
	}

}
