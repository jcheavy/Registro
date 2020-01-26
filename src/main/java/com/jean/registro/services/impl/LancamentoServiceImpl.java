package com.jean.registro.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jean.registro.entities.Lancamento;
import com.jean.registro.repositories.LancamentoRepository;
import com.jean.registro.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private  LancamentoRepository lancamentoRepository;
	
	
	@Override
	public Page<Lancamento> bucarFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
	    log.info("Buscando Lançamento para o funcionário Id {}", funcionarioId);
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Override
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando Lançamento para o funcionário Id {}", id);
		return lancamentoRepository.findById(id);
	}

	@Override
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo lançamento {}", lancamento );
		return null;
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo funcionario peleo id {}", id );
		this.lancamentoRepository.deleteById(id);
	}

}
