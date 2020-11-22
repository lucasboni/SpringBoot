package br.com.lucas.boni.bittencourt.cursomc;

import br.com.lucas.boni.bittencourt.cursomc.domain.*;
import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.EstadoPagamento;
import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.TipoCliente;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    /**
     * Execulta quando a aplicação inicia
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) {
    }
}
