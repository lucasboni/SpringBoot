package br.com.lucas.boni.bittencourt.cursomc;

import br.com.lucas.boni.bittencourt.cursomc.domain.Categoria;
import br.com.lucas.boni.bittencourt.cursomc.domain.Produto;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.CategoriaRepository;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	/**
	 * Execulta quando a aplicação inicia
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");

		Produto p1 = new Produto(null,"computador",2000.0);
		Produto p2 = new Produto(null,"impressora",800.0);
		Produto p3 = new Produto(null,"mause",80.0);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.save(Arrays.asList(cat1,cat2));   //no curso falava pra usar add  so que aki funncionou o addall
		produtoRepository.save(Arrays.asList(p1,p2,p3));
	}
}
