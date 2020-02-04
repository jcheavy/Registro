package com.jean.registro.dtos;

import java.util.Optional;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class FuncionarioDto {
	
	
	private Long id;
	private String nome;
	private String email;
	private Optional<String> senha = Optional.empty();
	private Optional<String> valorHora = Optional.empty();
	private Optional<String> qtdHorasTrabalhoDia = Optional.empty();
	private Optional<String> qtdHoraAlmoco = Optional.empty();
	
	public FuncionarioDto() {
		
	}

	public FuncionarioDto(Long id, String nome, String email, Optional<String> senha, Optional<String> valorHora,
			Optional<String> qtdHorasTrabalhoDia, Optional<String> qtdHoraAlmoco) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.valorHora = valorHora;
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
		this.qtdHoraAlmoco = qtdHoraAlmoco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
   
	@SuppressWarnings("deprecation")
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}

	public Optional<String> getValorHora() {
		return valorHora;
	}

	public void setValorHora(Optional<String> valorHora) {
		this.valorHora = valorHora;
	}

	public Optional<String> getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	public void setQtdHorasTrabalhoDia(Optional<String> qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	public Optional<String> getQtdHoraAlmoco() {
		return qtdHoraAlmoco;
	}

	public void setQtdHoraAlmoco(Optional<String> qtdHoraAlmoco) {
		this.qtdHoraAlmoco = qtdHoraAlmoco;
	}

	@Override
	public String toString() {
		return "FuncionarioDto [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", valorHora="
				+ valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHoraAlmoco=" + qtdHoraAlmoco + "]";
	}
	
	
}
