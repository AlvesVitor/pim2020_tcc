package br.com.pim.projetoPim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity(name = "permissao")
@Table(name = "permissao")
@Data
public class Permissao implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return descricao;
	}
}
