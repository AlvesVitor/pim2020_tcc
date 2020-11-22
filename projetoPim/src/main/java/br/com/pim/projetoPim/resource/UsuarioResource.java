package br.com.pim.projetoPim.resource;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pim.projetoPim.dto.usuario.UsuarioDTO;
import br.com.pim.projetoPim.model.Usuario;
import br.com.pim.projetoPim.repository.UsuarioRepository;
import br.com.pim.projetoPim.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<Usuario> cadastraUsuario(@RequestBody @Valid Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
		List<UsuarioDTO> usuariosdto = usuarioService.recuperaTodosUsuarios();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuariosdto);
	}

	@PutMapping("/{codigoUsuario}")
	@Transactional
	public ResponseEntity<Usuario> atualizaUsuario(@RequestBody Usuario usuario,
			@PathVariable(value = "codigoUsuario", required = true) Long codigo) throws Exception {
		usuario = usuarioService.atualizar(codigo, usuario);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
	}

	@GetMapping("/{codigoUsuario}")
	public ResponseEntity<Usuario> recuperaUsuario(@PathVariable(value = "codigoUsuario", required = true) Long codigo)
			throws Exception {
		Usuario usuario = usuarioService.recuperaUsuario(codigo);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuario);
	}

	@DeleteMapping("/{id}")
	@Transactional
	@PreAuthorize("hasAuthority('adm')")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if (usuarioOptional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
