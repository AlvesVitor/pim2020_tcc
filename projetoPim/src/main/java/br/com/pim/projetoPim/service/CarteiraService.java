package br.com.pim.projetoPim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import br.com.pim.projetoPim.dto.carteira.CarteiraDTO;
import br.com.pim.projetoPim.model.Carteira;
import br.com.pim.projetoPim.model.Usuario;
import br.com.pim.projetoPim.repository.CarteiraRepository;

@Service
public class CarteiraService {

	@Autowired
	private CarteiraRepository carteiraRepository;

	public Carteira salvarCarteira(CarteiraDTO carteiradto) throws Exception {
		Carteira carteira = new Carteira();

		ModelMapper modelMapper = new ModelMapper();
		
		carteira = modelMapper.map(carteiradto, Carteira.class);
		
		Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (usuario != null) {
			carteira.setUsuarioId(usuario);
		} else {
			throw new Exception();
		}

		carteiraRepository.save(carteira);

		return carteira;
	}

	public List<CarteiraDTO> recuperaCarteiras(Long codigo) throws Exception {
		List<Carteira> carteirasOptional = carteiraRepository.findByUsuarioId_Id(codigo);

		if (carteirasOptional != null) {

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.addMappings(new PropertyMap<Carteira, CarteiraDTO>() {
				@Override
				protected void configure() {

				}
			});
			List<CarteiraDTO> carteirasdto = new ArrayList<>();
			carteirasOptional.forEach(carteira -> {

				CarteiraDTO carteiras = modelMapper.map(carteira, CarteiraDTO.class);
				carteirasdto.add(carteiras);
			});
			return carteirasdto;

		} else {
			throw new Exception();
		}

	}

	public Carteira atualizar(Long codigo, CarteiraDTO carteiradto) throws Exception {
		Carteira carteira = new Carteira();
		Optional<Carteira> carteiraOptional = carteiraRepository.findById(codigo);
		if (carteiraOptional.isPresent()) {
			
			Carteira carteiraSalvo = carteiraOptional.get();
			ModelMapper modelMapper = new ModelMapper();
			
			carteira = modelMapper.map(carteiradto, Carteira.class);
			
			Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (usuario != null) {
				carteira.setUsuarioId(usuario);
			} else {
				throw new Exception();
			}
			
			BeanUtils.copyProperties(carteira, carteiraSalvo, "codigo");
			carteiraRepository.save(carteiraSalvo);
			return carteiraSalvo;
			
		} else {
			throw new Exception();
		}
	}

}
