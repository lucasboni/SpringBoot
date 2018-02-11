package br.com.lucas.boni.bittencourt.cursomc.repositoies;

import br.com.lucas.boni.bittencourt.cursomc.domain.Cliente;
import br.com.lucas.boni.bittencourt.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
