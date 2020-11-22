package br.com.pim.projetoPim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.pim.projetoPim.dto.usuario.UsuarioDTO;
import br.com.pim.projetoPim.model.Usuario;
import br.com.pim.projetoPim.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvarUsuario(Usuario usuario) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senha = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senha);

		usuarioRepository.save(usuario);
		return usuario;
	}

	public Usuario atualizar(Long codigo, Usuario usuario) throws Exception {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigo);

		if (usuarioOptional.isPresent()) {
			Usuario usuarioSalvo = usuarioOptional.get();

			if (!usuario.getSenha().equals(usuarioSalvo.getSenha())) {

				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String senhaAtual = passwordEncoder.encode(usuario.getSenha());
				usuario.setSenha(senhaAtual);
			}

			BeanUtils.copyProperties(usuario, usuarioSalvo, "codigo");
			return usuarioSalvo;

		} else {
			throw new Exception();
		}
	}

	public Usuario recuperaUsuario(Long codigo) throws Exception {
		Usuario usuario = new Usuario();
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigo);

		if (usuarioOptional.isPresent()) {
			usuario = usuarioOptional.get();
		} else {
			throw new Exception();
		}
		return usuario;
	}

	public List<UsuarioDTO> recuperaTodosUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosdto = new ArrayList<>();

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<Usuario, UsuarioDTO>() {
			@Override
			protected void configure() {

			}
		});

		for (Usuario usuario : usuarios) {
			UsuarioDTO usuariodto = modelMapper.map(usuario, UsuarioDTO.class);
			usuariosdto.add(usuariodto);
		}

		return usuariosdto;
	}

}
