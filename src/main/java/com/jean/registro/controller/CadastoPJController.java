package com.jean.registro.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jean.registro.dtos.CadastroPJDTO;
import com.jean.registro.entities.Empresa;
import com.jean.registro.entities.Funcionario;
import com.jean.registro.enums.PerfilEnum;
import com.jean.registro.response.Response;
import com.jean.registro.services.EmpresaService;
import com.jean.registro.services.FuncionarioService;

@Controller
@RequestMapping("/cadastrarpj")
@CrossOrigin(origins = "*")
public class CadastoPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastoPJController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	public CadastoPJController() {
		
	}
	
	/**
	 * Cadastra uma pessoa jurídica no sistema.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return ResponseEntity<Response<CadastroPJDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(method = RequestMethod.POST)
	 public ResponseEntity<Response<CadastroPJDTO>> cadastrar (@Valid @RequestBody CadastroPJDTO cadastroPJDto, BindingResult result) throws NoSuchAlgorithmException{
		 
		 log.info("Cadastrando Pessoa Juridica {}", cadastroPJDto.toString());
		 Response<CadastroPJDTO> response = new Response<CadastroPJDTO>();
		 
		 validarDadosExitentes(cadastroPJDto, result);
		 Empresa empresa = this.converterDtoParaEmpresa(cadastroPJDto);
		 Funcionario funcionario = this.converterDtoParaFuncionario(cadastroPJDto, result);
		
		 if (result.hasErrors()) {
				log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
				result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}

			this.empresaService.persistir(empresa);
			funcionario.setEmpresa(empresa);
			this.funcionarioService.persistir(funcionario);

			response.setData(this.converterCadastroPJDto(funcionario));
			return ResponseEntity.ok(response);		 		 
		 
	 }
	
	 private void validarDadosExitentes(CadastroPJDTO cadastroPJDto, BindingResult result) {
		 
		 this.empresaService.buscarPorCnpj(cadastroPJDto.getCnpj()).ifPresent(emp ->  result.addError(new ObjectError("empresa", "Empresa já existente.")));
		 
		 this.funcionarioService.buscarPorCpf(cadastroPJDto.getCpf()).ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente.")));
		
		 this.funcionarioService.buscarPorEmail(cadastroPJDto.getEmail()).ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente.")));
	 }
	 
	     /**
		 * Converte os dados do DTO para empresa.
		 * 
		 * @param cadastroPJDto
		 * @return Empresa
		 */
		private Empresa converterDtoParaEmpresa(CadastroPJDTO cadastroPJDto) {
			Empresa empresa = new Empresa();
			empresa.setCnpj(cadastroPJDto.getCnpj());
			empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());

			return empresa;
		}
		
		/**
		 * Converte os dados do DTO para funcionário.
		 * 
		 * @param cadastroPJDto
		 * @param result
		 * @return Funcionario
		 * @throws NoSuchAlgorithmException
		 */
		private Funcionario converterDtoParaFuncionario(CadastroPJDTO cadastroPJDto, BindingResult result)
				throws NoSuchAlgorithmException {
			Funcionario funcionario = new Funcionario();
			funcionario.setNome(cadastroPJDto.getNome());
			funcionario.setEmail(cadastroPJDto.getEmail());
			funcionario.setCpf(cadastroPJDto.getCpf());
			funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
			funcionario.setSenha(cadastroPJDto.getSenha());

			return funcionario;
		}
		
		/**
		 * Popula o DTO de cadastro com os dados do funcionário e empresa.
		 * 
		 * @param funcionario
		 * @return CadastroPJDto
		 */
		private CadastroPJDTO converterCadastroPJDto(Funcionario funcionario) {
			CadastroPJDTO cadastroPJDto = new CadastroPJDTO();
			cadastroPJDto.setId(funcionario.getId());
			cadastroPJDto.setNome(funcionario.getNome());
			cadastroPJDto.setEmail(funcionario.getEmail());
			cadastroPJDto.setCpf(funcionario.getCpf());
			cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
			cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());

			return cadastroPJDto;
		}
		
}
