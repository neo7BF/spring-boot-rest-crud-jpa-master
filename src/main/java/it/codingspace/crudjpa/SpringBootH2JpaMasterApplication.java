package it.codingspace.crudjpa;

import java.util.stream.IntStream;

import org.jeasy.random.EasyRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.codingspace.crudjpa.model.Customer;
import it.codingspace.crudjpa.model.CustomerRepository;

@SpringBootApplication
public class SpringBootH2JpaMasterApplication implements CommandLineRunner {
	
	@Autowired
	CustomerRepository cr;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2JpaMasterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		EasyRandom er = new EasyRandom();
		
		IntStream counter = IntStream.range(0,10);
		
		counter.forEach((c) -> cr.save(er.nextObject(Customer.class)));
		
	}

}
