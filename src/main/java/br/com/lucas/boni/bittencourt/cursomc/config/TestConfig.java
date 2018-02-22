package br.com.lucas.boni.bittencourt.cursomc.config;

import br.com.lucas.boni.bittencourt.cursomc.services.DBService;
import br.com.lucas.boni.bittencourt.cursomc.services.EmailService;
import br.com.lucas.boni.bittencourt.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


/**
 * COLOCA AS CLASSES QUE SERAO INSTANCIADAS NO PROFILE ESCOLHHIDO
 */
@Configuration
@Profile(value = "test")
public class TestConfig {

    @Autowired
    private DBService service;

    @Bean
    public boolean instanciateDataBase() throws ParseException {
        service.instanciedTestDataBase();       //popula o banco de teste
        return true;
    }

    @Bean // declara o email
    public EmailService emailSErvice() {
        return new MockEmailService();
    }
}
