package br.com.pim.projetoPim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pim.projetoPim.model.Carteira;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {
	List<Carteira> findByUsuarioId_Id(Long codigo);

}
