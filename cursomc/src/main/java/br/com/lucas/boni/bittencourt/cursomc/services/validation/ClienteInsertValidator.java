package br.com.lucas.boni.bittencourt.cursomc.services.validation;

import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.TipoCliente;
import br.com.lucas.boni.bittencourt.cursomc.dto.ClienteNewDTO;
import br.com.lucas.boni.bittencourt.cursomc.resources.exception.FieldMessage;
import br.com.lucas.boni.bittencourt.cursomc.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
   @Override
   public void initialize(ClienteInsert ann) {
   }
   @Override
   public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
      List<FieldMessage> list = new ArrayList<>();

     if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getId()) && !BR.isValidCpf(objDto.getCpfOuCnpj())){
        list.add(new FieldMessage("cpfOuCnpj","CPF INVÁLIDO"));
     }else if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getId()) && !BR.isValidCpf(objDto.getCpfOuCnpj())){
        list.add(new FieldMessage("cpfOuCnpj","CNPJ INVÁLIDO"));
     }

// inclua os testes aqui, inserindo erros na lista

      

      for (FieldMessage e : list) {
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(e.getMessage())
                 .addPropertyNode(e.getFildName()).addConstraintViolation();
      }
      return list.isEmpty();
   }
}
