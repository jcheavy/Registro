package com.jean.registro.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jean.registro.entities.Lancamento;

@Service
public interface LancamentoService {

	/**
	 * Retorna uma lista paginada de lançamentos de um funcionário.
	 * @param  funcionarioId
	 * @param  pageRequest
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> bucarFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	
	/**
	 * Busca Lançamendo pelo id.
	 * @param  id
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * Salva o Lançamento.
	 * @param  lancamento
	 * @return Lancamento persistir(Lancamento lancamento);
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Exclui Lançamento.
	 * @param id
	 * 
	 */
	void remover(Long id);
	
	
}
