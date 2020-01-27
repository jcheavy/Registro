package com.jean.registro.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jean.registro.dtos.EmpresaDto;
import com.jean.registro.entities.Empresa;
import com.jean.registro.response.Response;
import com.jean.registro.services.EmpresaService;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaServive;
	
	/**
	 * Retorna uma empresa por CNPJ.
	 * @param cnpj
	 * @return ResponseEntity<Response<EmpresaDto>> buscarPorCnpj
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/cnpj/{cnpj}")
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj){
		log.info("Buscando empresa por cnpj {}",cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = empresaServive.buscarPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			log.info("Empresa não encontrada para o  cnpj {}",cnpj);
			response.getErrors().add("Empresa não encontrada para o  CNPJ ! "+cnpj);
			return ResponseEntity.badRequest().body(response);			
		}
        response.setData(this.converterEmpresaDto(empresa.get()));
		return ResponseEntity.ok(response);
		
	}
	
	public EmpresaController() {
		
	}
    /**
     *  Transforma um DTO em ema empresa.
     * @param empresa
     * @return EmpresaDto converterEmpresaDto(Empresa empresa)
     */
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		return empresaDto;
		
	}
}
