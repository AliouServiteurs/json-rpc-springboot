package com.leserviteurs.json_rpc_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Spring Boot.
 * Lance le serveur JSON-RPC sur le port 8080 (par défaut).
 */

@SpringBootApplication
public class JsonRpcSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonRpcSpringbootApplication.class, args);
		        System.out.println("JSON-RPC Server is running on http://localhost:8080/jsonrpc");
	}

}
