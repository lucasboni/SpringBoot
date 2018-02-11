package br.com.lucas.boni.bittencourt.cursomc;

import br.com.lucas.boni.bittencourt.cursomc.domain.*;
import br.com.lucas.boni.bittencourt.cursomc.domain.enuns.TipoCliente;
import br.com.lucas.boni.bittencourt.cursomc.repositoies.*;
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

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

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

        
        Estado est1 = new Estado(null,"Minas Gerais");
        Estado est2 = new Estado(null,"São Paulo");

        Cidade cid1 = new Cidade(null,"Urbelandia",est1);
        Cidade cid2 = new Cidade(null,"São Paulo",est2);
        Cidade cid3 = new Cidade(null,"Campinaso",est2);

		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));

        Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","14256454632", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("997583472","2732228463"));

        Endereco e1 =new Endereco(null,"Rua Flores","300","Apto 203","Bairro Jardim","2943783",cli1,cid1);
        Endereco e2 =new Endereco(null,"Avenida Mattos","105","Sala 800","Bairro Centro","3428374",cli1,cid2);

        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));



        /*Salva no banco*/
		categoriaRepository.save(Arrays.asList(cat1,cat2));   //no curso falava pra usar add  so que aki funncionou o addall
		produtoRepository.save(Arrays.asList(p1,p2,p3));

        estadoRepository.save( Arrays.asList(est1,est2));
        cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));

        clienteRepository.save(Arrays.asList(cli1));
        enderecoRepository.save(Arrays.asList(e1,e2));



	}
}
