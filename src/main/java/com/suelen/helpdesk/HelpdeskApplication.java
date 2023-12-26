package com.suelen.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suelen.helpdesk.domain.Chamado;
import com.suelen.helpdesk.domain.Cliente;
import com.suelen.helpdesk.domain.Tecnico;
import com.suelen.helpdesk.domain.enums.Perfil;
import com.suelen.helpdesk.domain.enums.Prioridade;
import com.suelen.helpdesk.domain.enums.Status;
import com.suelen.helpdesk.repositories.ChamadoRepository;
import com.suelen.helpdesk.repositories.ClienteRepository;
import com.suelen.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner{

	
	@Autowired
	TecnicoRepository tecnicoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ChamadoRepository chamadoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	//esse método é implementado pelo comand liner runner
	//sempre que o spring startado, ele executa junto
		Tecnico tec1 = new Tecnico(null, "Michael Jackson", "20858233517", "michaelj@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Sia Chandeler", "66354895252", "sia@mail.com", "123");

		Chamado c1 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Conserto pc", "hd quebrou", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}			

}
