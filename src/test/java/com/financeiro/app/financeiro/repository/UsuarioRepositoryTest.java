package com.financeiro.app.financeiro.repository;

import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void verificarEmail(){

        Usuario usuario = Usuario.builder().nome("test").email("test@test.com").build();
        testEntityManager.persist(usuario);

        boolean result = usuarioRepository.existsByEmail("test@test.com");

        Assertions.assertTrue(result);

    }

    @Test
    public void verificarEmailNaoExistente(){
        boolean result = usuarioRepository.existsByEmail("test@test");
        Assertions.assertFalse(result);
    }
}
