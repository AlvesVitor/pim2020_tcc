package br.com.pim.projetoPim.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pim.projetoPim.dto.carteiraMovimento.CarteiraMovimentoDTO;
import br.com.pim.projetoPim.model.Carteira;
import br.com.pim.projetoPim.model.CarteiraMovimento;
import br.com.pim.projetoPim.repository.CarteiraMovimentoRepository;
import br.com.pim.projetoPim.service.CarteiraMovimentoService;

@RestController
@RequestMapping("/carteira/movimento")
public class CarteiraMovimentoResource {

	@Autowired
	private CarteiraMovimentoService carteiraService;

	@Autowired
	private CarteiraMovimentoRepository carteiraRepository;

	@PostMapping
	public ResponseEntity<CarteiraMovimento> cadastraCarteiraMovimento(
			@RequestBody @Valid CarteiraMovimentoDTO carteiradto) throws Exception {
		CarteiraMovimento carteiraSalvo = carteiraService.salvarCarteira(carteiradto);
		return ResponseEntity.status(HttpStatus.CREATED).body(carteiraSalvo);
	}

	@GetMapping("/{codigoCarteira}")
	public ResponseEntity<List<CarteiraMovimentoDTO>> getAllCarteiraMovimento(
			@PathVariable(value = "codigoCarteira", required = true) Long codigo) throws Exception {
		List<CarteiraMovimentoDTO> carteiras = carteiraService.recuperaCarteiraMovimento(codigo);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(carteiras);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<CarteiraMovimento> carteiraOptional = carteiraRepository.findById(id);
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

}
