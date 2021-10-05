package br.com.molina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.molina.service.StartupService;

@Component
public class Startup implements CommandLineRunner{

	@Autowired
	private StartupService startupService;
	
	

	
	@Override
	public void run(String... args) throws Exception {
		startupService.loadCsv();
	}
	
	

}
