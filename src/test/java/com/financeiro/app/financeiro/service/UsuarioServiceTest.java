package com.financeiro.app.financeiro.service;

import com.financeiro.app.financeiro.exception.RegraNegocioExeption;
import com.financeiro.app.financeiro.model.entity.Usuario;
import com.financeiro.app.financeiro.repositories.UsuarioRepository;
import com.financeiro.app.financeiro.services.UsuarioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioServiceTest {

    @Autowired
    UsuarioService service;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void validacaoEmail(){
        service.validarEmail("email@email");
    }

    @Test
    public void validacaoEmailExistente(){
        Usuario user = Usuario.builder().nome("nome").email("email@email.com").build();
        testEntityManager.persist(user);

        Assertions.assertThrows(RegraNegocioExeption.class, () -> {
            service.validarEmail("email@email.com");
        });
    }

    @Test
    public void validarSalvarUsuario(){
        Usuario user = Usuario.builder().nome("test").email("test@Test.com").senha("12354").build();

       testEntityManager.persist(user);
    }
}
