package com.arkahv.cotizador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class CotizadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CotizadorApplication.class, args);
	}

}
