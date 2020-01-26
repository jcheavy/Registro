package com.jean.registro.services;

import java.util.Optional;

import com.jean.registro.entities.Empresa;

public interface EmpresaService {
     
	/**
	 *  busca empresa pelo cnpj
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Salva empresa
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);
	
}
