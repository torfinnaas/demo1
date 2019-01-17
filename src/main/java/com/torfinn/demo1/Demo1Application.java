package com.torfinn.demo1;


import com.torfinn.demo1.services.TurPakkeSrv;
import com.torfinn.demo1.services.TurSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static com.torfinn.demo1.domain.Grad.Medium;

@SpringBootApplication
public class Demo1Application {

	@Autowired
	private TurPakkeSrv turPakkeSrv;
	@Autowired
	private TurSrv turSrv;

	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}


	@PostConstruct
	private void init() {
		System.out.println("\n\n\nLaster inn data til DB...");
		turPakkeSrv.createTurPakke("BC", "Backpack California");
		turPakkeSrv.createTurPakke("CC", "California Calm");
		turPakkeSrv.createTurPakke("CY", "Cycling in California");

		turPakkeSrv.lookup().forEach(turPakke -> System.out.println(turPakke));

		turSrv.createTur("Gåtur over Golden Gate", "Rusletur over den berømte broen", 100, "CC", Medium);
		turSrv.createTur("Vinsmaking i Napa Wally", "Californias berømte vin distrikt", 110, "BC", Medium);
		turSrv.createTur("Fishermens Warft", "Omvisning på denne turistfellen", 120, "BC", Medium);
		turSrv.createTur("Pasific Hills", "Gåtur rundt i dette upper-class området", 130, "BC", Medium);

		turSrv.lookup().forEach(tur -> System.out.println(tur));
		System.out.println("\n\nBilligturer\n");
		turSrv.listBillige().forEach(tur -> System.out.println(tur));
		System.out.println("\n\n");
	}
}




