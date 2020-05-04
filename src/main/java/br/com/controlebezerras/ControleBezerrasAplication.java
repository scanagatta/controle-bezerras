package br.com.controlebezerras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ControleBezerrasAplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleBezerrasAplication.class, args);
//		System.out.println("----------------------------------");
//		System.out.println("senha: ");
//		System.out.println(new BCryptPasswordEncoder().encode("123"));
//		System.out.println("----------------------------------");
	}

}
