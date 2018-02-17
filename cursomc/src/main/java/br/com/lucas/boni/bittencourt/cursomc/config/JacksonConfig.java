package br.com.lucas.boni.bittencourt.cursomc.config;

import br.com.lucas.boni.bittencourt.cursomc.domain.PagamentoComBoleto;
import br.com.lucas.boni.bittencourt.cursomc.domain.PagamentoComCartao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    // https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
    @Bean //falar para o json conseguir mapear as classes abstratas
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoComCartao.class);//se deve colocar manualmente aki as classes usadas
                objectMapper.registerSubtypes(PagamentoComBoleto.class);
                super.configure(objectMapper);
            };
        };
        return builder;
    }
}
