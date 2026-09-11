package reneiro.jean.registro.services.imp;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reneiro.jean.registro.entities.Empresa;
import reneiro.jean.registro.repositories.EmpresaRepository;
import reneiro.jean.registro.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {
    
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(EmpresaServiceImpl.class);
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}",cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa {}",empresa);
		return this.empresaRepository.save(empresa);
	}

}