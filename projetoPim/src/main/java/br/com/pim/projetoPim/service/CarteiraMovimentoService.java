package br.com.pim.projetoPim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pim.projetoPim.dto.carteiraMovimento.CarteiraMovimentoDTO;
import br.com.pim.projetoPim.model.Carteira;
import br.com.pim.projetoPim.model.CarteiraMovimento;
import br.com.pim.projetoPim.repository.CarteiraMovimentoRepository;
import br.com.pim.projetoPim.repository.CarteiraRepository;

@Service
public class CarteiraMovimentoService {

	@Autowired
	private CarteiraMovimentoRepository carteiraMovimentoRepository;

	@Autowired
	private CarteiraRepository carteiraRepository;

	public CarteiraMovimento salvarCarteira(CarteiraMovimentoDTO carteiradto) throws Exception {
		CarteiraMovimento carteira = new CarteiraMovimento();
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<CarteiraMovimentoDTO, CarteiraMovimento>() {
			@Override
			protected void configure() {

			}
		});

		carteira = modelMapper.map(carteiradto, CarteiraMovimento.class);
		Optional<Carteira> carteiraCodigo = carteiraRepository.findById(carteiradto.getCarteiraId());
		if (carteiraCodigo.isPresent()) {
			carteira.setCarteiraId(carteiraCodigo.get());
		} else {
			throw new Exception();
		}

		carteiraMovimentoRepository.save(carteira);

		return carteira;
	}

	public List<CarteiraMovimentoDTO> recuperaCarteiraMovimento(Long codigo) throws Exception {
		List<CarteiraMovimento> carteirasOptional = carteiraMovimentoRepository.findByCarteiraId_Id(codigo);

		if (carteirasOptional != null) {

			ModelMapper modelMapper = new ModelMapper();
			modelMapper.addMappings(new PropertyMap<CarteiraMovimento, CarteiraMovimentoDTO>() {
				@Override
				protected void configure() {

				}
			});
			List<CarteiraMovimentoDTO> carteirasdto = new ArrayList<>();
			carteirasOptional.forEach(carteira -> {

				CarteiraMovimentoDTO carteiras = modelMapper.map(carteira, CarteiraMovimentoDTO.class);
				carteirasdto.add(carteiras);
			});
			return carteirasdto;

		} else {
			throw new Exception();
		}

	}

	public List<CarteiraMovimento> recuperaCarteiras() {
		List<CarteiraMovimento> carteiras = carteiraMovimentoRepository.findAll();

		return carteiras;
	}

}
