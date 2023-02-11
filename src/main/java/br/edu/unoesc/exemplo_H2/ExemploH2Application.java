package br.edu.unoesc.exemplo_H2;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.exemplo_H2.model.Livro;
import br.edu.unoesc.exemplo_H2.repository.LivroRepository;

@SpringBootApplication
public class ExemploH2Application {

	public static void main(String[] args) {
		SpringApplication.run(ExemploH2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(LivroRepository repositorio) {
		return args ->{
			Livro l = new Livro(null, "O senhor dos pastéis", 666, "Tolkien");
			l = repositorio.save(l);
			
			repositorio.save(new Livro(null, "O Hobbit", 452, "J.R.R. Tolkien"));
			
			Optional<Livro> p = repositorio.findById(2);
			if (p.isPresent()) {
				System.out.println(p.get());
			} else {
				System.out.println("Registro não encontrado!");
			}
			
			l =  repositorio.findById(3).get();
			l.setTitulo("Titulo qualquer");
			l.setPaginas(100);
			l.setAutor("Fulano");
			repositorio.save(l);
			
			for (var livro : repositorio.findAll()) {
				System.out.println(livro);
			}
			for (var livro: repositorio.findByAutorContainingIgnoreCase("j")) {
				System.out.println(livro);
			}
			
			for (var livro: repositorio.porQtdPaginas(300)) {
				System.out.println(livro);
			}
			
			for (var livro: repositorio.findByFiltro("senhor")) {
				System.out.println(livro);
			}

		};
	}
}
