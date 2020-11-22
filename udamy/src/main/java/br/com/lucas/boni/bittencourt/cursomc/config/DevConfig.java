package br.com.lucas.boni.bittencourt.cursomc.config;

import br.com.lucas.boni.bittencourt.cursomc.services.DBService;
import br.com.lucas.boni.bittencourt.cursomc.services.EmailService;
import br.com.lucas.boni.bittencourt.cursomc.services.MockEmailService;
import br.com.lucas.boni.bittencourt.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;


/**
 * COLOCA AS CLASSES QUE SERAO INSTANCIADAS NO PROFILE ESCOLHHIDO
 */
@Configuration
@Profile(value = "dev")
public class DevConfig {

    @Autowired
    private DBService service;

    @Value("${spring.jpa.hibernate.ddl-auto}")//pega os dados de uma propriedade
    private String stratagy;

    @Bean//é obrigatorio retornar alguma coisa por isso retorna um true sempre
    public boolean instanciateDataBase() throws ParseException {
        if (!stratagy.equalsIgnoreCase("create")) {
            return false;
        } else {
            service.instanciedTestDataBase();       //popula o banco de teste
            return true;
        }
    }

    @Bean // declara o email
    public EmailService emailSErvice() {
        return new SmtpEmailService();
    }

}
