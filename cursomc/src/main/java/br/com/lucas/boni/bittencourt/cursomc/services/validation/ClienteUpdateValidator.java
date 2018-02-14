package br.com.lucas.boni.bittencourt.cursomc.services.validation;

import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.dto.ClienteDTO;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ClienteRepository;
import br.com.lucas.boni.bittencourt.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {


   @Autowired
   private ClienteRepository repo;

   @Autowired
    private HttpServletRequest request;

   @Override
   public void initialize(ClienteUpdate ann) {
   }
   @Override
   public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
       
       Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
       Integer uriId = Integer.parseInt(map.get("id"));
      List<FieldMessage> list = new ArrayList<>();


// inclua os testes aqui, inserindo erros na lista

      Cliente aux = repo.findByEmail(objDto.getEmail());

     if(aux!=null && !aux.getId().equals(uriId)) {
         list.add(new FieldMessage("email", "email já existente"));
     }

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
                 .addPropertyNode(e.getFildName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}
