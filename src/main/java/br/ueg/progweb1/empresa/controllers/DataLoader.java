package br.ueg.progweb1.empresa.controllers;
import br.ueg.progweb1.empresa.model.Usuario;
import br.ueg.progweb1.empresa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String username = "admin";
        String rawPassword = "admin";

        Optional<Usuario> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            // Se o usuário não existir, cria um novo
            System.out.println("Usuário '" + username + "' não encontrado. CRIANDO...");
            Usuario newUser = Usuario.builder()
                    .username(username)
                    .password(passwordEncoder.encode(rawPassword))
                    .build();
            userRepository.save(newUser);
            System.out.println("Usuário '" + username + "' criado com sucesso.");
        } else {
            // Se o usuário já existir, apenas verifica se a senha precisa ser atualizada
            Usuario existingUser = userOptional.get();
            if (!passwordEncoder.matches(rawPassword, existingUser.getPassword())) {
                System.out.println("Senha do usuário '" + username + "' está desatualizada. ATUALIZANDO...");
                existingUser.setPassword(passwordEncoder.encode(rawPassword));
                userRepository.save(existingUser);
                System.out.println("Senha do usuário '" + username + "' atualizada com sucesso.");
            } else {
                System.out.println("Usuário '" + username + "' já existe e a senha está correta.");
            }
        }
    }
}
