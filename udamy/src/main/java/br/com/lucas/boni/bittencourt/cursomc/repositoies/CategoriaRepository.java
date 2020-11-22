package br.com.lucas.boni.bittencourt.cursomc.repositoies;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
