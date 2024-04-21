package br.ueg.progweb1.empresa.service.imp;

import br.ueg.progweb1.empresa.exceptions.BusinessLogicError;
import br.ueg.progweb1.empresa.exceptions.BusinessLogicException;
import br.ueg.progweb1.empresa.exceptions.DataException;
import br.ueg.progweb1.empresa.exceptions.MandatoryException;
import br.ueg.progweb1.empresa.model.Empresa;
import br.ueg.progweb1.empresa.repository.EmpresaRepository;
import br.ueg.progweb1.empresa.service.EmpresaService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpresaServiceImp implements EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public List<Empresa> listAll() {
        return repository.findAll();
    }

    @Override
    public Empresa incluir(Empresa empresa) {
        prepararEmpresaParaIncluir(empresa);
        validarDuplicidade(empresa);
        validateMandatoryFields(empresa);
        return repository.save(empresa);
    }

    private void validarDuplicidade(Empresa empresa) {
        Optional<Empresa> empresaADD = null;
        empresaADD = repository.findEmpresaByCNPJ(empresa.getCNPJ());
        if (empresaADD.isPresent()) {
            throw new BusinessLogicException(BusinessLogicError.REGISTRO_DUPLICADO);
        }

    }

    private void prepararEmpresaParaIncluir(Empresa empresa) {
        empresa.setId(null);
    }

    @Override
    public Empresa alterar(Empresa empresa) {
        var dadoBusca = validateIdSaleExists(empresa.getId());
        setDadosEmpresaConsultada(dadoBusca, empresa);
        validateMandatoryFields(dadoBusca);
        validarDuplicidade(dadoBusca);
        return repository.save(dadoBusca);
    }

    private void setDadosEmpresaConsultada(Empresa dadoBusca, Empresa empresa) {
        dadoBusca.setCep(empresa.getCep());
        dadoBusca.setLogradouro(empresa.getLogradouro());
        dadoBusca.setNomeFantasia(empresa.getNomeFantasia());
        dadoBusca.setRural(empresa.getRural());
    }

    private Empresa validarRegistroExistente(Long idEmpresa) {
        Empresa empresaConsultada = null;
        boolean existe = false;

        if (Objects.nonNull(idEmpresa)) {
            empresaConsultada = repository.getReferenceById(idEmpresa);

            if (empresaConsultada == null) {
                existe = false;

            } else {
                existe = true;
            }
        }

        if (Boolean.FALSE.equals(existe)) {
            throw new DataException("Empresa não existe");
        }
        return empresaConsultada;
    }


    @Override
    public void excluirEmpresaPorID(Long id) {
        Empresa empresaParaRemover = this.validarRegistroExistente(id);
        this.repository.delete(empresaParaRemover);
    }

    private Optional<Empresa> consultaPorNomeFantasia(Empresa empresa) {
        Optional<Empresa> empresaConsultada = null;
        if (empresa.getNomeFantasia().isEmpty()) {
            throw new BusinessLogicException(BusinessLogicError.CAMPO_VAZIO);
        } else {
            empresaConsultada = repository.findEmpresaByNomeFantasia(empresa.getNomeFantasia());
            if (empresaConsultada.isPresent()) {
                return empresaConsultada;
            }
        }
        return empresaConsultada;
    }

    public Empresa validateIdSaleExists(Long id) {
        Optional<Empresa> empresa = repository.findById(id);
        if (empresa.isPresent()) {
            return empresa.get();
        } else {
            throw new BusinessLogicException(BusinessLogicError.ERROR_ID);
        }
    }


    private void validateMandatoryFields(Empresa empresa) {
        if (Strings.isEmpty(empresa.getCNPJ()) || Strings.isEmpty(empresa.getNomeFantasia())) {
            throw new MandatoryException("Campos Obrigatórios não preenchidos");
        }

    }
}
