package br.ueg.progweb1.empresa.repository;

import br.ueg.progweb1.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findEmpresaByNomeFantasia(String nomeFantasia);

    Optional<Empresa> findEmpresaByCNPJ(String CNPJ);
}

