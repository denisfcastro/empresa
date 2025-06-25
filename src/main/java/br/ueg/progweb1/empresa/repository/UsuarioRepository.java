package br.ueg.progweb1.empresa.repository;

import br.ueg.progweb1.empresa.model.Empresa;
import br.ueg.progweb1.empresa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

        Optional<Usuario> findByUsername(String username);
}

