package br.com.pim.projetoPim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pim.projetoPim.model.CarteiraMovimento;

public interface CarteiraMovimentoRepository  extends JpaRepository<CarteiraMovimento, Long>{
	
	List<CarteiraMovimento> findByCarteiraId_Id(Long codigo);
}
