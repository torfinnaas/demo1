package com.torfinn.demo1;


import com.torfinn.demo1.services.TurPakkeSrv;
import com.torfinn.demo1.services.TurSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application implements Runnable {

	@Autowired
	private TurPakkeSrv turPakkeSrv;
	@Autowired
	private TurSrv turSrv;

	public static void main(String[] args) {

		SpringApplication.run(Demo1Application.class, args);
	}


	@Override
	public void run() {
		System.out.println("Laster inn data til DB...");
		turPakkeSrv.createTurPakke("BC", "Backpack California");
		turPakkeSrv.createTurPakke("CC", "BCalifornia Calm");
		turPakkeSrv.createTurPakke("CY", "Cycling in California");

		turPakkeSrv.lookup().forEach(turPakke -> System.out.println(turPakke));

		turSrv.createTur("01", "Gåtur over Golden Gate", "Rusletur over den berømte broen", 100, "BC");

	}
}




