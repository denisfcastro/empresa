package br.ueg.progweb1.empresa.service;

import br.ueg.progweb1.empresa.model.Empresa;

import java.util.List;

public interface EmpresaService {

    List<Empresa> listAll();

    Empresa incluir(Empresa empresa);

    Empresa alterar(Empresa empresa);

    void excluirEmpresaPorID(Long id);
    Empresa validateIdSaleExists(Long id);

}
