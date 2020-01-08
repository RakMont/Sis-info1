package com.adjcv01.adjcv01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Adjcv01Application {

	public static void main(String[] args) {
		SpringApplication.run(Adjcv01Application.class, args);
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		//El String que mandamos al metodo encode es el password que queremos encriptar.
		System.out.println(bCryptPasswordEncoder.encode("1234"));
	}

}
