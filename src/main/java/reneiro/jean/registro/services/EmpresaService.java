package reneiro.jean.registro.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import reneiro.jean.registro.entities.Empresa;

@Service
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
