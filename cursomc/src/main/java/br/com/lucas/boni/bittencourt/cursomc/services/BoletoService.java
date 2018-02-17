package br.com.lucas.boni.bittencourt.cursomc.services;

import br.com.lucas.boni.bittencourt.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preemcherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instanteDoPedido);
        calendar.add(Calendar.DAY_OF_MONTH,7);
        pagto.setDataVencimento(calendar.getTime());
    }
}
