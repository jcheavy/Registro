package com.jean.registro.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jean.registro.entities.Funcionario;

@Service
public interface FuncionarioService {
   
	/**
	 * Salva funcionário
	 * @param funcionario
	 * @return Funcionario persistir(Funcionario funcionario);
	 */
	Funcionario persistir(Funcionario funcionario);
	
	/**
	 * busca e retorana dados do cpf do funcionário
	 * @param cpf
	 * @return Optional<Funcionario> buscarPorCpf(String cpf);
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * busca funcionario pelo email
	 * @param email
	 * @return Optional<Funcionario> buscarPorEmail(String email);
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 *  busca funcionario peleo id
	 * @param id
	 * @return Optional<Funcionario> buscarPorId(Long id)
	 */
	Optional<Funcionario> buscarPorId(Long id);
	
}
