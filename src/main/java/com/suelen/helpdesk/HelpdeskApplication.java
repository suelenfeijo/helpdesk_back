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
public class HelpdeskApplication /*implements CommandLineRunner*/{

	

	
	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	//@Override
	//public void run(String... args) throws Exception {
	//esse método é implementado pelo comand liner runner
	//sempre que o spring startado, ele executa junto
	
	}			


