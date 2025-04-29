package br.ueg.progweb1.empresa.mappers;

import br.ueg.progweb1.empresa.model.Empresa;
import br.ueg.progweb1.empresa.model.dtos.EmpresaDTO;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    public Empresa toModel(EmpresaDTO empresaDTO){
        Empresa empresa = new Empresa();
        empresa.setCep(empresaDTO.getCep());
        empresa.setLogradouro(empresaDTO.getLogradouro());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setCNPJ(empresaDTO.getCNPJ());
        empresa.setDataFundacao(empresaDTO.getDataFundacao());
        empresa.setStatus(empresaDTO.getStatus());
        return empresa;
    }

}
